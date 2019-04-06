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
import site.recomi.studentmanagement.other.BookEntitiy;

public class SearchFragment extends Fragment {
    View mView;
    RecyclerView rv;
    BaseRecycleViewAdapter<BookEntitiy> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView rv = mView.findViewById(R.id.rv);
        List<BookEntitiy> lists = new ArrayList<>();
        lists.add(new BookEntitiy(R.drawable.ic_launcher_background, "我是爸爸吧", "小哥" , "东方出版社"));
        lists.add(new BookEntitiy(R.drawable.ic_launcher_background, "我是爸爸吧", "小哥" , "东方出版社"));
        lists.add(new BookEntitiy(R.drawable.ic_launcher_background, "我是爸爸吧", "小哥" , "东方出版社"));
        lists.add(new BookEntitiy(R.drawable.ic_launcher_background, "我是爸爸吧", "小哥" , "东方出版社"));
        lists.add(new BookEntitiy(R.drawable.ic_launcher_background, "我是爸爸吧", "小哥" , "东方出版社"));
        lists.add(new BookEntitiy(R.drawable.ic_launcher_background, "我是爸爸吧", "小哥" , "东方出版社"));


        adapter = new BaseRecycleViewAdapter<BookEntitiy>(getContext() ,lists , R.layout.recycler_view_item_2) {
            @Override
            public void convert(ViewHolder holder, BookEntitiy bookEntitiy, int position) {
                holder.setText(R.id.name , bookEntitiy.getName());
                holder.setText(R.id.author , bookEntitiy.getAuthor());
                holder.setText(R.id.publishingHouse , bookEntitiy.getPublishingHouse());
                holder.setImageResource(R.id.cover , bookEntitiy.getBookID());
            }
        };

        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(adapter);
        return mView;
    }


}
