package com.example.wnzhang.zhihu.presenter;

import com.example.wnzhang.zhihu.service.DataService;
import com.example.wnzhang.zhihu.service.DataServiceFactory;
import com.example.wnzhang.zhihu.bean.RootEntity;
import com.example.wnzhang.zhihu.bean.StoriesEntity;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wnzhang on 16-7-24.
 */
public class HomePresenter implements IHomePresenter {
    private DataService mService;
    private IHomePresenter.View mView;

    public HomePresenter(IHomePresenter.View view) {
        mService = DataServiceFactory.getDataService();
        mView = view;
    }

    @Override
    public void loadDatas(String type) {
        Observable<RootEntity> observable = getObserverable(type);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).map(new Func1<RootEntity, ArrayList<StoriesEntity>>() {
            @Override
            public ArrayList<StoriesEntity> call(RootEntity rootEntity) {
                return rootEntity.getStories();
            }
        }).subscribe(new Subscriber<ArrayList<StoriesEntity>>() {
            @Override
            public void onCompleted() {
                mView.stopRefreshing();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopRefreshing();
            }

            @Override
            public void onNext(ArrayList<StoriesEntity> storiesEntities) {
                mView.updateUi(storiesEntities);
            }
        });
    }

    private Observable<RootEntity> getObserverable(String type) {
        if (type.equals("interest")) {
            return mService.getInterest();
        } else if (type.equals("news")) {
            return mService.getLatestNews();
        } else if (type.equals("safety")) {
            return mService.getSafety();
        } else if (type.equals("sport")) {
            return mService.getSport();
        }
        return null;
    }
}
