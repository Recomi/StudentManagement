package site.recomi.studentmanagement.gui.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.GirdButtonEntity;
import site.recomi.studentmanagement.entity.UserSharingPost;
import site.recomi.studentmanagement.gui.activities.BookActivity;
import site.recomi.studentmanagement.gui.activities.BrowserActivity;
import site.recomi.studentmanagement.gui.activities.CampusAssociationActivity;
import site.recomi.studentmanagement.gui.activities.CircleActivity;
import site.recomi.studentmanagement.gui.activities.ClassScheduleActivity;
import site.recomi.studentmanagement.gui.activities.GradeActivity;
import site.recomi.studentmanagement.gui.activities.JobWantedActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseMultiItemTypeRecyclerViewAdapter;
import site.recomi.studentmanagement.gui.adapter.Base.BaseNestedSVOnScrollChangeListener;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.Delegetes.SharingPostDelegete;
import site.recomi.studentmanagement.gui.adapter.MultiItemTypeSupport;
import site.recomi.studentmanagement.gui.adapter.PagerViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.gui.listenner.BaseRecyclerItemTouchListener;

import static android.content.Context.SENSOR_SERVICE;

public class HomeFragment extends Fragment implements View.OnClickListener, SensorEventListener {
    @BindView(R.id.refresh_home)
    PullRefreshLayout refresh_home;
    /*@BindView(R.id.recy_main_newest)
    RecyclerView recy;*/
    @BindView(R.id.recy_girdBtn_home)
    RecyclerView recy_girdBtn;
    @BindView(R.id.nestedSV_home)
    NestedScrollView nestedSV;
    @BindView(R.id.sensor)
    TextView sensor;

    View mView;
    Context mContext;
    ViewPager vp;
    MultiItemTypeSupport multiItemTypeSupport ;
    BaseMultiItemTypeRecyclerViewAdapter<UserSharingPost> adapter;
    BaseRecycleViewAdapter<GirdButtonEntity> adapter_girdBtn;
    List<String> bitmaps = new ArrayList<>();
    List<UserSharingPost> posts = new ArrayList<>();
    List<GirdButtonEntity> list_girdButtons ;
    SensorManager mSensorManager;

    int testData = 0;
    int testDataTop = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container , false);
        mContext = mView.getContext();
        ButterKnife.bind(this, mView);   //绑定ButterKnife

//        initView();                 //加载视图布局
        initGirdButtons();     //加载网格按钮布局
//        initNewestData();     //初始化最新的数据
        initMarqueeView(mView); //初始化滚动公告栏数据
        initSensor();
        return mView;
    }

    //加载网格按钮布局
    private void initGirdButtons() {
        list_girdButtons = new ArrayList<GirdButtonEntity>();
        list_girdButtons.add(new GirdButtonEntity(getString(R.string.library), R.drawable.ic_library));
        list_girdButtons.add(new GirdButtonEntity(getString(R.string.curriculum), R.drawable.ic_curriculum));
        list_girdButtons.add(new GirdButtonEntity(getString(R.string.job), R.drawable.job));
        list_girdButtons.add(new GirdButtonEntity(getString(R.string.society_associations), R.drawable.ic_associations));
        list_girdButtons.add(new GirdButtonEntity(getString(R.string.circle), R.drawable.ic_cricle));
        adapter_girdBtn = new BaseRecycleViewAdapter<GirdButtonEntity>(getContext(),list_girdButtons,R.layout.item_gird_btn) {
            @Override
            public void convert(ViewHolder holder, GirdButtonEntity girdButtonEntity, int position) {
                holder.setText(R.id.tv_gird_btn,girdButtonEntity.getName());
                holder.setImageResource(R.id.img_gird_btn,girdButtonEntity.getResId());
            }
        };
        recy_girdBtn.addOnItemTouchListener(new BaseRecyclerItemTouchListener(getContext(),new BaseRecyclerItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getContext() , BookActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext() , ClassScheduleActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext() , JobWantedActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext() , CampusAssociationActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getContext() , CircleActivity.class));
                        break;

                }
            }

            @Override
            public void onLongClick(View view, int posotion) {

            }
        }));
        recy_girdBtn.setAdapter(adapter_girdBtn);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),5,GridLayoutManager.VERTICAL,false);
        recy_girdBtn.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化最近信息的数据
     * */
    private void initNewestData() {
        posts.add(new UserSharingPost("白上吹血","12点22分","今天天气不错啊","http://recomi.site/license_pic/jpg" , 1 , 1 ,1 ));
        posts.add(new UserSharingPost("白上吹血","12点22分","今天天气不错啊","http://recomi.site/license_pic/jpg" , 1 , 1 ,1 ));
        posts.add(new UserSharingPost("白上吹血","12点22分","今天天气不错啊","http://recomi.site/license_pic/jpg" , 1 , 1 ,1 ));
        adapter = new BaseMultiItemTypeRecyclerViewAdapter<UserSharingPost>(getContext(), posts, new SharingPostDelegete()) {
            @Override
            public void convert(ViewHolder holder, UserSharingPost userSharingPost,int position) {
                if (position == getItemCount() -1) {
                    holder.setText(R.id.tv_loadmore,"加载中...");
                }
                else {
                    holder.setText(R.id.tv_name,userSharingPost.getName());
                    holder.setText(R.id.tv_post_time,userSharingPost.getTime());
                    holder.setText(R.id.tv_sharing_content,userSharingPost.getContent());
//                holder.setText(R.id.tv_name,userSharingPost.getHeadIconUrl());
                }
            }
        };
        nestedSV.setOnScrollChangeListener(
                new BaseNestedSVOnScrollChangeListener() {
                    @Override
                    public void onScrolledBotton() {
                        Log.e("到底了", "" + ++testData);
                    }

                    @Override
                    public void onScrolledTop() {
                        Log.e("顶部", "" + ++testDataTop);
                    }
                }
        );
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        /*recy.setLayoutManager(manager);
        recy.setAdapter(adapter);
        recy.setNestedScrollingEnabled(false);*/
    }


    //模拟加载更多数据
    private void refreshMoreData() {
        testData++;
        adapter.getList().add(new UserSharingPost("白上吹血" + testData,"12点22分","今天天气不错啊" + testData ,"http://recomi.site/license_pic/jpg",1,1,1));
        adapter.notifyDataSetChanged();
        Log.e("Data","Refresh:" + testData);
    }

    //初始化滚动信息栏
    private void initMarqueeView(View view){
        MarqueeView marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("2019年五年一贯制大专招生简章");
        info.add("关于电类职业技能鉴定报考通知");
        info.add("关于2019年自主招生简章的通知");
        info.add("关于计算机水平考试报名的通知");

        marqueeView.startWithList(info);
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Intent intent;
                switch (position){
                    case 0:
                            intent = new Intent(getActivity() , BrowserActivity.class);
                            intent.putExtra("site", "http://www.luodingpoly.cn/zs/zhaoshengkuaixun/wunianyiguanzhizhaoshengkuaixun/118.html");
                            getActivity().startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(getActivity() , BrowserActivity.class);
                        intent.putExtra("site", "http://113.107.212.68:81/xnbm/jnjxb/Article/Show.asp?id=1771");
                        getActivity().startActivity(intent);

                        break;
                    case 2:
                        intent = new Intent(getActivity() , BrowserActivity.class);
                        intent.putExtra("site", "http://www.luodingpoly.cn/zs/zhaoshengkuaixun/zizhuzhaoshengkuaixun/112.html");
                        getActivity().startActivity(intent);

                        break;
                    case 3:
                        intent = new Intent(getActivity() , BrowserActivity.class);
                        intent.putExtra("site", "http://113.107.212.68:81/xnbm/jnjxb/Article/Show.asp?id=1768\n");
                        getActivity().startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        vp = mView.findViewById(R.id.vp);
        bitmaps.add("http://img0.imgtn.bdimg.com/it/u=1899561195,3106332361&fm=26&gp=0.jpg");
        bitmaps.add("http://www.luodingpoly.cn/zs/themes/zs/images/banner1.jpg");
        bitmaps.add("http://www.luodingpoly.cn/zs/themes/zs/images/banner3.jpg");
        vp.setAdapter(new PagerViewAdapter(getContext() ,bitmaps));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int mDetector = 0;
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            sensor.setText(String.valueOf(event.values[0]));

        }
        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0) {
                mDetector++;
                //event.values[0]一次有效计步数据
                Toast.makeText(getContext(), String.valueOf(mDetector), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void initSensor(){
        mSensorManager = (SensorManager) Objects.requireNonNull(getActivity()).getSystemService(SENSOR_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Sensor  mStepCount = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            Sensor  mStepDetector = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            mSensorManager.registerListener(this, mStepDetector, SensorManager.SENSOR_DELAY_FASTEST);
            mSensorManager.registerListener(this, mStepCount, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    class RefreshMoreData extends Thread {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    refreshMoreData();
                }
            });
        }
    }


}
