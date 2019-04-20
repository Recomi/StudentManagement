package site.recomi.studentmanagement.gui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.BookEntitiy;

public class BookAppointmentActivity extends MySwipeBackActivity {
    RecyclerView rv;
    BaseRecycleViewAdapter<BookEntitiy> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        setToolbarPaddingTop(toolbar);
        toolbar.setTitle("我的预约");
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initView();
    }

    private void initView(){


        rv = (RecyclerView) findViewById(R.id.rv);
        List<BookEntitiy> lists = new ArrayList<>();
        lists.add(new BookEntitiy(R.drawable.test3, "沉思录", "优瓦尔" , "东方出版社"));
        lists.add(new BookEntitiy(R.drawable.test4, "给大忙人看的佛法", "顾城" , "东方出版社"));
        lists.add(new BookEntitiy(R.drawable.test5, "鲨权力", "小小" , "京东出版社"));
        lists.add(new BookEntitiy(R.drawable.test6, "甄嬛著稿", "嚄嚄" , "北交大出版社"));



        adapter = new BaseRecycleViewAdapter<BookEntitiy>(this ,lists , R.layout.recycler_view_item_2) {
            @Override
            public void convert(ViewHolder holder, BookEntitiy bookEntitiy, int position) {
                holder.setText(R.id.name , bookEntitiy.getName());
                holder.setText(R.id.author , bookEntitiy.getAuthor());
                holder.setText(R.id.publishingHouse , bookEntitiy.getPublishingHouse());
                holder.setImageResource(R.id.cover , bookEntitiy.getBookID());
            }
        };

        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }
}
