package site.recomi.studentmanagement.gui.activities.notes;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import org.litepal.LitePal;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.Diary;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.utils.CountWordsUtil;

public class ViewDiaryActivity extends MySwipeBackActivity implements Toolbar.OnMenuItemClickListener{
    private boolean ifRemove;
    private TextView title;
    private TextView content;
    private TextView weather;
    private TextView location;
    private List<Diary> diaries;    // Diary数据库集合
    private Diary diary;            // 本界面所管理的Diary数据

    /**
     *
     * 该方法用于删除日记前的提示框，需要传入显示提示框的context：xxx.this
     */
    public boolean ifRemoveDialog(Context context){
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle(R.string.remove_title);
        normalDialog.setMessage(R.string.remove_content);
        normalDialog.setPositiveButton(R.string.exit_yes,
                (dialog, which) -> {
                    // 删除本界面管理的Diary信息
                    diary.delete();
                    Toast.makeText(mContext, R.string.remove_successful, Toast.LENGTH_SHORT).show();
                    finish();
                    ifRemove = true;
                });
        normalDialog.setNegativeButton(R.string.exit_cancel,
                (dialog, which) -> ifRemove = false);
        // 显示
        normalDialog.show();
        return ifRemove;
    }

    /**
     *
     * 获取星期几字符串
     */
    public String getWeek_stringId(int i){
        int[] ids = {R.string.sun,R.string.mon,R.string.tues,R.string.wed,R.string.thur,R.string.fri,R.string.sat};
        return getText(ids[i+1]).toString();
    }

    /**
     * 获取月份字符串
     * */
    public String getMonth_stringId(int i){
        int[] ids = {R.string.jan,R.string.feb,R.string.mar,
                R.string.apr,R.string.may,R.string.jun,
                R.string.jul,R.string.aug,R.string.sept,
                R.string.oct,R.string.nov,R.string.dec};
        return getText(ids[i]).toString();
    }

    /**
     *
     * 该方法用于加载本界面中应该显示的Diary信息
     */
    private void loadDiaryInfo(){
        title.setText(diary.getTitle());
        content.setText(diary.getContent());
        weather.setText(diary.getWeather());
        location.setText(diary.getAddress());
    }
    /**
     * 更新Diary信息
     * */
    private void updateDiaryInfo(){
        // 获取当前时间
        Date date = new Date();
        Calendar now = new GregorianCalendar();
        now.setTime(date);
        // String
        String day_str = now.get(Calendar.DAY_OF_MONTH)+"";
        String monthAndWeek_str = getMonth_stringId(now.get(Calendar.MONTH))+"/"+getWeek_stringId(now.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        String time_str = now.get(Calendar.HOUR_OF_DAY)+""+":"+now.get(Calendar.MINUTE);
        String title_str = title.getText().toString()+" ";
        String content_str = content.getText().toString()+" ";
        String weather_str = weather.getText().toString()+" ";
        String address_str = ""+location.getText().toString();
        //
        diary.setDay(day_str);
        diary.setMonthAndWeek(monthAndWeek_str);
        diary.setTime(time_str);
        diary.setTitle(title_str);
        diary.setContent(content_str);
        diary.setWeather(weather_str);
        diary.setAddress(address_str);
        diary.save();
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diary);
        mContext = ViewDiaryActivity.this;
        title = (TextView) findViewById(R.id.text_view_title);
        content = (TextView) findViewById(R.id.text_view_content);
        weather = (TextView) findViewById(R.id.text_view_weather);
        location = (TextView) findViewById(R.id.text_view_address);
        // Toolbat
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        setToolbarPaddingTop(toolbar);
        setSupportActionBar(toolbar);
        // 主标题
//        toolbar.setTitle("");
        // 左边的小箭头
        toolbar.setNavigationIcon(R.drawable.ic_back_dark_medium);
        // 菜单点击事件
        toolbar.setOnMenuItemClickListener(this);
        // 获取Diary的id
        int id = getIntent().getIntExtra("id",0);
        // 获取数据库表
        diaries = LitePal.findAll(Diary.class);
        Collections.reverse(diaries);       // 倒序排序
        // 获取Diary实例
        diary = diaries.get(id);
        //加载Diary信息
        loadDiaryInfo();

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_view_remove:
                // 删除Diary
                if(ifRemoveDialog(mContext)) {
                    finish();
                }
                break;
            case R.id.action_view_save:
                // 保存Diary信息
                updateDiaryInfo();
                Toast.makeText(mContext, R.string.update_successful, Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.action_view_count:
                // 统计字数
                CountWordsUtil.displayCountWords(mContext,title.getText().toString().length(),content.getText().toString().length());
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 绑定toobar跟menu
        getMenuInflater().inflate(R.menu.menu_view_diary, menu);
        return true;
    }

    // 加载menus
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
}
