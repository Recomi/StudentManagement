package site.recomi.studentmanagement.gui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import site.recomi.studentmanagement.R;

public class WelcomeActivity extends AppCompatActivity {
    SharedPreferences.Editor editor;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            getHome();
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        initializationSettings();
        if (readIsFirstStart()){
            editor.putBoolean("isFirstStart" , false);
            editor.apply();

            startActivity(new Intent(WelcomeActivity.this , GuideActivity.class));
            finish();
        }else {
            //如果不是第一次启动,则设置欢迎界面启动主页面的定时任务
            handler.sendEmptyMessageDelayed(0,1500);
        }

    }

    //第一次启动程序初始化一个设置清单
    private void initializationSettings(){
        editor = getSharedPreferences("settings" , MODE_PRIVATE).edit();
        editor.apply();
    }

    //读取程序是否第一次启动
    private Boolean readIsFirstStart(){
        SharedPreferences sharedPreferences = getSharedPreferences("settings" , MODE_PRIVATE);
        Boolean isFirstStart = sharedPreferences.getBoolean("isFirstStart" , true);
        return isFirstStart;
    }

    public void getHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
