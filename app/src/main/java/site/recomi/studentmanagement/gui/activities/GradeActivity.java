package site.recomi.studentmanagement.gui.activities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.format.bg.BaseBackgroundFormat;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.style.LineStyle;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import site.recomi.studentmanagement.Constant;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.UserSharingPost;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.other.StudentGrade;
import site.recomi.studentmanagement.other.entitiy.UserClasssCheduleInfo;

public class GradeActivity extends MySwipeBackActivity {
    SmartTable<StudentGrade> smartTable;
    List<JSONObject> finalData;         //包含全部学期的数据(课程名/成绩)
    List<StudentGrade> show = new ArrayList<>();        //要显示的数据
    private static final String TAG = "GradeActivity";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        getOnlineData();

        initView();
        initTable();

    }

    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        toolbar.setTitle("成绩查询");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (finalData != null){
                    JSONObject data = finalData.get(position);
                    Iterator<String> it = data.keys();
                    while(it.hasNext()){
                        try {
                            String key = it.next();
                            int value = Integer.parseInt(data.getString(key));
                            show.add(new StudentGrade(key, value));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    smartTable.setData(show);
                    show.clear();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void initTable(){
        //数据，最终需要从云数据库读取


        smartTable =  (SmartTable<StudentGrade>) findViewById(R.id.table);
        smartTable.getConfig().setShowXSequence(false).setShowYSequence(false);
        smartTable.setData(show);

        TableConfig tc = smartTable.getConfig();


        tc.setContentStyle(new FontStyle().setTextSize(50));
        tc.setColumnTitleStyle(new FontStyle().setTextSize(50));
        tc.setContentCellBackgroundFormat(new BaseCellBackgroundFormat<CellInfo>() {
            @Override
            public int getBackGroundColor(CellInfo cellInfo) {
                if(cellInfo.row%2 ==1) {
                    return ContextCompat.getColor(GradeActivity.this, R.color.gray);      //需要在 app/res/values 中添加 <color name="tableBackground">#d4d4d4</color>
                }else{
                    return TableConfig.INVALID_COLOR;
                }
            }
        });
    }

    /*
     * 发起网络请求,获取服务器数据
     * */
    private void getOnlineData(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type" , "grade")
                .add("studentid" , "17304590114")
                .build();
        Request request = new Request.Builder()
                .url(Constant.MAIN_PHP)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //针对异常情况处理
                Log.d("xxxxx", "onFailure: "+ e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {

                    finalData = new ArrayList<>();
                    String data =  response.body().string().replace("\\", "");      //接受并处理掉转义符\
                    String data2 = data.substring(1,data.length() - 1);    //处理掉前后"符号
                    Log.d("xxxxxx", "服务器返回的数据: " + data2);

                    JSONArray dataArray = new JSONArray(data2);
                    for (int i=0;i < dataArray.length(); i++){
                        finalData.add(dataArray.getJSONObject(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (finalData != null) {
                            JSONObject data = finalData.get(0);
                            Iterator<String> it = data.keys();
                            while (it.hasNext()) {
                                try {
                                    String key = it.next();
                                    int value = Integer.parseInt(data.getString(key));
                                    show.add(new StudentGrade(key, value));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            smartTable.setData(show);
                            show.clear();
                        }
                    }
                });
            }
        });
    }
}
