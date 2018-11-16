package com.by5388.rxdemo.utils2;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * @author by Administrator on 2018/3/19.
 */

public class RxUtils2 {
    private final static String TAG = RxUtils2.class.getName();

    /**
     *
     */


    public static void createObservable() {
        //1.创建被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("第一");
                    subscriber.onNext("第二");
                    subscriber.onNext("第三");
                    subscriber.onCompleted();
                }
            }
        });
        //2.创建观察者方法1
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "-onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "-onError: " + e.getMessage());

            }

            @Override
            public void onNext(String o) {
                Log.d(TAG, "-onNext: " + o);
            }
        };
        //2.创建观察者方法2
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: --->" + s);
            }
        };

        //3.确定订阅关系
        observable.subscribe(subscriber);
        observable.subscribe(observer);
        //4.被调用
    }

    class MyObserver implements Observer {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {

        }
    }


    class MyObservable implements Observer {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Object o) {

        }
    }

    public MyObservable getObservable() {
        return new MyObservable();
    }


}
