package com.by5388.rxdemo.wether;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.by5388.rxdemo.R;

/**
 * 天气预报
 */
public class WeatherActivity extends AppCompatActivity {
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initData();
        initView();

    }

    private void initData() {
    }

    private void initView() {

        findViewById(R.id.button_get_weather).setOnClickListener(v -> {

        });

        webView = findViewById(R.id.webView_weather);
        settingWebView(webView);
    }

    private void settingWebView(WebView webView) {
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();


        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        webView.loadUrl("file:///android_asset/weather.html");
        webView.setWebViewClient(new WebViewClient());
    }
}
