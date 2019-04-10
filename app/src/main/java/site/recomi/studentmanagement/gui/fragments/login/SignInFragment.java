package site.recomi.studentmanagement.gui.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
import site.recomi.studentmanagement.gui.activities.AccountActivity;
import site.recomi.studentmanagement.gui.activities.MainActivity;
import site.recomi.studentmanagement.other.LoginEvent;

public class SignInFragment extends Fragment {
    @BindView(R.id.button_login_login)
    Button login;
    @BindView(R.id.input_account)
    AutoCompleteTextView input_account;
    @BindView(R.id.input_password)
    AutoCompleteTextView input_password;

    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.frag_account_signin,container,false);
        mContext = view.getContext();
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    private void init() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getOnlineData();
            }
        });
    }

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    /*
     * 发起网络请求,获取服务器数据
     * */
    private void getOnlineData(){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("type" , "verifyUser")
                .add("id",input_account.getText().toString())
                .add("password", input_password.getText().toString())
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
                Log.e("123456", "onResponse: " + responseData );
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    if (jsonObject.getString("result").equals("1")){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(mContext, MainActivity.class);
                                try {
                                    EventBus.getDefault().post(new LoginEvent(input_account.getText().toString(),jsonObject.getString("name") , jsonObject.getString("headphoto")));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                startActivity(intent);

                            }
                        });
                    }else if (jsonObject.getString("result").equals("0")){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "检查账号密码", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "未知错误,检查网络", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
