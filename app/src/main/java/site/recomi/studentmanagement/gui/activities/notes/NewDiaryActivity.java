package site.recomi.studentmanagement.gui.activities.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.Diary;
import site.recomi.studentmanagement.gui.activities.MainActivity;
import site.recomi.studentmanagement.gui.activities.base.BaseActivity;
import site.recomi.studentmanagement.utils.CountWordsUtil;

import static android.app.Activity.RESULT_OK;

public class NewDiaryActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    private Context mContext;
    private TextView title;
    private TextView content;
    private TextView weather;
    private TextView location;
    HashMap<String,String> data;


    /**
     *  返回数据到MainActivity
     * */
    public void backBundle(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        Bundle bd = new Bundle();
        bd.putBoolean("update",true);
        intent.putExtras(bd);
        setResult(RESULT_OK,intent);
    }

    private void initView(){
        title = (TextView) findViewById(R.id.text_new_title);
        content = (TextView) findViewById(R.id.text_new_content);
        weather = (TextView) findViewById(R.id.text_new_weather);
        location = (TextView) findViewById(R.id.text_new_address);
    }
    
    // 将week转化文字
    public String getWeek_stringId(int i){
        int[] ids = {R.string.sun,R.string.mon,R.string.tues,R.string.wed,R.string.thur,R.string.fri,R.string.sat};
        return getText(ids[i+1]).toString();
    }

    // 将月份转化文字
    public String getMonth_stringId(int i){
        int[] ids = {R.string.jan,R.string.feb,R.string.mar,
                R.string.apr,R.string.may,R.string.jun,
                R.string.jul,R.string.aug,R.string.sept,
                R.string.oct,R.string.nov,R.string.dec};
        return getText(ids[i]).toString();
    }

    // 从输入框获取Diary
    private Diary getDiary_fromInput(){
        Date date = new Date();
        Calendar now = new GregorianCalendar();
        now.setTime(date);
        Diary diary = new Diary(
                now.get(Calendar.DAY_OF_MONTH)+"",
                getMonth_stringId(now.get(Calendar.MONTH))+"/"+getWeek_stringId(now.get(Calendar.DAY_OF_WEEK_IN_MONTH)),
                now.get(Calendar.HOUR_OF_DAY)+""+":"+now.get(Calendar.MINUTE),
                title.getText().toString()+" ",
                content.getText().toString()+" ",
                weather.getText().toString()+" ",
                ""+location.getText().toString(),-1);
        return diary;
    }

    private void saveDiary(Diary diary){
        diary.save();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_diary);
        mContext = NewDiaryActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        setSupportActionBar(toolbar);
        // 主标题
        toolbar.setTitle("Title");
        // 左边的小箭头
        toolbar.setNavigationIcon(R.drawable.ic_back_dark_medium);
        // 菜单点击事件
        toolbar.setOnMenuItemClickListener(this);
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// back button
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onBackPressed() {
        if(ifExitDialog(NewDiaryActivity.this,R.string.exit_title,R.string.exit_content)){
            super.onBackPressed();
            NewDiaryActivity.this.finish();
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            // 保存日记
            case R.id.action_save:
                if(title.getText().equals("") || content.getText().equals("")){
                    AlertDialog.Builder normalDialog = new AlertDialog.Builder(mContext);
                    normalDialog.setTitle(R.string.exit_title);
                    normalDialog.setMessage(R.string.exit_content);
                    normalDialog.setPositiveButton(R.string.exit_yes,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    // 显示
                    normalDialog.show();
                }else{
                    saveDiary(getDiary_fromInput()); // 保存
                    backBundle(mContext); // 返回数据
                    finish();
                }
                break;
            // 显示统计字数
            case R.id.action_count:
                CountWordsUtil.displayCountWords(mContext,title.getText().toString().length(),content.getText().toString().length());
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toobar跟menu
        getMenuInflater().inflate(R.menu.menu_new_diary, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }
//
//    @Override
//    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
//        if (menu != null) {
//            if (menu.getClass() == MenuBuilder.class) {
//                try {
//                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    m.setAccessible(true);
//                    m.invoke(menu, true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return super.onPrepareOptionsPanel(view, menu);
//    }
}
