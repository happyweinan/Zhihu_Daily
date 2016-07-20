package com.example.wnzhang.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by wnzhang on 16-7-15.
 */
public class InterestFragment extends BaseFragmet {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadDatas(service.getInterest());
    }
}
