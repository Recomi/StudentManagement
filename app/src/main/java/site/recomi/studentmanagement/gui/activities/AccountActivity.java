package site.recomi.studentmanagement.gui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.activities.mineFeatures.MyClassActivity;

public class AccountActivity extends MySwipeBackActivity {
    @BindView(R.id.btn_logOut)
    Button btn_logOut;
    @BindView(R.id.collaps)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(AccountActivity.this);
        mContext = AccountActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        setToolbarPaddingTop(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_dark_medium);
        setTitle("我的个人信息");
        setSupportActionBar(toolbar);
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mCollapsingToolbarLayout.setTitle("我的个人信息");
        initView();
    }

    private void initView() {
        btn_logOut.setOnClickListener(v -> {
            log_out();
            onBackPressed();
        });
    }

    private void log_out() {
        //获取当前登录的账户名，未登录则显示未登录
        SharedPreferences.Editor editor =getSharedPreferences("account", Context.MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();
        toastLongMessage(mContext,"成功退出当前账号");
    }
}
