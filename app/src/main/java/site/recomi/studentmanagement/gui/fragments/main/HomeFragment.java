package site.recomi.studentmanagement.gui.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.CampusAssociationActivity;
import site.recomi.studentmanagement.gui.activities.ClassScheduleActivity;
import site.recomi.studentmanagement.gui.activities.MainActivity;
import site.recomi.studentmanagement.gui.adapter.PagerViewAdapter;

public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    List<String> bitmaps = new ArrayList<>();
    ViewPager vp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container , false);

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

        initMarqueeView(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        mainActivity= (MainActivity)getActivity();
        mainActivity.setTitle("首页");
        mainActivity.setCurrentFragmentLocation(1);

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
