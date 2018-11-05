package site.recomi.studentmanagement.gui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.fragments.login.FindPasswordFragment;
import site.recomi.studentmanagement.gui.fragments.login.SignInFragment;
import site.recomi.studentmanagement.gui.fragments.login.SignUpFragment;

public class LoginPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private ArrayList<Fragment> fragments;
    private FragmentManager manager;
    private String[] titles;

    public LoginPagerAdapter(Context context,FragmentManager manager, ArrayList<Fragment> fragments, String[] titles) {
        super(manager);
        this.mContext = context;
        this.manager = manager;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

//      不要重该方法，会导致PagerView为空白
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//        return view == o;
//    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.isEmpty()) {
            switch (position) {
                case 0:
                    return SignInFragment.newInstance();
                case 1:
                    return SignUpFragment.newInstance();
                case 2:
                    return FindPasswordFragment.newInstance();
                default:
                    return SignInFragment.newInstance();
            }
        }else {
            return fragments.get(position);
        }
    }

//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
////        container.removeView((View)  object);
//    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
