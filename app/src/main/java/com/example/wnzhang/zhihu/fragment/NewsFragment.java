package com.example.wnzhang.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.example.wnzhang.zhihu.bean.RootEntity;

import rx.Observable;

/**
 * Created by wnzhang on 16-7-15.
 */
public class NewsFragment extends BaseFragmet {
    private int mDay = 19;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadDatas(service.getLatestNews());
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDatas(service.getLatestNews());
            }
        });
    }

    @Override
    public Observable<RootEntity> getMoreData() {
        String str = "";
        if (mDay < 10) {
            str = "0";
        }
        str += String.valueOf(mDay);
        mDay--;
        return service.getNewsByDate("201607" + str);
    }
}
