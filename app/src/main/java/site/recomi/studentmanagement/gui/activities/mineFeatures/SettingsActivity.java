package site.recomi.studentmanagement.gui.activities.mineFeatures;

import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;

public class SettingsActivity extends MySwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        initToolbarDefaultStyle(toolbar);
        // 菜单点击事件
//        toolbar.setOnMenuItemClickListener(this);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        setTitle(R.string.action_settings);

        initView();    //初始化视图布局
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_settings,new SettingsFragment())
                .commit();
    }

    //初始化视图布局
    private void initView() {

    }

    public static class SettingsFragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }

    }
}
