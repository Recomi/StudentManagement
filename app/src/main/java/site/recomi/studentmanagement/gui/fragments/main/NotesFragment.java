package site.recomi.studentmanagement.gui.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baoyz.widget.PullRefreshLayout;


import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.Diary;
import site.recomi.studentmanagement.gui.activities.MainActivity;
import site.recomi.studentmanagement.gui.activities.notes.NewDiaryActivity;
import site.recomi.studentmanagement.gui.activities.notes.ViewDiaryActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.gui.listenner.BaseRecyclerItemTouchListener;

public class NotesFragment extends Fragment {
    ArrayList<Diary> diaryArrayList;             // Diary集合
    private View view;
    private Context mContext;
    private RecyclerView recy_diaries;           // RecyclerView
    private BaseRecycleViewAdapter adapter;      // 适配器
    private PullRefreshLayout refreshLayout;     // 下拉刷新
//    private PullRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_diary_list,container,false);
        mContext = view.getContext();
        initView();
        return view;
    }
    // 加载view
    private void initView(){
        //浮动按钮
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_main);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // NewDiaryActivity
                Intent intent = new Intent(getActivity(), NewDiaryActivity.class);
                startActivityForResult(intent,233);
            }
        });

        diaryArrayList = new ArrayList<>();
        recy_diaries= (RecyclerView) view.findViewById(R.id.recy_diary_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL,false);
        adapter = new BaseRecycleViewAdapter<Diary>(mContext,diaryArrayList,R.layout.item_diary_mode_normal) {
            @Override
            public void convert(ViewHolder holder, Diary diary, int position) {
                holder.setText(R.id.text_day_normal,diary.getDay());
                holder.setText(R.id.text_monAndweek_normal,diary.getMonthAndWeek());
                holder.setText(R.id.text_time_normal,diary.getTime());
                if (diary.getTitle().equals("")){
                    holder.setTextSize(R.id.text_title_normal,Float.parseFloat("0"));
                }
                holder.setText(R.id.text_title_normal,diary.getTitle());
                if (diary.getContent().equals("")){
                    holder.setTextSize(R.id.text_content_normal,Float.parseFloat("0"));
                }
                holder.setText(R.id.text_content_normal,diary.getContent());
                holder.setText(R.id.text_weather_normal,diary.getWeather());
                holder.setText(R.id.text_address_normal,diary.getAddress());
                if (diary.getImgRes() != -1) {
                    holder.setImageResource(R.id.image1_normal, diary.getImgRes());
                    holder.setImageHeight(R.id.image1_normal, 10);
                }
            }

        };
        recy_diaries.setLayoutManager(layoutManager);
        recy_diaries.setAdapter(adapter);
        recy_diaries.addOnItemTouchListener(new BaseRecyclerItemTouchListener(getContext(), new BaseRecyclerItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ViewDiaryActivity.class);
                intent.putExtra("id",position);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int posotion) {

            }
        }));
        refreshLayout = (PullRefreshLayout) view.findViewById(R.id.refresh_main);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new UpdateRecy().start();
            }
        });
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_WATER_DROP);
        updateDiaries();
    }
    /**
     * 更新Diary-RecyclerView
     * */
    public void updateDiaries(){
        List<Diary> diaries = LitePal.findAll(Diary.class);
        Collections.reverse(diaries);
        diaryArrayList.clear();
        diaryArrayList.addAll(diaries);
        adapter.notifyDataSetChanged();
    }

    private void setConvert(ViewHolder holder){

    }

    @Override
    public void onStart() {
        super.onStart();
        new UpdateRecy().start();
    }
    /**
     * 更新Diary RecyclerView的线程
     * */
    class UpdateRecy extends Thread {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    List<Diary> diaries = LitePal.findAll(Diary.class);
                    Collections.reverse(diaries);
                    diaryArrayList.clear();
                    diaryArrayList.addAll(diaries);
                    // 停止刷新
                    refreshLayout.setRefreshing(false);
                    // 刷新recycleView
                    adapter.notifyDataSetChanged();
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }
}
