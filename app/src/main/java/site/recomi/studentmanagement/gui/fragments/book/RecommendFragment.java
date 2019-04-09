package site.recomi.studentmanagement.gui.fragments.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.BookDetailActivity;

public class RecommendFragment extends Fragment {
    View mview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_recommend, container, false);
        LinearLayout view = mview.findViewById(R.id.testbook);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext() , BookDetailActivity.class);
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });



        return mview;

    }


}
