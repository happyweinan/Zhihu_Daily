package com.example.wnzhang.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by wnzhang on 16-7-15.
 */
public class SafetyFragment extends BaseFragmet {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        loadDatas(service.getSafety());
    }
}
