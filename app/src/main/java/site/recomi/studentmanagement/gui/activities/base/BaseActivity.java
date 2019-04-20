package site.recomi.studentmanagement.gui.activities.base;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import site.recomi.studentmanagement.R;

import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.TEXT_ALIGNMENT_CENTER;

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected boolean ifExit = false;
    protected boolean DARK_STATUS_FONT = true;
    protected boolean LIGHT_STATUS_FONT = false;

    /**
     * @setIcon 设置对话框图标(此处无用)
     * @setTitle 设置对话框标题
     * @setMessage 设置对话框消息提示
     * setXXX方法返回Dialog以链式设置属性对象
     *
     * TODO:该方法用于退出活动前的提示框，需要传入显示提示框的context：xxx.this
     */
    public boolean ifExitDialog(Context context,int title,int message){

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(context);
        normalDialog.setTitle(title);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton(R.string.exit_yes,
                (dialog, which) -> {
                    ifExit = true;
                    finish();
                });
        normalDialog.setNegativeButton(R.string.exit_cancel,
                (dialog, which) -> ifExit = false);
        // 显示
        normalDialog.show();
        return ifExit;
    }

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
     * 设置标题居中显示
     * */
    public void setTitleCenter(Toolbar toolbar) {
        int childCount = toolbar.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = toolbar.getChildAt(i);
            if (child instanceof TextView) {
                TextView childTitle = (TextView) child;
                if (childTitle.getText().equals(toolbar.getTitle())) {
                    int deviceWidth = getWindowManager().getDefaultDisplay().getWidth();
                    Paint p = childTitle.getPaint();
                    float textWidth = p.measureText(childTitle.getText().toString());
                    float tx = (deviceWidth - textWidth) / 2.0f - toolbar.getContentInsetLeft();
                    childTitle.setTranslationX(tx);
                    break;
                }
            }
        }
    }

    //沉浸式状态栏下，设置Toolbar的paddingTop
    public void setToolbarPaddingTop(Toolbar toolbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            toolbar.setPaddingRelative(0,getStatusBarHeight(),0,0);
    }

    //获取statusbar高度
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
