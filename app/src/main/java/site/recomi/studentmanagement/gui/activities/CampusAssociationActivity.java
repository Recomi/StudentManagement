package site.recomi.studentmanagement.gui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.entity.UserSharingPost;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.CampusAssociationItem;

public class CampusAssociationActivity extends MySwipeBackActivity implements View.OnClickListener {
    @BindView(R.id.rv)
    public RecyclerView rv;
    BaseRecycleViewAdapter<UserSharingPost> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_association);
        ButterKnife.bind(this);
        ImmersionBar.with(this).init();     //沉浸状态栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




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

    private void initNewestData() {
        List<UserSharingPost> posts = new ArrayList<>();
        posts.add(new UserSharingPost("白上吹血","12点22分","今天天气不错啊","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("index","12点20分","嗯........","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("are you ok","12点10分","你们很棒哦","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("ddd","11点55分","你们还好么","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("学生会会长","11点40分","救命啊","http://recomi.site/license_pic/jpg"));
        posts.add(new UserSharingPost("四宫辉夜","11点35分","你还真是可爱呢","http://recomi.site/license_pic/jpg"));
        adapter = new BaseRecycleViewAdapter<UserSharingPost>(this,posts,R.layout.item_user_sharing_post) {
            @Override
            public void convert(ViewHolder holder, UserSharingPost userSharingPost, int position) {
                holder.setText(R.id.tv_name,userSharingPost.getName());
                holder.setText(R.id.tv_post_time,userSharingPost.getTime());
                holder.setText(R.id.tv_sharing_content,userSharingPost.getContent());
//                holder.setText(R.id.tv_name,userSharingPost.getHeadIconUrl());
            }
        };
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
