package site.recomi.studentmanagement.gui.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.adapter.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.CampusAssociationItem;

public class CampusAssociationAddActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    public RecyclerView rv;
    BaseRecycleViewAdapter<CampusAssociationItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_association_add);

        List<CampusAssociationItem> lists = new ArrayList<>();
        lists.add(new CampusAssociationItem("1"));
        lists.add(new CampusAssociationItem("2"));
        lists.add(new CampusAssociationItem("3"));
        lists.add(new CampusAssociationItem("4"));
        lists.add(new CampusAssociationItem("5"));
        adapter = new BaseRecycleViewAdapter<CampusAssociationItem>(this ,lists , R.layout.recycler_view_item_1) {
            @Override
            public void convert(ViewHolder holder, CampusAssociationItem campusAssociationItem) {

            }
        };

    }
}
