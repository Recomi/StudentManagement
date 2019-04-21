package site.recomi.studentmanagement.gui.activities.mineFeatures;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;

public class MyCollectionsActivity extends MySwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collections);
        ButterKnife.bind(MyCollectionsActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        initToolbarDefaultStyle(toolbar);
        setSupportActionBar(toolbar);
        setTitle("我的收藏");
    }
}
