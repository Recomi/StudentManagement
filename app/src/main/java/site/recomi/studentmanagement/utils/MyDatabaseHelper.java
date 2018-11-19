package site.recomi.studentmanagement.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabaseHelper";
    // autoincrement关键字的作用是自增长,在往Book表中添加数据的时候,可以不添加id数据,应该其会自动增长.
    private static final String CREATE_TABLE = "create table TitleContentTable("
            + "id integer primary key autoincrement,"
            + "title text,"
            + "content longtext,"
            + "month text,"
            + "day text" + ")";


    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //新建语句,此方法的特点就是如果新建的数据库已经存在,那么此方法将不会再次执行.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //执行语句
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d(TAG, "onCreate: ");
    }

    //升级语句,这个方法的执不行执行要看        myDatabaseHelper = new MyDatabaseHelper(this , "BookStore.db" , null ,1);语句中的版本号,之前我们传入的是1,我们现在传入比1大的值,onUpgrade方法就会执行了.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}

