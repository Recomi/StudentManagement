package site.recomi.studentmanagement.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.UserSharingPost;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseMultiItemTypeRecyclerViewAdapter;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecyclerViewOnScrollListener;
import site.recomi.studentmanagement.gui.adapter.Delegetes.SharingPostDelegete;
import site.recomi.studentmanagement.gui.adapter.MultiItemTypeSupport;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;

public class CampusAssociationActivity extends MySwipeBackActivity implements View.OnClickListener {
    @BindView(R.id.rv)
    public RecyclerView rv;
    BaseMultiItemTypeRecyclerViewAdapter<UserSharingPost> adapter;
    MultiItemTypeSupport multiItemTypeSupport;

    int testData = 0;
    int testDataTop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_association);
        ButterKnife.bind(this);
//        ImmersionBar.with(this).init();     //沉浸状态栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setStatusBarTextColor(LIGHT_STATUS_FONT);  //状态栏文字颜色，白色

//        SharedPreferences sharedPreferences = getSharedPreferences("card", MODE_PRIVATE);

        initView();
        initNewestData();

    }

    private void initView(){
        //添加按钮
        CardView cardView = (CardView) findViewById(R.id.plus);
        cardView.setOnClickListener(this);

        //返回按钮
        ImageButton returnButton = (ImageButton) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(this);


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
        adapter = new BaseMultiItemTypeRecyclerViewAdapter<UserSharingPost>(this,posts,new SharingPostDelegete()) {
            @Override
            public void convert(ViewHolder holder, UserSharingPost userSharingPost, int position) {
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
        rv.addOnScrollListener(new BaseRecyclerViewOnScrollListener() {
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
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.plus:
                Intent intent = new Intent(getApplicationContext() , CampusAssociationAddActivity.class);
                startActivity(intent);
                break;
            case R.id.returnButton:
                finish();
                break;

        }
    }
}
