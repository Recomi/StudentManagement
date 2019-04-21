package site.recomi.studentmanagement.gui.activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.fragments.book.RecommendFragment;
import site.recomi.studentmanagement.gui.fragments.book.NoticeFragment;
import site.recomi.studentmanagement.gui.fragments.book.PersonalFragment;

public class BookActivity extends MySwipeBackActivity {
    SearchView.SearchAutoComplete mSearchAutoComplete;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();


    }

    private void initView(){
        //状态栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        toolbar.setTitle("图书馆");
        initToolbarDefaultStyle(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("馆荐"));
        tabLayout.addTab(tabLayout.newTab().setText("公告"));
        tabLayout.addTab(tabLayout.newTab().setText("个人中心"));
        tabLayout.setTabRippleColor(ColorStateList.valueOf(Color.parseColor("#00000000")));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //ViewPage
        final List<Fragment> listFragment = new ArrayList<>();
        listFragment.add(new RecommendFragment());
        listFragment.add(new NoticeFragment());
        listFragment.add(new PersonalFragment());
        ViewPager vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            String[] tabNames = {"馆荐" , "公告" , "个人中心"};
            @Override
            public Fragment getItem(int i) {
                return listFragment.get(i);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabNames[position];
            }
        });
        tabLayout.setupWithViewPager(vp);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_book , menu);

        //获取SearchView对象
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();


        //开启提交按钮
        searchView.setSubmitButtonEnabled(true);
        //设置文字提示
        searchView.setQueryHint("查找图书");
        searchView.setMaxWidth(1000);

        //监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //提交按钮的点击事件
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(BookActivity.this, query, Toast.LENGTH_SHORT).show();
                return true;
            }

            //当输入框内容改变的时候回调
            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(BookActivity.this, newText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //设置输入框提示文字样式
        mSearchAutoComplete = searchView.findViewById(R.id.search_src_text);
        mSearchAutoComplete.setHintTextColor(getResources().getColor(android.R.color.darker_gray));             //设置提示文字颜色
        mSearchAutoComplete.setTextColor(getResources().getColor(android.R.color.background_dark));                 //设置内容文字颜色
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.finish:
                Toast.makeText(this , "finish" , Toast.LENGTH_LONG).show();
                break;
            case android.R.id.home:// back button
                this.finish();
                break;
        }
        return true;
    }






}
