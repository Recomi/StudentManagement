package site.recomi.studentmanagement.gui.fragments.book;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.adapter.Base.BaseRecycleViewAdapter;
import site.recomi.studentmanagement.gui.adapter.ViewHolder;
import site.recomi.studentmanagement.other.CampusAssociationItem;

public class NoticeFragment extends Fragment {
    View mview;
    BaseRecycleViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_notice, container, false);
        initRecyView();
        return mview;
    }

    private void initRecyView(){
        RecyclerView recyclerView = mview.findViewById(R.id.recyclerView);
        List<CampusAssociationItem> lists = new ArrayList<>();
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));
        lists.add(new CampusAssociationItem(1,"45654654","45654",false));


        adapter = new BaseRecycleViewAdapter<CampusAssociationItem>(getContext() ,lists , R.layout.recycler_view_item) {
            @Override
            public void convert(ViewHolder holder, CampusAssociationItem campusAssociationItem, int position) {
                holder.setText(R.id.textView , campusAssociationItem.getName());
                holder.setText(R.id.textView2 , campusAssociationItem.getSubTitle());
//                    holder.setImageResource(R.id.imageView2 , campusAssociationItem.getImgSrc());



            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

}
