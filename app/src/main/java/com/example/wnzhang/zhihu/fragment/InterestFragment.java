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
public class InterestFragment extends BaseFragmet {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadDatas(service.getInterest());
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDatas(service.getInterest());
            }
        });
    }

    @Override
    public Observable<RootEntity> getMoreData() {
        return null;
    }
}
