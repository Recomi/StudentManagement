package site.recomi.studentmanagement.gui.activities;

import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import site.recomi.studentmanagement.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {


    private ViewPager viewpager_account;
    private ImageView img_cursor;
    private Button button_page_login;
    private Button button_page_signup;
    private Button button_page_password;
    private CardView cardView;

    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        viewpager_account = (ViewPager) findViewById(R.id.viewpage_account);
        button_page_login = (Button) findViewById(R.id.button_page_login);
        button_page_signup = (Button) findViewById(R.id.button_page_signup);
        button_page_password = (Button) findViewById(R.id.button_page_password);
        img_cursor = (ImageView) findViewById(R.id.img_page_cursor);
        cardView = (CardView) findViewById(R.id.cardView_account_pager);

        //下划线动画的相关设置：
//        bmpWidth = BitmapFactory.decodeResource(getResources(), R.id.img_page_cursor).getWidth();// 获取图片宽度
        img_cursor.setMaxWidth(button_page_login.getWidth());
        bmpWidth = button_page_login.getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int cardview_width = cardView.getWidth();// 获取Cardview宽度
        offset = (cardview_width / 3 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3


        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.page_account_login, null, false));
        listViews.add(mInflater.inflate(R.layout.page_account_signup, null, false));
        listViews.add(mInflater.inflate(R.layout.page_account_password, null, false));
        viewpager_account.setAdapter(new MyPagerAdapter(listViews));
        viewpager_account.setCurrentItem(0);          //设置ViewPager当前页，从0开始算

        button_page_login.setOnClickListener(this);
        button_page_signup.setOnClickListener(this);
        button_page_password.setOnClickListener(this);

        viewpager_account.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_page_login:
                viewpager_account.setCurrentItem(0);
                break;
            case R.id.button_page_signup:
                viewpager_account.setCurrentItem(1);
                break;
            case R.id.button_page_password:
                viewpager_account.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        img_cursor.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    class MyPagerAdapter extends PagerAdapter {
        private ArrayList<View> viewLists;

        public MyPagerAdapter() {
        }

        public MyPagerAdapter(ArrayList<View> viewLists) {
            super();
            this.viewLists = viewLists;
        }

        @Override
        public int getCount() {
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewLists.get(position));
            return viewLists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewLists.get(position));
        }
    }
}