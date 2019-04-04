package site.recomi.studentmanagement.gui.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.UserSharingPost;
import site.recomi.studentmanagement.gui.activities.CampusAssociationActivity;
import site.recomi.studentmanagement.gui.activities.ClassScheduleActivity;
import site.recomi.studentmanagement.gui.activities.MainActivity;
import site.recomi.studentmanagement.gui.adapter.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.PagerViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;

public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    List<String> bitmaps = new ArrayList<>();
    ViewPager vp;

    @BindView(R.id.refresh_home)
    PullRefreshLayout refresh_home;
    @BindView(R.id.recy_main_newest)
    RecyclerView recy;
    BaseRecycleViewAdapter<UserSharingPost> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container , false);
        ButterKnife.bind(this, view);   //绑定ButterKnife

        LinearLayout classShedule = view.findViewById(R.id.classChedule);
        classShedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , ClassScheduleActivity.class);
                getActivity().startActivity(intent);
            }
        });

        LinearLayout campusAssociation = view.findViewById(R.id.campusAssociation);
        campusAssociation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , CampusAssociationActivity.class);
                getActivity().startActivity(intent);
            }
        });

        initNewestData();
        initMarqueeView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 初始化最近信息的数据
     * */
    private void initNewestData() {
        List<UserSharingPost> posts = new ArrayList<>();
        posts.add(new UserSharingPost("白上吹血","12点22分","今天天气不错啊","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("index","12点20分","嗯........","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("are you ok","12点10分","你们很棒哦","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("ddd","11点55分","你们还好么","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("学生会会长","11点40分","救命啊","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("四宫辉夜","11点35分","你还真是可爱呢","http://recomi.site/license_pic/jpg"));
        adapter = new BaseRecycleViewAdapter<UserSharingPost>(getContext(),posts,R.layout.item_user_sharing_post) {
            @Override
            public void convert(ViewHolder holder, UserSharingPost userSharingPost) {
                holder.setText(R.id.tv_name,userSharingPost.getName());
                holder.setText(R.id.tv_post_time,userSharingPost.getTime());
                holder.setText(R.id.tv_sharing_content,userSharingPost.getContent());
//                holder.setText(R.id.tv_name,userSharingPost.getHeadIconUrl());
            }
        };
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recy.setLayoutManager(manager);
        recy.setAdapter(adapter);
    }


    //初始化滚动信息栏
    private void initMarqueeView(View view){
        MarqueeView marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("11111111111111");
        info.add("22222222222222");
        info.add("33333333333333");
        info.add("44444444444444");
        info.add("55555555555555");
        info.add("66666666666666");
        marqueeView.startWithList(info);
        marqueeView.startWithList(info, R.anim.anim_bottom_in, R.anim.anim_top_out);    //动画
        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(mainActivity.getApplicationContext(), String.valueOf(position) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        vp = getActivity().findViewById(R.id.vp);
        bitmaps.add("http://img0.imgtn.bdimg.com/it/u=1899561195,3106332361&fm=26&gp=0.jpg");
        bitmaps.add("http://www.luodingpoly.cn/zs/themes/zs/images/banner1.jpg");
        bitmaps.add("http://www.luodingpoly.cn/zs/themes/zs/images/banner3.jpg");
        vp.setAdapter(new PagerViewAdapter(getContext() ,bitmaps));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
