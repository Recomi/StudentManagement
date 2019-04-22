package site.recomi.studentmanagement.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;

public class BrowserActivity extends MySwipeBackActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);
        mContext = BrowserActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        initToolbarDefaultStyle(toolbar);
        setTitle("正文");
        setSupportActionBar(toolbar);

        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(false);           //关闭缩放
        settings.setBuiltInZoomControls(false);   //关闭内建缩放控制
        settings.setUseWideViewPort(true);//不显示webview缩放按钮

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //开启JavaScrip
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        //设置 缓存模式
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 开启 DOM storage API 功能
        settings.setDomStorageEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String site = intent.getStringExtra("site");
        webView.loadUrl(site);
    }
}
