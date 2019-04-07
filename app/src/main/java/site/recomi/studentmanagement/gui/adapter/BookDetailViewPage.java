package site.recomi.studentmanagement.gui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import site.recomi.studentmanagement.R;

public class BookDetailViewPage extends PagerAdapter {
    private Context mContext;
    private List<Integer> layoutID;         //所有要显示的布局id
    String[] tabNames = {"馆藏信息" , "内容简介" ,"图片欣赏"};

    public BookDetailViewPage(Context context , List<Integer> layoutID){
        this.mContext = context;
        this.layoutID = layoutID;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        switch (position){
            case 0:
                View view0 = View.inflate(mContext, layoutID.get(0),null);
//                TextView tv = (TextView) view.findViewById(R.id.tv);
//                tv.setText(mData.get(position));
                container.addView(view0);
                return view0;
            case 1:
                View view1 = View.inflate(mContext, layoutID.get(1),null);
//                TextView tv = (TextView) view.findViewById(R.id.tv);
//                tv.setText(mData.get(position));
                container.addView(view1);
                return view1;
            case 2:
                View view2 = View.inflate(mContext, layoutID.get(2),null);
//                TextView tv = (TextView) view.findViewById(R.id.tv);
//                tv.setText(mData.get(position));
                container.addView(view2);
                return view2;
        }
        return super.instantiateItem(container, position);
    }

    @Override
    public int getCount() {
        return layoutID.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames[position];
    }


}
