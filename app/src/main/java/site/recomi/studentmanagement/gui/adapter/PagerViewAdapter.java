package site.recomi.studentmanagement.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.activities.BrowserActivity;

public class PagerViewAdapter extends android.support.v4.view.PagerAdapter {
    private Context mContext;
    private List<String> mData;

    public PagerViewAdapter(Context context , List<String> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.pager_item_base,null);
        ImageView iv = view.findViewById(R.id.iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(mContext , BrowserActivity.class);
                        intent.putExtra("site" , "http://113.107.212.69:81/hdsbzt/19da/shownews.asp?id=1458");
                        mContext.startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(mContext , BrowserActivity.class);
                        intent.putExtra("site" , "http://www.luodingpoly.cn/zs/zhaoshengkuaixun/yixuekaozhaoshengkuaixun/113.html");
                        mContext.startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(mContext , BrowserActivity.class);
                        intent.putExtra("site" , "http://www.luodingpoly.cn/");
                        mContext.startActivity(intent);
                        break;
                }

            }
        });


        Picasso.with(mContext).load(mData.get(position)).into(iv);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //每一个所占空间
    @Override
    public float getPageWidth(int position) {
        //return 1f; 默认返回1,代表该position占据了ViewPager的一整页,范围(0,1]
        return 1f;
    }
}

