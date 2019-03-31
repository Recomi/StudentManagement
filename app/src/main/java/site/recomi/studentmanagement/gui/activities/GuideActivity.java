package site.recomi.studentmanagement.gui.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import site.recomi.studentmanagement.gui.adapter.PagerViewAdapter;
import site.recomi.studentmanagement.R;
import site.recomi.studentmanagement.gui.adapter.WelcomePagerViewAdapter;

public class GuideActivity extends AppCompatActivity {
    List<Integer> bitmaps = new ArrayList<>();
    ViewPager vp;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);

        start = findViewById(R.id.startButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this , MainActivity.class));
                GuideActivity.this.finish();
            }
        });

        vp = findViewById(R.id.vp);
        bitmaps.add(R.drawable.one);
        bitmaps.add(R.drawable.two);
        bitmaps.add(R.drawable.three);
        vp.setAdapter(new WelcomePagerViewAdapter(this ,bitmaps));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2){
                    start.setVisibility(View.VISIBLE);
                }else {
                    start.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
