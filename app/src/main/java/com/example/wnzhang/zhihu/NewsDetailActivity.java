package com.example.wnzhang.zhihu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wnzhang.zhihu.bean.StoryDetailsEntity;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NewsDetailActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://news-at.zhihu.com";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
    }

    private void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mWebView = (WebView) findViewById(R.id.webView);
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        int id = getIntent().getIntExtra("id", 0);
        loadDatas(getService().getNewsDetail(id));
    }

    public DataService getService() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory
                (GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory
                .create()).baseUrl(BASE_URL).build();
        return retrofit.create(DataService.class);
    }

    public void loadDatas(Observable<StoryDetailsEntity> observable) {
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
                mWebView.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //点击小箭头返回
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
