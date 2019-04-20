package site.recomi.studentmanagement.gui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.bin.david.form.core.SmartTable;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import site.recomi.studentmanagement.Constant;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.other.StudentGrade;
import site.recomi.studentmanagement.other.entitiy.UserClasssCheduleInfo;

public class ClassScheduleActivity extends MySwipeBackActivity {
    SmartTable<UserClasssCheduleInfo> smartTable;
    PieChart picChart;
    List<JSONObject> finalData;     //存储从服务器接受并解析的JSONObject对象集合，共10个对象，第一节~第十节的所有数据
    List<UserClasssCheduleInfo> list = new ArrayList<>();       // 存放待显示的实体类数据集合，周一~周日
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(() -> swipeRefreshLayout.post(this::getOnlineData));


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        setToolbarPaddingTop(toolbar);
        toolbar.setTitle("课表查询");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);

        initChart();
        initTable();
        getOnlineData();



    }

    public void initTable(){
        smartTable =  (SmartTable<UserClasssCheduleInfo>) findViewById(R.id.table);
        smartTable.getConfig().setShowXSequence(false).setShowYSequence(false);
        smartTable.getConfig().setFixedYSequence(true);
        //X序号列
        smartTable.getConfig().setFixedXSequence(true);
        //列标题
        smartTable.getConfig().setFixedCountRow(true);
        //统计行
        smartTable.getConfig().setFixedTitle(true);
    }

    public void initChart(){
        picChart = (PieChart)findViewById(R.id.pic_chart);
        picChart.getDescription().setEnabled(false);    //设置是否开启描述

        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"数据结构和算法分析"));
        strings.add(new PieEntry(20f,"JAVA高级设计"));
        strings.add(new PieEntry(10f,"计算机英语"));
        strings.add(new PieEntry(10f,"高等数学"));
        strings.add(new PieEntry(20f,"体育和运动"));
        strings.add(new PieEntry(10f,"晚修"));

        PieDataSet dataSet = new PieDataSet(strings,"");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#778899"));
        colors.add(Color.parseColor("#F08080"));
        colors.add(Color.parseColor("#90ee90"));
        colors.add(Color.parseColor("#FF7F50"));
        colors.add(Color.parseColor("#DAA520"));
        colors.add(Color.parseColor("#FFA500"));

        dataSet.setColors(colors);
        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLineColor(Color.LTGRAY);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        Legend legend = picChart.getLegend();
        legend.setEnabled(false);
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);


        picChart.setData(pieData);
        picChart.invalidate();
    }

    /*
     * 发起网络请求,获取服务器数据
     * */
    private void getOnlineData(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type" , "classSchedule")
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
                Log.d("failure", "onFailure: "+ e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    finalData = new ArrayList<>();
                    String data =  response.body().string().replace("\\", "");      //接受并处理掉转义符\
                    String data2 = data.substring(1,data.length() - 1);    //处理掉前后"符号
                    Log.d("xxxxxx", "服务器返回的数据: " + data2);

                    //将获取到的数据存入

                    JSONArray dataArray = new JSONArray(data2);
                    for (int i=0;i < dataArray.length(); i++){
                        finalData.add(dataArray.getJSONObject(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //数据获取完后，更新界面，显示数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        for (int i = 0; i < finalData.size(); i++){
                            JSONObject jsonObject = finalData.get(i);
                            try {
                                list.add(new UserClasssCheduleInfo(String.valueOf(i) , jsonObject.getString("monday"), jsonObject.getString("tuesday"), jsonObject.getString("wednesday"), jsonObject.getString("thursday"), jsonObject.getString("friday") , jsonObject.getString("saturday"), jsonObject.getString("sunday")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        swipeRefreshLayout.setRefreshing(false);
                        smartTable.setData(list);
                    }
                });

            }


        });
    }
}
