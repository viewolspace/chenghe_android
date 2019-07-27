package com.example.chenghejianzhi.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.base.base.BaseActivity;
import com.example.base.constants.Constants;
import com.example.base.rx.RxEvent;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zdy On 2019/7/27.
 */
public class WebActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.webView)
    WebView webView;
    private String url;

    public static void start(Context context,String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(Constants.WEB_URL,url);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webSetting(webView);

    }


    @Override
    protected void initData() {
        url = getIntent().getStringExtra(Constants.WEB_URL);
        loadUrl(url);
    }
    private void loadUrl(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }


    private void webSetting(WebView webView){
        webView.setBackgroundColor(getResources().getColor(R.color.white));
        // 设置缩放
        WebSettings websettings = webView.getSettings();
        websettings.setBuiltInZoomControls(true);
        // 设置垂直滚动
//        if (!verticalScroll) {
//            webView.setVerticalScrollBarEnabled(false);
//        }
        // 设置Client
        //webView.setWebViewClient(webClient);
        // 开启javascript设置
        websettings.setJavaScriptEnabled(true);
        websettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 应用可以有数据库
        websettings.setDatabaseEnabled(true);
        // 应用可以有缓存
        websettings.setDomStorageEnabled(true);
        websettings.setAppCacheEnabled(true);
        websettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 可以读取文件缓存
        websettings.setGeolocationEnabled(true);
        websettings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(true);
        }
        webView.setWebViewClient(new WebViewClient());

    }
}
