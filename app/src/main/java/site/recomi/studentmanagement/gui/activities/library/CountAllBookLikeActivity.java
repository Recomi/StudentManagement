package site.recomi.studentmanagement.gui.activities.library;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

import com.baoyz.widget.PullRefreshLayout;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.activities.notes.NewDiaryActivity;

public class CountAllBookLikeActivity extends MySwipeBackActivity {

    public static final int[] PIE_COLORS = {
            Color.parseColor("#778899"),//亮蓝灰（亮石板灰）
            Color.parseColor("#F08080"),//淡珊瑚色
            Color.parseColor("#90ee90"),//淡绿色
            Color.parseColor("#FF7F50"),//珊瑚色
            Color.parseColor("#DAA520"),//金菊黄
            Color.parseColor("#FFA500"),//橙色
            Color.parseColor("#87CEEB"),//天蓝色
            Color.parseColor("#2E8B57"),//海洋绿
//            Color.parseColor("#E6E6FA"),//淡紫色（薰衣草淡紫）
            Color.parseColor("#4169E1"),//皇家蓝/宝蓝
    };
    @BindView(R.id.chart_bookLike_school)
    PieChart chart_bookLike_school;
//    @BindView(R.id.pullRF_libraty_mine)
//    PullRefreshLayout pullRF_libraty_mine;
    HashMap dataMap  =  new  HashMap();         //HashMap用于存放键值对数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_all_book_like);
        ButterKnife.bind(this);   //绑定ButterKnife
        mContext = CountAllBookLikeActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        setToolbarPaddingTop(toolbar);
        // 主标题
        toolbar.setTitle("校内读书喜好分析");
        setTitle("校内读书喜好分析");
        // 左边的小箭头
        toolbar.setNavigationIcon(R.drawable.ic_back_dark_medium);
        setSupportActionBar(toolbar);
        initView();
        count();
    }


    private void initView() {
        String chart_name = "喜好";
//        pullRF_libraty_mine.setRefreshStyle(PullRefreshLayout.STYLE_SMARTISAN);
        chart_bookLike_school.setDrawHoleEnabled(false);              //true是环形图，false是饼图
        chart_bookLike_school.setExtraOffsets(20,20,20,25);//设置边距
        chart_bookLike_school.setHighlightPerTapEnabled(true);       //设置点击是否放大
        chart_bookLike_school.setUsePercentValues(true);             //设置是否使用百分比
        chart_bookLike_school.getDescription().setEnabled(false);    //设置是否开启描述
        chart_bookLike_school.setDragDecelerationFrictionCoef(.8f);//设置摩擦系数
//        chart_bookLike_school.setDrawCenterText(true);        //设置是否绘制环中的文字
        chart_bookLike_school.setCenterTextRadiusPercent(10f);//
//        chart_bookLike_school.setCenterText(chart_name);           //设置环中的文字
//        chart_bookLike_school.setCenterTextSize(18f);         //设置环中文字大小
        chart_bookLike_school.setRotationEnabled(true);       //设置是否可以旋转
        chart_bookLike_school.setRotationAngle(120f);         //设置旋转角度
//        chart_bookLike_school.setTransparentCircleRadius(55f);//设置半透明圆环的半径，立体感
        chart_bookLike_school.setTransparentCircleAlpha(110); //设置半透明圆环的透明度
//        chart_bookLike_school.setHoleColor(Color.WHITE);      //设置环中空白部分的颜色
    }

    /**
     * 统计
     * */
    private void count() {
        //实例化PieEntry的有序数列，PieEntry用于存放键值对：
        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        //Map存放数据（键值对）：
        dataMap.put("科技类" , "123");
        dataMap.put("人文社科类" , "605");
        dataMap.put("文艺类" , "422");
        dataMap.put("小说" , "210");
        dataMap.put("教育类" , "133");
        dataMap.put("生活类" , "89");
        dataMap.put("成功/励志" , "170");
        dataMap.put("经管类" , "154");
        dataMap.put("漫画" , "145");

        //Map获得Set的迭代器，遍历，将Map的键值对转化存入PieEntry数列
        for (Object o : dataMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            entries.add(new PieEntry(
                    Float.valueOf(entry.getValue().toString()), entry.getKey().toString()
            ));
        }
        //新建PieDataSet，构造函数传入PieEntry的数列和数据命名
        PieDataSet dataSet = new PieDataSet(entries,"");

        //设置饼块的显示属性
        dataSet.setValueLinePart1OffsetPercentage(80f);//设置数据连接线起始端距离圆心的百分比距离
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.4f);
        dataSet.setValueLineColor(Color.DKGRAY);  //设置连接线的颜色
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        dataSet.setSliceSpace(1f);  //设置饼块之间的间隔
        dataSet.setSelectionShift(5f);//设置饼块选中时偏离饼图中心的距离

        //设置饼块的颜色
        dataSet.setColors(PIE_COLORS);
        //PieDataSet转换成PieData
        PieData pieData = new PieData(dataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(14f);
        pieData.setValueTextColor(Color.parseColor("#FF005937"));
        //PieChart设置数据
        chart_bookLike_school.setData(pieData);
        //使整个图面无效化并重绘控件
//        chart_bookLike_school.invalidate();
    }

    /**
     * 刷新数据
     * */
    private void refresh() {

    }
}
