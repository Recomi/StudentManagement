package site.recomi.studentmanagement.gui.activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.BookDetailViewPage;
import site.recomi.studentmanagement.gui.fragments.book.RecommendFragment;
import site.recomi.studentmanagement.gui.fragments.book.NoticeFragment;
import site.recomi.studentmanagement.gui.fragments.book.PersonalFragment;

public class BookDetailActivity extends MySwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        initView();
    }

    private void initView(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        toolbar.setTitle("图书详细");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);



        //TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("馆藏信息"));
        tabLayout.addTab(tabLayout.newTab().setText("内容简介"));
        tabLayout.addTab(tabLayout.newTab().setText("图片欣赏"));
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
        ViewPager vp = (ViewPager) findViewById(R.id.viewPage);
        List<Integer> integers  = new ArrayList<>();
        integers.add(R.layout.book_detail_message);
        integers.add(R.layout.book_detail_content);
        integers.add(R.layout.book_detail_img);
        BookDetailViewPage bookDetailViewPage = new BookDetailViewPage(this , integers);
        vp.setAdapter(bookDetailViewPage);
        tabLayout.setupWithViewPager(vp);

    }
}
