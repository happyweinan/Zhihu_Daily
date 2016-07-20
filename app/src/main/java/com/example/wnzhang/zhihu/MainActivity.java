package com.example.wnzhang.zhihu;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.example.wnzhang.zhihu.adapter.PagerAdapter;

public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip)findViewById(R.id.tabs);
        tabs.setViewPager(mViewPager);
    }
}
