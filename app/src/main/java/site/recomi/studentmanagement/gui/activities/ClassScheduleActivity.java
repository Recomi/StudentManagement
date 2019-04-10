package site.recomi.studentmanagement.gui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.bin.david.form.core.SmartTable;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
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
    PieChart picChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        toolbar.setTitle("课表查询");
//        toolbar.setTitleTextColor(Color.parseColor("#000000"));
//        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        initChart();
        initTable();



    }

    public void initTable(){
        List<UserClasssCheduleInfo> list = new ArrayList<>();
        list.add(new UserClasssCheduleInfo("1" ,"数据结构和算法分析" ,"" ,"高等数学" ,"" ,"数据结构和算法分析" ,"" ,"" ));
        list.add(new UserClasssCheduleInfo("2" ,"数据结构和算法分析" ,"" ,"高等数学" ,"" ,"数据结构和算法分析" ,"" ,"" ));
        list.add(new UserClasssCheduleInfo("3" ,"" ,"数据结构和算法分析" ,"" ,"计算机英语" ,"" ,"" ,"" ));
        list.add(new UserClasssCheduleInfo("4" ,"" ,"数据结构和算法分析" ,"" ,"计算机英语" ,"" ,"" ,"" ));
        list.add(new UserClasssCheduleInfo("5" ,"高等数学" ,"" ,"JAVA高级设计" ,"" ,"JAVA高级设计" ,"" ,"JAVA高级设计" ));
        list.add(new UserClasssCheduleInfo("6" ,"高等数学" ,"" ,"JAVA高级设计" ,"" ,"JAVA高级设计" ,"" ,"JAVA高级设计" ));
        list.add(new UserClasssCheduleInfo("7" ,"计算机英语" ,"体育和运动" ,"" ,"体育和运动" ,"数据结构和算法分析" ,"" ,"" ));
        list.add(new UserClasssCheduleInfo("8" ,"计算机英语" ,"体育和运动" ,"" ,"体育和运动" ,"数据结构和算法分析" ,"" ,"" ));
        list.add(new UserClasssCheduleInfo("9" ,"" ,"晚修" ,"数据结构和算法分析" ,"" ,"晚修" ,"" ,"" ));
        list.add(new UserClasssCheduleInfo("10" ,"" ,"晚修" ,"数据结构和算法分析" ,"" ,"晚修" ,"" ,"" ));

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
        picChart = (PieChart)findViewById(R.id.pic_chart);
        picChart.getDescription().setEnabled(false);    //设置是否开启描述

        List<PieEntry> strings = new ArrayList<>();
        strings.add(new PieEntry(30f,"数据结构和算法分析"));
        strings.add(new PieEntry(20f,"JAVA高级设计"));
        strings.add(new PieEntry(10f,"计算机英语"));
        strings.add(new PieEntry(10f,"高等数学"));
        strings.add(new PieEntry(20f,"体育和运动"));
        strings.add(new PieEntry(10f,"晚修"));

        PieDataSet dataSet = new PieDataSet(strings,"");

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.parseColor("#778899"));
        colors.add(Color.parseColor("#F08080"));
        colors.add(Color.parseColor("#90ee90"));
        colors.add(Color.parseColor("#FF7F50"));
        colors.add(Color.parseColor("#DAA520"));
        colors.add(Color.parseColor("#FFA500"));

        dataSet.setColors(colors);
        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLineColor(Color.LTGRAY);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        Legend legend = picChart.getLegend();
        legend.setEnabled(false);
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(true);


        picChart.setData(pieData);
        picChart.invalidate();
    }
}
