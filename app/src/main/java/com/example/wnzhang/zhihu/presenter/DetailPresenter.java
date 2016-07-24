package com.example.wnzhang.zhihu.presenter;

import com.example.wnzhang.zhihu.service.DataService;
import com.example.wnzhang.zhihu.service.DataServiceFactory;
import com.example.wnzhang.zhihu.HtmlUtils;
import com.example.wnzhang.zhihu.bean.StoryDetailsEntity;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wnzhang on 16-7-24.
 */
public class DetailPresenter implements IDetailPresenter {
    private IDetailPresenter.View mView;

    public DetailPresenter(IDetailPresenter.View view) {
        mView = view;
    }

    @Override
    public void loadDatas(int id) {
        DataService service = DataServiceFactory.getDataService();
        Observable<StoryDetailsEntity> observable = service.getNewsDetail(id);
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).map(new Func1<StoryDetailsEntity, String>() {
            @Override
            public String call(StoryDetailsEntity storyDetailsEntity) {
                return HtmlUtils.structHtml(storyDetailsEntity);
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String str) {
                mView.updateUi(str);
            }
        });
    }
}
