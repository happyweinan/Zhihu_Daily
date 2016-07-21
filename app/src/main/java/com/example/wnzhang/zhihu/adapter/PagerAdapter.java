package com.example.wnzhang.zhihu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wnzhang.zhihu.fragment.InterestFragment;
import com.example.wnzhang.zhihu.fragment.NewsFragment;
import com.example.wnzhang.zhihu.fragment.SafetyFragment;
import com.example.wnzhang.zhihu.fragment.SportFragment;

import java.util.ArrayList;

/**
 * Created by wnzhang on 16-7-15.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private ArrayList<String> titleList =new ArrayList<String>();

    public PagerAdapter(FragmentManager manager) {
        super(manager);

        mFragments.add(new NewsFragment());
        mFragments.add(new InterestFragment());
        mFragments.add(new SafetyFragment());
        mFragments.add(new SportFragment());

        titleList.add("今日头条");
        titleList.add("不准无聊");
        titleList.add("互联网安全");
        titleList.add("体育日报");
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
