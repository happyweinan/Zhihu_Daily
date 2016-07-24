package com.example.wnzhang.zhihu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wnzhang.zhihu.presenter.DetailPresenter;
import com.example.wnzhang.zhihu.presenter.IDetailPresenter;

public class NewsDetailActivity extends AppCompatActivity implements IDetailPresenter.View {
    private WebView mWebView;
    private IDetailPresenter mPresenter;

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
        mPresenter = new DetailPresenter(this);
        mPresenter.loadDatas(id);
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

    @Override
    public void updateUi(String str) {
        mWebView.loadDataWithBaseURL("file:///android_asset/", str, "text/html", "utf-8", null);
    }
}
