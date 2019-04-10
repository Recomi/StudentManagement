package site.recomi.studentmanagement.gui.fragments.main;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.TitleAndIconEntity;
import site.recomi.studentmanagement.gui.activities.AccountActivity;
import site.recomi.studentmanagement.gui.activities.BookActivity;
import site.recomi.studentmanagement.gui.activities.GradeActivity;
import site.recomi.studentmanagement.gui.activities.LoginActivity;
import site.recomi.studentmanagement.gui.activities.mineFeatures.MyClassActivity;
import site.recomi.studentmanagement.gui.activities.mineFeatures.MyCollectionsActivity;
import site.recomi.studentmanagement.gui.activities.mineFeatures.MyGroupActivity;
import site.recomi.studentmanagement.gui.activities.mineFeatures.SettingsActivity;
import site.recomi.studentmanagement.gui.activities.mineFeatures.ViewHistoryActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.gui.fragments.Base.BaseFragment;
import site.recomi.studentmanagement.gui.listenner.BaseRecyclerItemTouchListener;
import site.recomi.studentmanagement.other.LoginEvent;

public class MineFragment extends BaseFragment {
    @BindView(R.id.pullRF_mine)
    PullRefreshLayout pull_refresh;
    @BindView(R.id.card_mine_user)
    CardView card_mine;
    @BindView(R.id.recy_mine_features)
    RecyclerView recy_features;
    @BindView(R.id.tv_mine_name)        //用户名
    TextView tv_mine_name;
    @BindView(R.id.circleImageView)         //用户头像
    CircleImageView circleImageView;


    View mView;
    Context mContext;
    ViewPager vp;
    BaseRecycleViewAdapter<TitleAndIconEntity> adapter;
    List<TitleAndIconEntity> moreFeaturesList;


    int testData = 0;
    int testDataTop = 0;
    String userName;
    boolean is_loggedIn = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, container, false);
        mContext = mView.getContext();
        ButterKnife.bind(this, mView);   //绑定ButterKnife
        EventBus.getDefault().register(this);
        initView();                 //加载视图布局
        tv_mine_name.setText(userName);
        return mView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //处理login活动传过来的事件(登陆成功才传)
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getUserInfo(LoginEvent loginEvent) {
        //更新个人主页信息
        tv_mine_name.setText(loginEvent.getName());
        Picasso.with(mContext).load(loginEvent.getHeadphoto()).into(circleImageView);
        //持久化数据，以便下回再次进入时使用

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 加载视图布局
     */
    private void initView() {
        pull_refresh.setRefreshStyle(PullRefreshLayout.STYLE_SMARTISAN);
        pull_refresh.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkIsLoggedIn(mContext);
                tv_mine_name.setText(userName);
                pull_refresh.setRefreshing(false);
            }
        });
        //用户卡片的点击事件
        card_mine.setOnClickListener(v -> {
            if (is_loggedIn)
                startActivityOnly(mContext, AccountActivity.class);
            else
                startActivityOnly(mContext, LoginActivity.class);
        });

        //功能列表的活动反射数组
        Class[] feature_classes  = new Class[]{
                MyClassActivity.class, MyGroupActivity.class, BookActivity.class, GradeActivity.class,
                ViewHistoryActivity.class, MyCollectionsActivity.class, SettingsActivity.class
        };
        moreFeaturesList = new ArrayList<>();
        moreFeaturesList.add(new TitleAndIconEntity("我的班级", R.drawable.ic_my_class));
        moreFeaturesList.add(new TitleAndIconEntity("小组", R.drawable.ic_my_group));
        moreFeaturesList.add(new TitleAndIconEntity("生活记录", R.drawable.ic_note));
        moreFeaturesList.add(new TitleAndIconEntity("成绩", R.drawable.ic_grade));
        moreFeaturesList.add(new TitleAndIconEntity("浏览历史", R.drawable.ic_view_history));
        moreFeaturesList.add(new TitleAndIconEntity("我的收藏", R.drawable.ic_collection));
        moreFeaturesList.add(new TitleAndIconEntity("设置", R.drawable.ic_setting));
        adapter = new BaseRecycleViewAdapter<TitleAndIconEntity>(getContext(), moreFeaturesList, R.layout.item_features_list) {
            @Override
            public void convert(ViewHolder holder, TitleAndIconEntity titleAndIconEntity, int position) {
                holder.setText(R.id.tv_feature_title,titleAndIconEntity.getTitle());
                holder.setImageResource(R.id.img_feature_icon,titleAndIconEntity.getResId());
            }
        };
        recy_features.addOnItemTouchListener(new BaseRecyclerItemTouchListener(getContext(), new BaseRecyclerItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //通过点击的位置快速启动数组内的反射活动
                startActivityInList(mContext,feature_classes,position);
            }
            @Override
            public void onLongClick(View view, int posotion) {}
        }));
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recy_features.setLayoutManager(manager);
        recy_features.setAdapter(adapter);
        recy_features.setNestedScrollingEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        checkIsLoggedIn(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            checkIsLoggedIn(activity);
        }
    }

    /**
     * 检查本地是否已经登录了帐号
     * */
    private void checkIsLoggedIn(Context context) {
        //获取当前登录的账户名，未登录则显示未登录
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserBaseInfo",Context.MODE_PRIVATE);
        String getUserName = sharedPreferences.getString("name","");
        if (getUserName != null && ! getUserName.equals("")) {
            userName = getUserName;
            is_loggedIn = true;
        }else {
            userName = "未登录";
            is_loggedIn = false;
        }
    }
}