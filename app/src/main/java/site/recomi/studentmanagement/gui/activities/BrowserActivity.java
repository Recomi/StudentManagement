package site.recomi.studentmanagement.gui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import site.recomi.studentmanagement.R;

public class BrowserActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        webView = findViewById(R.id.webView);
        webView.setInitialScale(100);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        String site = intent.getStringExtra("site");
        webView.loadUrl(site);


    }
}
