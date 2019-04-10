package site.recomi.studentmanagement.gui.fragments.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.RegisterFaceActivity;
import site.recomi.studentmanagement.gui.fragments.Base.BaseFragment;

public class SignUpFragment extends BaseFragment {
    @BindView(R.id.btn_register_facePic)
    Button btn_register_facePic;
    @BindView(R.id.btn_signup)
    Button btn_signup;
    @BindView(R.id.input_signup_id)
    TextView tv_id;
    @BindView(R.id.input_signup_name)
    TextView tv_name;
    @BindView(R.id.input_signup_phone)
    TextView tv_phone;
    @BindView(R.id.input_signup_password)
    TextView tv_password;
    @BindView(R.id.input_signup_password_again)
    TextView tv_password_again;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.frag_account_signup,container,false);
        mContext = getContext();
        ButterKnife.bind(this, mView);   //绑定ButterKnife
        initView(); //初始化视图
        return mView;
    }

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    //初始化视图布局监听
    private void initView() {
        btn_register_facePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityOnly(mContext, RegisterFaceActivity.class);
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLocalUserBaseInfo();
                if (getActivity() != null)
                    getActivity().onBackPressed();
            }
        });
    }

    private void saveLocalUserBaseInfo() {
        SharedPreferences.Editor editor = mContext.getSharedPreferences("UserBaseInfo", Context.MODE_PRIVATE).edit();
        editor.putString("id",tv_id.getText().toString());
        editor.putString("name",tv_name.getText().toString());
        editor.putString("phone",tv_phone.getText().toString());
//        editor.putString("password",tv_password);
        editor.apply();
    }
}
