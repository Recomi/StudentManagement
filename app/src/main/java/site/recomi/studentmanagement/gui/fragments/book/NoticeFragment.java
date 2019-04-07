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
import site.recomi.studentmanagement.other.BookNoticeEntitiy;

public class NoticeFragment extends Fragment {
    View mview;
    BaseRecycleViewAdapter<BookNoticeEntitiy> adapter;

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
        List<BookNoticeEntitiy> lists = new ArrayList<>();

        lists.add(new BookNoticeEntitiy("新进一批图书" ,"2018/8/8罗定市捐赠","2018/8/8",R.drawable.testpic));
        lists.add(new BookNoticeEntitiy("新进一批图书" ,"2018/8/8罗定市捐赠","2018/8/8",R.drawable.testpic));
        lists.add(new BookNoticeEntitiy("新进一批图书" ,"2018/8/8罗定市捐赠","2018/8/8",R.drawable.testpic));
        lists.add(new BookNoticeEntitiy("新进一批图书" ,"2018/8/8罗定市捐赠","2018/8/8",R.drawable.testpic));
        lists.add(new BookNoticeEntitiy("新进一批图书" ,"2018/8/8罗定市捐赠","2018/8/8",R.drawable.testpic));


        adapter = new BaseRecycleViewAdapter<BookNoticeEntitiy>(getContext() ,lists , R.layout.recycler_view_item) {
            @Override
            public void convert(ViewHolder holder, BookNoticeEntitiy bookNoticeEntitiy, int position) {
                holder.setText(R.id.title , bookNoticeEntitiy.getTitle());
                holder.setText(R.id.content , bookNoticeEntitiy.getContent());
                holder.setText(R.id.date , bookNoticeEntitiy.getDate());
                holder.setImageResource(R.id.imgSrc , bookNoticeEntitiy.getSrcImage());



            }
        };

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

}
