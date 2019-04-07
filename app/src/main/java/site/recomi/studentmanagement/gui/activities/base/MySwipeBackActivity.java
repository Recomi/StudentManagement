package site.recomi.studentmanagement.gui.activities.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

public class MySwipeBackActivity extends SwipeBackActivity {
    protected Context mContext;
    protected boolean ifRemove;

    protected boolean DARK_STATUS_FONT = true;
    protected boolean LIGHT_STATUS_FONT = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        }
        //状态栏文字颜色默认为黑色
        setStatusBarTextColor(DARK_STATUS_FONT);
    }

    /**
     * 设置状态栏文字的颜色
     * 传入true / DARK_STATUS_FONT ：黑色
     * 传入false  / LIGHT_STATUS_FONT：白色
     * */
    protected void setStatusBarTextColor(boolean isDark) {
        View decor = getWindow().getDecorView();
        if (isDark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// back button
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 快速启动活动
     * */
    private void startActivityOnly(Class c) {
        Intent intent = new Intent(mContext, c);
        startActivity(intent);
    }
}
