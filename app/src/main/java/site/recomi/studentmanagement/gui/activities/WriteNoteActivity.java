package site.recomi.studentmanagement.gui.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.utils.MyDatabaseHelper;

public class WriteNoteActivity extends AppCompatActivity {
    private EditText titleEditText,contentEditText;
    private int lastId;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);

        myDatabaseHelper = new MyDatabaseHelper(this , "Notes.db" , null ,1);
        db = myDatabaseHelper.getWritableDatabase();

        setDarkStatusIcon(true);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //显示Toolbar返回按钮
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //初始化标题框和内容框
        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);

        getDatabaseData();
    }

    //接受上个活动的数据
    public void getDatabaseData(){
        Intent intentLast = getIntent();
        lastId = intentLast.getIntExtra("id" , -1);
        Cursor cursor = db.query("TitleContentTable" , new String[]{"title" , "content" , "month" , "day"} ,  "id = ?", new String[]{String.valueOf(lastId)} , null , null , null);
        if (cursor.moveToFirst()) {
            String title = cursor.getString((cursor.getColumnIndex("title")));
            String content = cursor.getString((cursor.getColumnIndex("content")));
            titleEditText.setText(title);
            contentEditText.setText(content);
        }
        cursor.close();
    }


    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                //按钮HomeAsUp的id永远是android.R.id.home
                case android.R.id.home:
                    intent = new Intent();
                    intent.putExtra("key" , "back");
                    setResult(RESULT_OK , intent);
                    finish();
                    break;
                case R.id.save:
                    saveData();
                    break;
                case R.id.delete:
                    db.delete("TitleContentTable", "id = ?",new String[]{String.valueOf(lastId)});
                    finish();
                    break;
                case R.id.statistics:
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(WriteNoteActivity.this);
                    alertDialog.setTitle("字数统计");
                    final String temp = "标题字数: " + String.valueOf(titleEditText.getText().toString().length()) + "\n"
                            + "内容字数: " + String.valueOf(contentEditText.getText().toString().length()) + "\n"
                            + "总字数: " + String.valueOf(titleEditText.getText().toString().length() + contentEditText.getText().toString().length());
                    alertDialog.setMessage(temp);
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("返回", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.setNegativeButton("复制", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            cm.setPrimaryClip(ClipData.newPlainText(null,temp));
                            Toast.makeText(WriteNoteActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    alertDialog.show();
            }
            return true;
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.toolbar , menu);
            return true;
        }

        public void saveData(){
            String title = titleEditText.getText().toString();
            String content = contentEditText.getText().toString();
            String month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
            String day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            if (month.length() == 1){
                month = "0" + month;
            }
            if (day.length() == 1){
                day = "0" + day;
            }
        if (content.length() >500000){
            content = content.substring(0,500000);

        }

        //-1代表从悬浮按钮进入,证明用户要新建数据 ,否则说明用户单击RecyclerView中的某行数据,我们根据此行的id来更新数据.
        if (!(title.equals("") && content.equals("")) ) {
            if (lastId == -1){
                ContentValues values = new ContentValues();
                values.put("title" , title);
                values.put("content" , content);
                values.put("month" , month);
                values.put("day" , day);
                db.insert("TitleContentTable" , null ,values);
                values.clear();
            }else {
                ContentValues values = new ContentValues();
                values.put("title" , title);
                values.put("content" , content);
                db.update("TitleContentTable" , values , "id = ?" , new String[]{String.valueOf(lastId)});
                values.clear();
            }
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveData();
    }

    //设置状态栏白底黑图标
    public void setDarkStatusIcon(boolean bDark) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //getWindow()检索活动的当前窗口,返回一个Window.getDecorView()获取顶级窗口装饰视图.
            View decorView = getWindow().getDecorView();
            //设置状态栏颜色,就是style中colorPrimaryDark的颜色
            getWindow().setStatusBarColor(ContextCompat.getColor(this , R.color.colorWhite));
            if(decorView != null){
                int vis = decorView.getSystemUiVisibility();
                if(bDark){
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else{
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }
}
