package site.recomi.studentmanagement.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.BaseActivity;
import site.recomi.studentmanagement.gui.adapter.MainFragmentPagerAdapter;
import site.recomi.studentmanagement.gui.fragments.main.HomeFragment;
import site.recomi.studentmanagement.gui.fragments.main.MessageFragment;
import site.recomi.studentmanagement.gui.fragments.main.NoteFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int currentFragmentLocation = 1;
    int notesReturnCode = 1;

    @BindView(R.id.vp_main)
    ViewPager vp_main;

    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        bindingFragment(new HomeFragment());
        initBottomNavigationView();

        // click to start LoginActivity
        LinearLayout account = navigationView.getHeaderView(0).findViewById(R.id.click_account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        fragments.add(new HomeFragment());
        fragments.add(new NoteFragment());
        fragments.add(new MessageFragment());
        vp_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            case R.id.action_add:
               Intent intent = new Intent(this , WriteNoteActivity.class);
               startActivityForResult(intent,notesReturnCode);
               return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //改变菜单文件,由当前碎片决定
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        switch (currentFragmentLocation){
            case 1:
                getMenuInflater().inflate(R.menu.main_main, menu);
                break;
            case 2:
                getMenuInflater().inflate(R.menu.main_note, menu);
                break;
            case 3:
                getMenuInflater().inflate(R.menu.main_message, menu);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //设置当前的碎片位置,并根据位置信息让活动做出相应的改变
    public void setCurrentFragmentLocation(int currentFragmentLocation) {
        this.currentFragmentLocation = currentFragmentLocation;
        supportInvalidateOptionsMenu(); //通知系统更新菜单
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //绑定主界面三个按钮相应的碎片
    public void bindingFragment(Fragment fragment){
            FragmentManager fragmentManager = getSupportFragmentManager();
            //碎片事务
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            //replace方法用来添加或者修改碎片
            transaction.replace(R.id.container_layout , fragment);
            //commit提交事务
            transaction.commit();
    }

    //初始化底部导航栏
    private void initBottomNavigationView(){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.action_main:
                    vp_main.setCurrentItem(0);
                    return true;
                case R.id.action_note:
                    vp_main.setCurrentItem(1);
                    return true;
                case R.id.action_message:
                    vp_main.setCurrentItem(2);
                    return true;
            }
            return false;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1 :
                if (resultCode == RESULT_OK){
                    setCurrentFragmentLocation(2);
                }
                break;
            default:
                break;
        }
    }
}
