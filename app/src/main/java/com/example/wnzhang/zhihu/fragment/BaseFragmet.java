package com.example.wnzhang.zhihu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wnzhang.zhihu.DataService;
import com.example.wnzhang.zhihu.R;
import com.example.wnzhang.zhihu.adapter.NewsAdapter;
import com.example.wnzhang.zhihu.bean.RootEntity;
import com.example.wnzhang.zhihu.bean.StoriesEntity;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wnzhang on 16-7-14.
 */
public class BaseFragmet extends Fragment {
    private static final String BASE_URL = "http://news-at.zhihu.com";
    protected DataService service;
    protected SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<StoriesEntity> mEntities = new ArrayList<StoriesEntity>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        service = getService();
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public DataService getService() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory
                (GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory
                .create()).baseUrl(BASE_URL).build();
        return retrofit.create(DataService.class);
    }

    public void loadDatas(Observable<RootEntity> observable) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).map(new Func1<RootEntity, ArrayList<StoriesEntity>>() {
            @Override
            public ArrayList<StoriesEntity> call(RootEntity rootEntity) {
                return rootEntity.getStories();
            }
        }).subscribe(new Subscriber<ArrayList<StoriesEntity>>() {
            @Override
            public void onCompleted() {
                mSwipeLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeLayout.setRefreshing(false);
            }

            @Override
            public void onNext(ArrayList<StoriesEntity> storiesEntities) {
                mEntities.clear();
                if (null != storiesEntities) {
                    mEntities.addAll(storiesEntities);
                }
                if (null == mRecyclerView.getAdapter()) {
                    mRecyclerView.setAdapter(new NewsAdapter(mEntities, getActivity()));
                } else {
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                }
            }
        });
    }
}
