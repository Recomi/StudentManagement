package site.recomi.studentmanagement.gui.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.CampusAssociationItem;

public class CampusAssociationAddActivity extends MySwipeBackActivity {
    @BindView(R.id.rv)
    public RecyclerView rv;
    BaseRecycleViewAdapter<CampusAssociationItem> adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_association_add);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        initToolbarDefaultStyle(toolbar);
        toolbar.setTitle("编辑");
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        List<CampusAssociationItem> lists = new ArrayList<>();
        lists.add(new CampusAssociationItem("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2585479940,133847490&fm=26&gp=0.jpg","吉他协会","让音乐代替人生!",false));
        lists.add(new CampusAssociationItem("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1801822150,3015823651&fm=26&gp=0.jpg","学生会","校园核心社团!",false));
        lists.add(new CampusAssociationItem("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1855551208,1635420690&fm=26&gp=0.jpg","羽毛协会","羽毛之巅!",false));
        lists.add(new CampusAssociationItem("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2028409111,3687555525&fm=26&gp=0.jpg","乒乓球协会","快乐运动!",false));


        adapter = new BaseRecycleViewAdapter<CampusAssociationItem>(this ,lists , R.layout.recycler_view_item_1) {
            @Override
            public void convert(ViewHolder holder, CampusAssociationItem campusAssociationItem, int position) {
                    holder.setText(R.id.textView , campusAssociationItem.getName());
                    holder.setText(R.id.textView2 , campusAssociationItem.getSubTitle());
                    holder.setImageOnlineResource(R.id.imageView2 , campusAssociationItem.getImgSrc());



            }
        };

        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_campus_association_add , menu);
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
