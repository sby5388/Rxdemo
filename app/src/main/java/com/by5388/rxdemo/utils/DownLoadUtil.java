package com.by5388.rxdemo.utils;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * @author by5388
 *         Administrator on 2018/3/24.
 */

public class DownLoadUtil {
    /**
     * TODO 网络连接类：？？？
     */
    private OkHttpClient client;

    public DownLoadUtil() {
        this.client = new OkHttpClient();
    }

    /**
     * 返回一个被观察者对象
     *
     * @param path 图片路径
     * @return
     */
    public Observable<byte[]> downloadImage(String path) {
        //byte[]:返回类型
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    //访问网络操作
                    //get
                    Request request = new Request.Builder().url(path).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            //异常
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            //回调
                            //Response response:取得数据
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                if (null != data) {
                                    subscriber.onNext(data);
                                }
                            }
                            //TODO 完成!注意 onCompleted(）放置的位置
                            subscriber.onCompleted();
                        }
                    });

                }
            }
        });

    }


    /**
     * 一个简单的Get请求例子
     *
     * @return
     */
    public Observable<String> getSomeMessage() {
        return Observable.create(subscriber -> {
                    if (!subscriber.isUnsubscribed()) {
                        Request request = new Request.Builder()
                                .url("https://www.publicobject.com/helloworld.txt")
                                .header("User-Agent", "OkHttp Example")
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                subscriber.onError(e);
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                if (response.isSuccessful()) {
                                    String message = response.body().string();
                                    if (!TextUtils.isEmpty(message)) {
                                        subscriber.onNext(message);
                                    }
                                }
                            }
                        });

                    }
                }
        );

    }

}
