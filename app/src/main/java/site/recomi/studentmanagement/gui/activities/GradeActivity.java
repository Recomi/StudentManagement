package site.recomi.studentmanagement.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bin.david.form.core.SmartTable;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.other.StudentGrade;
import site.recomi.studentmanagement.other.entitiy.UserClasssCheduleInfo;

public class GradeActivity extends AppCompatActivity {
    SmartTable<StudentGrade> smartTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        initTable();
    }

    public void initTable(){
        List<StudentGrade> list = new ArrayList<>();
        list.add(new StudentGrade("lksdajf" , 4574));


        smartTable =  (SmartTable<StudentGrade>) findViewById(R.id.table);
        smartTable.getConfig().setShowXSequence(false).setShowYSequence(false);
        smartTable.setData(list);

        //Y序号列
        smartTable.getConfig().setFixedYSequence(true);
        //X序号列
        smartTable.getConfig().setFixedXSequence(true);
        //列标题
        smartTable.getConfig().setFixedCountRow(true);
        //统计行
        smartTable.getConfig().setFixedTitle(true);
    }
}
