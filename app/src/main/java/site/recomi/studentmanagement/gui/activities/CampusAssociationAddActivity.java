package site.recomi.studentmanagement.gui.activities;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.adapter.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.CampusAssociationItem;

public class CampusAssociationAddActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    public RecyclerView rv;
    BaseRecycleViewAdapter<CampusAssociationItem> adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_association_add);
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark2));

        List<CampusAssociationItem> lists = new ArrayList<>();
        lists.add(new CampusAssociationItem(R.drawable.bg10 , "学生会" , "校园核心社团"));
        lists.add(new CampusAssociationItem(R.drawable.bg10 , "吉他社" , "每个人都是歌手"));
        lists.add(new CampusAssociationItem(R.drawable.bg10 , "学生会" , "校园核心社团"));
        lists.add(new CampusAssociationItem(R.drawable.bg10 , "学生会" , "校园核心社团"));
        lists.add(new CampusAssociationItem(R.drawable.bg10 , "学生会" , "校园核心社团"));
        lists.add(new CampusAssociationItem(R.drawable.bg10 , "学生会" , "校园核心社团"));

        adapter = new BaseRecycleViewAdapter<CampusAssociationItem>(this ,lists , R.layout.recycler_view_item_1) {
            @Override
            public void convert(ViewHolder holder, CampusAssociationItem campusAssociationItem) {
                    holder.setText(R.id.textView , campusAssociationItem.getName());
                    holder.setText(R.id.textView2 , campusAssociationItem.getSubTitle());
                    holder.setImageResource(R.id.imageView2 , campusAssociationItem.getImgSrc());



            }
        };

        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
    }

}
