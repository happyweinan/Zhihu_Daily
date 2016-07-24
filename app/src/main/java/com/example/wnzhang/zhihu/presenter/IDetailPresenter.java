package com.example.wnzhang.zhihu.presenter;

import com.example.wnzhang.zhihu.bean.StoriesEntity;

import java.util.ArrayList;

/**
 * Created by wnzhang on 16-7-24.
 */
public interface IDetailPresenter {
    void loadDatas(int id);
    interface View {
        void updateUi(String str);
    }
}
