package site.recomi.studentmanagement.gui.fragments.book;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.BookAppointmentActivity;
import site.recomi.studentmanagement.gui.activities.BookCollectActivity;
import site.recomi.studentmanagement.gui.activities.BookHistoryActivity;
import site.recomi.studentmanagement.gui.activities.library.CountAllBookLikeActivity;
import site.recomi.studentmanagement.gui.fragments.Base.BaseFragment;

public class PersonalFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.card_myBookCollect)
    CardView card_myBookCollect;
    @BindView(R.id.card_myBookHistory)
    CardView card_myBookHistory;
    @BindView(R.id.card_myAppointment)
    CardView card_myAppointment;
    @BindView(R.id.rell_to_bookLike_count)
    RelativeLayout rell_to_bookLike_count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_personal, container, false);
        mContext = mView.getContext();
        ButterKnife.bind(this, mView);   //绑定ButterKnife
        initView();
        return mView;
    }

    private void initView() {
        card_myBookCollect.setOnClickListener(this);
        card_myBookHistory.setOnClickListener(this);
        card_myAppointment.setOnClickListener(this);
        rell_to_bookLike_count.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_myBookCollect:
                mContext.startActivity(new Intent(getContext() , BookCollectActivity.class));
                break;
            case R.id.card_myBookHistory:
                mContext.startActivity(new Intent(getContext() , BookHistoryActivity.class));
                break;
            case R.id.card_myAppointment:
                mContext.startActivity(new Intent(getContext() , BookAppointmentActivity.class));
                break;
            case R.id.rell_to_bookLike_count:
                startActivityOnly(getContext(), CountAllBookLikeActivity.class);
                break;
        }
    }

}
