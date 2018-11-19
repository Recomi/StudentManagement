package site.recomi.studentmanagement.gui.fragments.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.MainActivity;

public class MessageFragment extends Fragment {
    MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container , false);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        mainActivity= (MainActivity)getActivity();
        mainActivity.setTitle("信息");
        mainActivity.setCurrentFragmentLocation(3);

    }
}
