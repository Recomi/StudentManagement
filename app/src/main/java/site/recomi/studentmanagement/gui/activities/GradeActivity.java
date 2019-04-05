package site.recomi.studentmanagement.gui.activities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.core.TableConfig;
import com.bin.david.form.data.CellInfo;
import com.bin.david.form.data.format.bg.BaseBackgroundFormat;
import com.bin.david.form.data.format.bg.BaseCellBackgroundFormat;
import com.bin.david.form.data.style.FontStyle;
import com.bin.david.form.data.style.LineStyle;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.other.StudentGrade;
import site.recomi.studentmanagement.other.entitiy.UserClasssCheduleInfo;

public class GradeActivity extends AppCompatActivity {
    SmartTable<StudentGrade> smartTable;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark2));

        initTable();        //初始化表格
    }

    public void initTable(){
        //数据，最终需要从云数据库读取
        List<StudentGrade> list = new ArrayList<>();
        list.add(new StudentGrade("lksdajf" , 55));
        list.add(new StudentGrade("lksdnjnbfgajf" , 88));
        list.add(new StudentGrade("lksdajf" , 75));
        list.add(new StudentGrade("lksfgdajf" , 44));
        list.add(new StudentGrade("lksfdgdajf" , 99));
        list.add(new StudentGrade("lksfdgdajf" , 100));
        list.add(new StudentGrade("lksdajf" , 24));
        list.add(new StudentGrade("lkfdgsdajf" , 87));


        smartTable =  (SmartTable<StudentGrade>) findViewById(R.id.table);
        smartTable.getConfig().setShowXSequence(false).setShowYSequence(false);
        smartTable.setData(list);

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
}
