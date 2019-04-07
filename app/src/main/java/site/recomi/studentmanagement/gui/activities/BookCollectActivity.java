package site.recomi.studentmanagement.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;

public class BookCollectActivity extends MySwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_collect);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        toolbar.setTitle("我的收藏");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
