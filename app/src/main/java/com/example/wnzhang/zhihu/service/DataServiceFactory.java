package com.example.wnzhang.zhihu.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wnzhang on 16-7-24.
 */
public class DataServiceFactory {
    private static final String BASE_URL = "http://news-at.zhihu.com";

    public static DataService getDataService() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory
                (GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory
                .create()).baseUrl(BASE_URL).build();
        return retrofit.create(DataService.class);
    }
}
