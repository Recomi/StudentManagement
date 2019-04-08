package site.recomi.studentmanagement.gui.fragments.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.TitleAndIconEntity;
import site.recomi.studentmanagement.gui.activities.BookActivity;
import site.recomi.studentmanagement.gui.activities.mineFeatures.SettingsActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.gui.fragments.Base.BaseFragment;
import site.recomi.studentmanagement.gui.listenner.BaseRecyclerItemTouchListener;

public class MineFragment extends BaseFragment {
    @BindView(R.id.recy_mine_features)
    RecyclerView recy_features;

    View mView;
    Context mContext;
    ViewPager vp;
    BaseRecycleViewAdapter<TitleAndIconEntity> adapter;
    List<TitleAndIconEntity> moreFeaturesList;

    int testData = 0;
    int testDataTop = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mine, container, false);
        mContext = mView.getContext();
        ButterKnife.bind(this, mView);   //绑定ButterKnife

        initView();                 //加载视图布局
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 加载视图布局
     */
    private void initView() {
        //功能列表的活动反射数组
        Class[] feature_classes  = new Class[]{
                BookActivity.class, BookActivity.class, BookActivity.class, BookActivity.class,
                BookActivity.class, BookActivity.class, SettingsActivity.class
        };
        moreFeaturesList = new ArrayList<>();
        moreFeaturesList.add(new TitleAndIconEntity("我的班级", R.drawable.ic_cricle));
        moreFeaturesList.add(new TitleAndIconEntity("小组", R.drawable.ic_associations));
        moreFeaturesList.add(new TitleAndIconEntity("笔记", R.drawable.ic_library));
        moreFeaturesList.add(new TitleAndIconEntity("成绩", R.drawable.ic_grade));
        moreFeaturesList.add(new TitleAndIconEntity("浏览历史", R.drawable.ic_cricle));
        moreFeaturesList.add(new TitleAndIconEntity("我的收藏", R.drawable.ic_cricle));
        moreFeaturesList.add(new TitleAndIconEntity("设置", R.drawable.ic_cricle));
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
    }

}