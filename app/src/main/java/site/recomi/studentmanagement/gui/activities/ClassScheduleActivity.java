package site.recomi.studentmanagement.gui.activities;

import android.os.Bundle;


import com.bin.david.form.core.SmartTable;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.other.entitiy.UserClasssCheduleInfo;

public class ClassScheduleActivity extends MySwipeBackActivity {
    SmartTable<UserClasssCheduleInfo> smartTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);
        initChart();
        initTable();



    }

    public void initTable(){
        List<UserClasssCheduleInfo> list = new ArrayList<>();
        list.add(new UserClasssCheduleInfo("第一节" ,"数据结构和算法分析" ,"123" ,"123" ,"123" ,"123" ,"123" ,"数据结构和算法分析" ));
        list.add(new UserClasssCheduleInfo("第二节" ,"123" ,"数据结构和算法分析" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第三节" ,"123" ,"数据结构和算法分析" ,"123" ,"123" ,"数据结构和算法分析" ,"数据结构和算法分析" ,"123" ));
        list.add(new UserClasssCheduleInfo("第四节" ,"123" ,"123" ,"数据结构和算法分析" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第五节" ,"数据结构和算法分析" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第六节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"数据结构和算法分析" ));
        list.add(new UserClasssCheduleInfo("第七节" ,"123" ,"123" ,"123" ,"123" ,"数据结构和算法分析" ,"数据结构和算法分析" ,"123" ));
        list.add(new UserClasssCheduleInfo("第八节" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第九节" ,"123" ,"数据结构和算法分析" ,"数据结构和算法分析" ,"数据结构和算法分析" ,"数据结构和算法分析" ,"123" ,"123" ));
        list.add(new UserClasssCheduleInfo("第十节" ,"123" ,"123" ,"123" ,"123" ,"数据结构和算法分析" ,"123" ,"123" ));

        smartTable =  (SmartTable<UserClasssCheduleInfo>) findViewById(R.id.table);
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

    public void initChart(){
        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"aaa"));
        strings.add(new PieEntry(70f,"bbb"));

        PieDataSet dataSet = new PieDataSet(strings,"Label");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(R.color.colorPrimary));
        colors.add(getResources().getColor(R.color.colorAccent));
        dataSet.setColors(colors);

        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);

        PieChart picChart = (PieChart)findViewById(R.id.pic_chart);
        picChart.setData(pieData);
        picChart.invalidate();
    }
}
