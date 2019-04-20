package site.recomi.studentmanagement.gui.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
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
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.BookNoticeEntitiy;
import site.recomi.studentmanagement.other.RecruitmentInformationEntitiy;

import static org.litepal.LitePalApplication.getContext;

public class JobWantedActivity extends MySwipeBackActivity {
    @BindView(R.id.recruit_city)
    public Spinner recruit_city;
    @BindView(R.id.job_position)
    public Spinner job_position;

    SearchView.SearchAutoComplete mSearchAutoComplete;
    SearchView searchView;
    BaseRecycleViewAdapter<RecruitmentInformationEntitiy> adapter;
    List<RecruitmentInformationEntitiy> lists;              //待显示的数据列表
    SwipeRefreshLayout layout;           //刷新按钮
    String jobLocation = "all" , jobCategory = "all";     //待查询数据库的岗位所在地,岗位类别
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_wanted);
        ButterKnife.bind(this);
        mContext = JobWantedActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        setToolbarPaddingTop(toolbar);//设置top外边距为状态栏高度
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        setTitle("校园求职");
        setSupportActionBar(toolbar);

        initRecyView();
        initSwipeRefreshLayout();
        initSpinner();

    }

    private void initSpinner() {
        recruit_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == 0){
                    jobLocation = "all";
                    refreshOnlineData();
                }else {
                    jobLocation = String.valueOf(parent.getItemAtPosition(position).toString());
                    refreshOnlineData();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        job_position.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                if (position == 0){
                    jobCategory = "all";
                    refreshOnlineData();
                }else {
                    jobCategory = String.valueOf(parent.getItemAtPosition(position).toString());
                    refreshOnlineData();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book , menu);
        //获取SearchView对象
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        //开启提交按钮
        searchView.setSubmitButtonEnabled(true);
        //设置文字提示
        searchView.setQueryHint("查找图书");
        searchView.setMaxWidth(1000);
        //监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //提交按钮的点击事件
            @Override
            public boolean onQueryTextSubmit(String query) {
                swipeRefreshLayout.setRefreshing(true);
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getOnlineDataSearch(query);
                        // 刷新3秒完成
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
                return true;
            }

            //当输入框内容改变的时候回调
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        //设置输入框提示文字样式
        mSearchAutoComplete = searchView.findViewById(R.id.search_src_text);
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.darker_gray));             //设置提示文字颜色
        mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.background_dark));                 //设置内容文字颜色
        return true;
    }

    /*
     * 初始化下拉刷新
     * */
    private void initSwipeRefreshLayout(){
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(true);
        refreshOnlineData();       //获取网络数据，并显示
        //监听，更新数据
        swipeRefreshLayout.setOnRefreshListener(this::refreshOnlineData);
    }

    /*
     * 初始化列表
     * */
    private void initRecyView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        lists = new ArrayList<>();
        adapter = new BaseRecycleViewAdapter<RecruitmentInformationEntitiy>(getContext() ,lists , R.layout.recycler_view_item_3) {
            @Override
            public void convert(ViewHolder holder, RecruitmentInformationEntitiy recruitmentInformationEntitiy, int position) {
                if (mList.size() == 0)
                    return;
                holder.setText(R.id.jobName , recruitmentInformationEntitiy.getJobName());
                holder.setText(R.id.company , recruitmentInformationEntitiy.getCompany());
                holder.setText(R.id.location , recruitmentInformationEntitiy.getLocation());
                holder.setText(R.id.sum , recruitmentInformationEntitiy.getSum());
                holder.setText(R.id.date , recruitmentInformationEntitiy.getDate());
                holder.setText(R.id.salary , recruitmentInformationEntitiy.getSalary());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    /*
     * 发起网络请求,获取服务器数据
     * */
    private void refreshOnlineData(){
        swipeRefreshLayout.setRefreshing(true);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type" , "recruitmentInformation")
                .add("jobLocation" , jobLocation)         //岗位所在地,默认为all
                .add("jobCategory" , jobCategory)             //岗位类型,默认为all
                .build();
        Request request = new Request.Builder()
                .url(Constant.MAIN_PHP)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //针对异常情况处理
                runOnUiThread(() ->
                        toastLongMessage(mContext,"获取信息失败，请检查网络是否通畅"));
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                lists.clear();
                try {
                    JSONArray jsonObject = new JSONArray(responseData);
                    for (int i = 0; i < jsonObject.length(); i++){
                        String jobName = jsonObject.getJSONObject(i).getString("jobName");
                        String company = jsonObject.getJSONObject(i).getString("company");
                        String location = jsonObject.getJSONObject(i).getString("location");
                        String sum = jsonObject.getJSONObject(i).getString("sum");
                        String date = jsonObject.getJSONObject(i).getString("date");
                        String salary = jsonObject.getJSONObject(i).getString("salary");
                        String category = jsonObject.getJSONObject(i).getString("category");
                        lists.add(new RecruitmentInformationEntitiy(jobName,company,location,sum,date,salary,category));
                    }
                } catch (JSONException e) {
                    swipeRefreshLayout.setRefreshing(false);
                    runOnUiThread(() ->
                            toastLongMessage(mContext,"当前暂无该类别的职位"));
                }
                runOnUiThread(() -> {
                    adapter.notifyDataSetChanged(); //刷新界面数据
                    swipeRefreshLayout.setRefreshing(false); //停止下拉刷新
                });
            }
        });
    }


    /*
     * 发起网络请求,获取服务器数据,此方式为搜索特定名称的职业
     * */
    private void getOnlineDataSearch(String jobName){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type" , "recruitmentInformation")
                .add("jobName" , jobName)
                .build();
        Request request = new Request.Builder()
                .url(Constant.MAIN_PHP)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //针对异常情况处理
                Log.d("xxxxx", "onFailure: "+ e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                lists.clear();
                try {
                    JSONArray jsonObject = new JSONArray(responseData);
                    for (int i = 0; i < jsonObject.length(); i++){
                        String jobName = jsonObject.getJSONObject(i).getString("jobName");
                        String company = jsonObject.getJSONObject(i).getString("company");
                        String location = jsonObject.getJSONObject(i).getString("location");
                        String sum = jsonObject.getJSONObject(i).getString("sum");
                        String date = jsonObject.getJSONObject(i).getString("date");
                        String salary = jsonObject.getJSONObject(i).getString("salary");
                        String category = jsonObject.getJSONObject(i).getString("category");
                        lists.add(new RecruitmentInformationEntitiy(jobName,company,location,sum,date,salary,category));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> adapter.notifyDataSetChanged());
                Log.d("xxxxxxx", "服务器返回的数据: " + responseData);
            }
        });
    }

}
