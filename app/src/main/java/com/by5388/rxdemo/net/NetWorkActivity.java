package com.by5388.rxdemo.net;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.by5388.rxdemo.R;
import com.by5388.rxdemo.utils.DownLoadUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * by5388 20180507
 * 网络请求
 *
 * @author by5388
 */
public class NetWorkActivity extends AppCompatActivity {
    public static final String TAG = NetWorkActivity.class.getSimpleName();
    DownLoadUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);

        initData();
    }

    private void initData() {
        util = new DownLoadUtil();
        toGetSomething();
    }

    private void toGetSomething() {
        util.getSomeMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext: " + s);
                    }
                });
    }

    private void initData1() {
        final OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("https://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();

        new Thread(() -> {
            try {
                Response response = okHttpClient.newCall(request).execute();
                Log.d(TAG, "run: " + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
