package site.recomi.studentmanagement.gui.activities.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import site.recomi.studentmanagement.R;

public class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected boolean ifExit = false;

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
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ifExit = true;
                        finish();
                    }
                });
        normalDialog.setNegativeButton(R.string.exit_cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ifExit = false;
                    }
                });
        // 显示
        normalDialog.show();
        return ifExit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
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

}
