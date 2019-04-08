package site.recomi.studentmanagement.gui.activities.mineFeatures;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;

public class MyGroupActivity extends MySwipeBackActivity {
    @BindView(R.id.collaps_group)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_group);
        ButterKnife.bind(MyGroupActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        toolbar.setNavigationIcon(R.drawable.ic_back_dark_medium);
        setSupportActionBar(toolbar);
        setTitle("我的小组");
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mCollapsingToolbarLayout.setTitle("我的班级");
    }
}
