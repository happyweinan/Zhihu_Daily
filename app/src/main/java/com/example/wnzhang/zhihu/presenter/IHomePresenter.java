package com.example.wnzhang.zhihu.presenter;

import com.example.wnzhang.zhihu.bean.StoriesEntity;

import java.util.ArrayList;

/**
 * Created by wnzhang on 16-7-24.
 */
public interface IHomePresenter {
    void loadDatas(String type);

    interface View {
        void updateUi(ArrayList<StoriesEntity> storiesEntities);
        void stopRefreshing();
    }
}
