package site.recomi.studentmanagement.gui.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.MainActivity;
import site.recomi.studentmanagement.gui.adapter.PagerViewAdapter;

public class HomeFragment extends Fragment {
    MainActivity mainActivity;
    List<Integer> bitmaps = new ArrayList<>();
    ViewPager vp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container , false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public void onStart() {
        super.onStart();
        mainActivity= (MainActivity)getActivity();
        mainActivity.setTitle("首页");
        mainActivity.setCurrentFragmentLocation(1);

        vp = getActivity().findViewById(R.id.vp);
        bitmaps.add(R.drawable.one);
        bitmaps.add(R.drawable.two);
        bitmaps.add(R.drawable.three);
        vp.setAdapter(new PagerViewAdapter(getContext() ,bitmaps));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
