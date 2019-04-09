package site.recomi.studentmanagement.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.gyf.barlibrary.ImmersionBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import site.recomi.studentmanagement.entity.UserSharingPost;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseMultiItemTypeRecyclerViewAdapter;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecyclerViewOnScrollListener;
import site.recomi.studentmanagement.gui.adapter.Delegetes.SharingPostDelegete;
import site.recomi.studentmanagement.gui.adapter.MultiItemTypeSupport;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.BookNoticeEntitiy;
import site.recomi.studentmanagement.other.CampusAssociationItem;

public class CampusAssociationActivity extends MySwipeBackActivity implements View.OnClickListener {
    @BindView(R.id.rv)
    public RecyclerView rv;
    BaseMultiItemTypeRecyclerViewAdapter<UserSharingPost> adapter;
    MultiItemTypeSupport multiItemTypeSupport;
    List<UserSharingPost> posts;              //待显示的数据列表
    SwipeRefreshLayout layout;           //刷新按钮

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
        initPullRefreshLayout();
        initNewestData();

    }

    /*
     * 初始化刷新按钮
     * */
    private void initPullRefreshLayout(){
        layout = (SwipeRefreshLayout)findViewById(R.id.pull_campus);

        //首次进入时自动刷新数据
        layout.setRefreshing(true);
        layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getOnlineData();
                layout.setRefreshing(false);
            }
        } , 1500);

        //监听
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getOnlineData();
                        layout.setRefreshing(false);
                    }
                } , 3000);
            }
        });
    }

    /*
     * 发起网络请求,获取服务器数据
     * */
    private void getOnlineData(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type" , "campusAssociation")
                .build();
        Request request = new Request.Builder()
                .url(Constant.MAIN_PHP)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //针对异常情况处理
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                posts.clear();
                try {
                    JSONArray jsonObject = new JSONArray(responseData);

                    for (int i = 0; i < jsonObject.length(); i++){
                        String name = jsonObject.getJSONObject(i).getString("name");
                        String time = jsonObject.getJSONObject(i).getString("time");
                        String content = jsonObject.getJSONObject(i).getString("content");
                        String headIconUrl = jsonObject.getJSONObject(i).getString("headIconUrl");
                        int like = jsonObject.getJSONObject(i).getInt("like");
                        int collect = jsonObject.getJSONObject(i).getInt("collect");
                        int share = jsonObject.getJSONObject(i).getInt("share");
                        posts.add(new UserSharingPost(name, time, content, headIconUrl, like, collect, share));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                Log.d("data", "服务器返回的数据: " + responseData);
            }
        });
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
        posts = new ArrayList<>();
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
                    holder.setImageOnlineResource(R.id.img_sharing_headicon, userSharingPost.getHeadIconUrl());
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
