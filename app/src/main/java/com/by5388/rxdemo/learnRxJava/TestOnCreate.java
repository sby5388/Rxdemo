package com.by5388.rxdemo.learnRxJava;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * @author by5388  on 2018/5/6.
 */

public class TestOnCreate {

    public static void main(String... args) {
        TestOnCreate test = new TestOnCreate();
//        test.create1();
        test.from1();
    }

    /**
     * Create
     */
    void create1() {
        Observable observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i < 5; i++) {
                        subscriber.onNext(i);
                    }
                }
                subscriber.onCompleted();
            }
        });

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                println(String.valueOf(integer));
            }
        };
        observable.subscribe(subscriber);
    }


    private static void println(String s) {
        System.out.println(s);
    }

    private static void println(Integer integer) {
        println(String.valueOf(integer));
    }


    private void from1() {
        List<Integer> numbers = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            numbers.add(i);
        }
        Observable<Integer> observable = Observable.from(numbers);
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                println(integer);
            }
        });
        observable.subscribe((Integer integer) -> {
            println(integer);;
        });

    }

}
