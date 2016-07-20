package com.example.wnzhang.zhihu;

import com.example.wnzhang.zhihu.bean.RootEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wnzhang on 16-7-15.
 */
public interface DataService {
    //今日头条
    @GET("/api/4/news/latest")
    Observable<RootEntity> getLatestNews();

    @GET("/api/4/news/before/{date}")
    Observable<RootEntity> getNewsByDate(@Path("date")String date);

    //互联网安全
    @GET("/api/4/theme/10")
    Observable<RootEntity> getSafety();

    //不准无聊
    @GET("/api/4/theme/11")
    Observable<RootEntity> getInterest();

    //体育日报
    @GET("/api/4/theme/8")
    Observable<RootEntity> getSport();
}
