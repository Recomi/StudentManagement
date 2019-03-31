package site.recomi.studentmanagement.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.table.TableData;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.other.UserClasssCheduleInfo;

public class ClassScheduleActivity extends AppCompatActivity {
    SmartTable<UserClasssCheduleInfo> smartTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        List<UserClasssCheduleInfo> list = new ArrayList<>();
        list.add(new UserClasssCheduleInfo("第一节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第二节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第三节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第四节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第五节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第六节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第七节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第八节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第九节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第十节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));

        smartTable =  findViewById(R.id.table);
        smartTable.getConfig().setShowXSequence(false).setShowYSequence(false);
        smartTable.setData(list);


    }
}
