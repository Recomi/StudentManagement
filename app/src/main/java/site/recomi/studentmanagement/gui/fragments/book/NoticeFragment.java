package site.recomi.studentmanagement.gui.fragments.book;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.BookDetailActivity;

public class NoticeFragment extends Fragment {
    View mview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_notice, container, false);
        TextView view = mview.findViewById(R.id.xxxxx);
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
