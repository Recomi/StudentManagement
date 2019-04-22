package site.recomi.studentmanagement.gui.fragments.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.sunfusheng.marqueeview.MarqueeView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import site.recomi.studentmanagement.Constant;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.CarouselMap;
import site.recomi.studentmanagement.entity.GirdButtonEntity;
import site.recomi.studentmanagement.entity.UserSharingPost;
import site.recomi.studentmanagement.gui.activities.BookActivity;
import site.recomi.studentmanagement.gui.activities.BrowserActivity;
import site.recomi.studentmanagement.gui.activities.CampusAssociationActivity;
import site.recomi.studentmanagement.gui.activities.CircleActivity;
import site.recomi.studentmanagement.gui.activities.ClassScheduleActivity;
import site.recomi.studentmanagement.gui.activities.JobWantedActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseMultiItemTypeRecyclerViewAdapter;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.CarouselMapPagerViewAdapter;
import site.recomi.studentmanagement.gui.adapter.MultiItemTypeSupport;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.gui.fragments.Base.BaseFragment;
import site.recomi.studentmanagement.gui.listenner.BaseRecyclerItemTouchListener;

import static android.content.Context.SENSOR_SERVICE;

public class HomeFragment extends BaseFragment implements SensorEventListener {
    @BindView(R.id.refresh_home)
    PullRefreshLayout refresh_home;
    @BindView(R.id.recy_girdBtn_home)
    RecyclerView recy_girdBtn;
    @BindView(R.id.nestedSV_home)
    NestedScrollView nestedSV;
    @BindView(R.id.sensor)
    TextView sensor;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.km)
    TextView km;
    @BindView(R.id.kilocalorie)
    TextView kilocalorie;

    MarqueeView marqueeView;
    BaseMultiItemTypeRecyclerViewAdapter<UserSharingPost> adapter;
    BaseRecycleViewAdapter<GirdButtonEntity> adapter_girdBtn;
    List<CarouselMap> carouselMaps = new ArrayList<>();         //轮播图的实例列表
    List<GirdButtonEntity> list_girdButtons ;
    SensorManager mSensorManager;
    List<String> info;
    ArrayList<String> list_articleSrc = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container , false);
        mContext = mView.getContext();
        ButterKnife.bind(this, mView);

//        initPagerView();
        getOnlineData();
        initGirdButtons();
        initMarqueeView(mView);
        initSensor();

        return mView;
    }


/*    *//*
    * 初始化滚动界面数据
    * *//*
    private void initPagerView(){
        bitmaps.add("https://recomi.site/campus_management_system_pic/home_page/one.jpg");
        bitmaps.add("https://recomi.site/campus_management_system_pic/home_page/two.jpg");
        bitmaps.add("https://recomi.site/campus_management_system_pic/home_page/three.jpg");
        vp.setAdapter(new CarouselMapPagerViewAdapter(getContext() ,bitmaps));
    }*/

    /*
     * 初始化滚动信息栏
     * */
    private void initMarqueeView(View view){
        marqueeView = view.findViewById(R.id.marqueeView);
        info = new ArrayList<>();
        info.add("2019年五年一贯制大专招生简章");
        info.add("关于电类职业技能鉴定报考通知");
        info.add("关于2019年自主招生简章的通知");
        info.add("关于计算机水平考试报名的通知");

//        marqueeView.startWithList(info);
        marqueeView.setOnItemClickListener((position, textView) -> {
            Intent intent = new Intent(mContext , BrowserActivity.class);
            String articleSrc = list_articleSrc.get(position);
            intent.putExtra("site", Constant.ARTICLES_URL+ "/" + articleSrc);
            mContext.startActivity(intent);
        });
    }

    /*
    * 加载首页五个按钮网格按钮布局
    * */
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

    /*
    * 初始化计步传感器
    * */
    private void initSensor(){
        mSensorManager = (SensorManager) Objects.requireNonNull(getActivity()).getSystemService(SENSOR_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Sensor  mStepCount = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            Sensor  mStepDetector = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
            mSensorManager.registerListener(this, mStepDetector, SensorManager.SENSOR_DELAY_FASTEST);
            mSensorManager.registerListener(this, mStepCount, SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    /*
    * implements SensorEventListener
    * 读取系统计步传感器的数据
    * */
    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        int mDetector = 0;
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            sensor.setText(String.valueOf((int)(event.values[0])));
            kilocalorie.setText(String.valueOf(((int)(event.values[0] * 0.027)) + "千卡"));
            km.setText(String.valueOf(((int)(event.values[0] * 0.75*0.001))) + "公里");

        }
        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            if (event.values[0] == 1.0) {
                mDetector++;
                //event.values[0]一次有效计步数据
//                Toast.makeText(getContext(), String.valueOf(mDetector), Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
    * implements SensorEventListener
    * 传感器的精度变化时调用
    * */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    /*
     * 发起网络请求,获取服务器数据
     * */
    private void getOnlineData(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type" , "homePageArticle")
                .build();
        Request request = new Request.Builder()
                .url(Constant.MAIN_PHP)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                    toastLongMessage(mContext, "读取封面数据异常，请检查网络是否通畅");
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String data =  response.body().string();
                    JSONArray jsonArray = new JSONArray(data);

                    //封面数量由获取的JSONArray控制，每张封面图片的文件名对应其文章的文件名
                    for (int i = 0; i < jsonArray.length(); i++){
                        String imgSrc = jsonArray.getJSONObject(i).getString("imgSrc");
                        String articleSrc = jsonArray.getJSONObject(i).getString("articleSrc");
                        list_articleSrc.add(articleSrc);
                        String img_url = Constant.IMG_URL + "/home_page/" + imgSrc;
                        String article_url = Constant.ARTICLES_URL + "/" + articleSrc;
//                        Log.e("首页封面图片",Constant.IMG_URL + "/home_page/" + imgSrc);
                        //加入到轮播实例列表
                        carouselMaps.add(new CarouselMap(img_url,article_url));
                    }
                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
//                        Toast.makeText(mContext,String.valueOf( info.size()), Toast.LENGTH_SHORT).show();
                        marqueeView.startWithList(info);
                        vp.setAdapter(new CarouselMapPagerViewAdapter(getContext() , carouselMaps));
                    });
                } catch (JSONException e) {
                    Objects.requireNonNull(getActivity()).runOnUiThread(() -> {
                        toastLongMessage(mContext, "读取封面json异常，请检查网络是否通畅");
                    });
                }
            }
        });
    }
}
