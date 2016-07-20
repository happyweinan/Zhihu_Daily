package com.example.wnzhang.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

/**
 * Created by wnzhang on 16-7-15.
 */
public class SportFragment extends BaseFragmet {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadDatas(service.getSport());
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadDatas(service.getSport());
            }
        });
    }
}
