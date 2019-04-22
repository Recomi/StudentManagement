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
import site.recomi.studentmanagement.entity.CarouselMap;
import site.recomi.studentmanagement.gui.activities.BrowserActivity;

public class CarouselMapPagerViewAdapter extends android.support.v4.view.PagerAdapter {
    private Context mContext;
    private List<CarouselMap> mData;

    public CarouselMapPagerViewAdapter(Context context , List<CarouselMap> list) {
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
        iv.setOnClickListener(v -> {
            Intent intent = new Intent(mContext , BrowserActivity.class);
            //启动页面传入文章地址
            intent.putExtra("site" , mData.get(position).getArticle_url());
            mContext.startActivity(intent);
        });
        //轮播图片地址
        Picasso.with(mContext).load(mData.get(position).getImg_url()).into(iv);
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

