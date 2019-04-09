package site.recomi.studentmanagement.gui.fragments.book;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import site.recomi.studentmanagement.Constant;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.BookNoticeEntitiy;

public class NoticeFragment extends Fragment {
    View mview;
    BaseRecycleViewAdapter<BookNoticeEntitiy> adapter;
    List<BookNoticeEntitiy> lists;              //待显示的数据列表
    PullRefreshLayout layout;           //刷新按钮

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_notice, container, false);
        initRecyView();
        initPullRefreshLayout();
        return mview;
    }

    /*
    * 初始化刷新按钮
    * */
    private void initPullRefreshLayout(){
        layout = (PullRefreshLayout)mview.findViewById(R.id.pullRefreshLayout);
        layout.setRefreshing(true);
        layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                getOnlineData();
                // 刷新3秒完成
                layout.setRefreshing(false);
            }
        }, 3000);

        //监听
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getOnlineData();
                        // 刷新3秒完成
                        layout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    /*
    * 初始化列表
    * */
    private void initRecyView(){
        RecyclerView recyclerView = mview.findViewById(R.id.recyclerView);
        lists = new ArrayList<>();
        adapter = new BaseRecycleViewAdapter<BookNoticeEntitiy>(getContext() ,lists , R.layout.recycler_view_item) {
            @Override
            public void convert(ViewHolder holder, BookNoticeEntitiy bookNoticeEntitiy, int position) {
                holder.setText(R.id.title , bookNoticeEntitiy.getTitle());
                holder.setText(R.id.content , bookNoticeEntitiy.getContent());
                holder.setText(R.id.date , bookNoticeEntitiy.getDate());
                holder.setImageOnlineResource(R.id.imgSrc, bookNoticeEntitiy.getSrcImage());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    /*
    * 发起网络请求,获取服务器数据
    * */
    private void getOnlineData(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type" , "bookNotice")
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
                lists.clear();
                try {
                    JSONArray jsonObject = new JSONArray(responseData);
                    for (int i = 0; i < jsonObject.length(); i++){
                        String title = jsonObject.getJSONObject(i).getString("title");
                        String content = jsonObject.getJSONObject(i).getString("content");
                        String date = jsonObject.getJSONObject(i).getString("date");
                        String img = jsonObject.getJSONObject(i).getString("img");
                        lists.add(new BookNoticeEntitiy(title, content, date, img));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
                Log.d("data", "服务器返回的数据: " + responseData);
            }
        });
    }

}
