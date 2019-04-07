package site.recomi.studentmanagement.gui.fragments.Base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import site.recomi.studentmanagement.R;

public class BaseFragment extends Fragment {
    View mView;
    Context mContext;

    /**
     * 通过活动数组列表和点击的位置，代替switch-case启动反射的活动
     * 传入：活动反射的数组 ， 点击的位置
     * */
    public void startActivityInList(Class[] classes,int position) {
        startActivityOnly(classes[position]);
    }

    /**
     * 方便快速点击切换活动
     * 只启动活动，不进行其他操作
     * 传入：活动的class
     * */
    private void startActivityOnly(Class c) {
        Intent intent = new Intent(mContext, c);
        startActivity(intent);
    }
}
