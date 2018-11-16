package com.by5388.rxdemo.learnRxJava;

import rx.Observable;
import rx.Subscriber;

/**
 * @author by5388  on 2018/5/6.
 */

public class HelloRxJava {
    public static void main(String... args) {
        Observable<String> stringObservable = Observable.just("a", "b", "c");
        stringObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                println(s);
            }
        });
    }

    private static void println(String s) {
        System.out.println(s);
    }
}
