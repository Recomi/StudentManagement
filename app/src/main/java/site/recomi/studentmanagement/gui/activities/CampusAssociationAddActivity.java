package site.recomi.studentmanagement.gui.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.base.MySwipeBackActivity;
import site.recomi.studentmanagement.gui.adapter.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.CampusAssociationItem;

public class CampusAssociationAddActivity extends MySwipeBackActivity {
    @BindView(R.id.rv)
    public RecyclerView rv;
    BaseRecycleViewAdapter<CampusAssociationItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_association_add);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_base);
        toolbar.setTitle("编辑");
//        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);


        List<CampusAssociationItem> lists = new ArrayList<>();
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));


        adapter = new BaseRecycleViewAdapter<CampusAssociationItem>(this ,lists , R.layout.recycler_view_item_1) {
            @Override
            public void convert(ViewHolder holder, CampusAssociationItem campusAssociationItem, int position) {
                    holder.setText(R.id.textView , campusAssociationItem.getName());
                    holder.setText(R.id.textView2 , campusAssociationItem.getSubTitle());
//                    holder.setImageResource(R.id.imageView2 , campusAssociationItem.getImgSrc());



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
