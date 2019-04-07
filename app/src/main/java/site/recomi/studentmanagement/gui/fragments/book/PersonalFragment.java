package site.recomi.studentmanagement.gui.fragments.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.BookAppointmentActivity;
import site.recomi.studentmanagement.gui.activities.BookCollectActivity;
import site.recomi.studentmanagement.gui.activities.BookHistoryActivity;

public class PersonalFragment extends Fragment implements View.OnClickListener {
    View mview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_personal, container, false);
        CardView collect = mview.findViewById(R.id.collect);
        collect.setOnClickListener(this);
        CardView history = mview.findViewById(R.id.history);
        history.setOnClickListener(this);
        CardView appointment = mview.findViewById(R.id.appointment);
        appointment.setOnClickListener(this);

        return mview;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.collect:
                getActivity().startActivity(new Intent(getContext() , BookCollectActivity.class));
                break;
            case R.id.history:
                getActivity().startActivity(new Intent(getContext() , BookHistoryActivity.class));
                break;
            case R.id.appointment:
                getActivity().startActivity(new Intent(getContext() , BookAppointmentActivity.class));
                break;
        }
    }
}
