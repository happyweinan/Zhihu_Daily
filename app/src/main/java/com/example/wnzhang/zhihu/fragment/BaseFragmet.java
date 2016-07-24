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

import com.example.wnzhang.zhihu.R;
import com.example.wnzhang.zhihu.adapter.NewsAdapter;
import com.example.wnzhang.zhihu.bean.StoriesEntity;
import com.example.wnzhang.zhihu.presenter.HomePresenter;
import com.example.wnzhang.zhihu.presenter.IHomePresenter;

import java.util.ArrayList;

/**
 * Created by wnzhang on 16-7-14.
 */
public abstract class BaseFragmet extends Fragment implements IHomePresenter.View{

    protected SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mRecyclerView;
    protected IHomePresenter presenter;
    private ArrayList<StoriesEntity> mEntities = new ArrayList<StoriesEntity>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter = new HomePresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeLayout.setRefreshing(true);
        presenter.loadDatas(getViewType());
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadDatas(getViewType());
            }
        });
    }

    @Override
    public void updateUi(ArrayList<StoriesEntity> storiesEntities) {
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

    @Override
    public void stopRefreshing() {
        mSwipeLayout.setRefreshing(false);
    }

    protected abstract String getViewType();
}
