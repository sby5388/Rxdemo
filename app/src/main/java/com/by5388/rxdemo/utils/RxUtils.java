package com.by5388.rxdemo.utils;

import android.util.Log;

import com.by5388.rxdemo.Person;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Rx的主要创建方式
 *
 * @author by5388
 *         Administrator on 2018/3/18.
 */

public class RxUtils {
    private final static String TAG = RxUtils.class.getName();


    /**
     * 使用“create”方法来实现Rx
     */
    public static void createObservable() {
        //步骤1：创建一个被观察者
        //String :返回值类型
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        //如果观察者与被观察者之间还有订阅关系的话
                        if (!subscriber.isUnsubscribed()) {
                            //onNext（）参数的类型要与 Observable<String> observable 相一致
                            //onNext():开始发送（返回数据）,或者执行命令
                            subscriber.onNext("你好");
                            subscriber.onNext("byy");
                            subscriber.onNext(getName());
                            subscriber.onNext("哈哈");
                            //onCompleted():发送数据完成
                            subscriber.onCompleted();
                        }
                    }
                });

        //步骤2：定义观察者（订阅者）
        //String :接收的数据类型是String
        Subscriber<String> showData = new Subscriber<String>() {
            //三个回调方法
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.toString());
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: ---》" + s);
            }
        };
        //步骤3：确定关联关系（“订阅”）
        observable.subscribe(showData);
        //步骤4：被调用：RxUtils.createObservable();
    }

    /**
     * 使用‘create’方法2实现Rx
     */
    public static void createObservable2() {
        Log.d(TAG, "createObservable2: ");
        //一个被观察者--->多个观察者：多对多的响应

        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(1);
                    subscriber.onNext(2);
                    subscriber.onNext(3);
                    subscriber.onCompleted();
                }
            }
        });


        Observable<Integer> observable2 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(5);
                    subscriber.onNext(6);
                    subscriber.onNext(7);
                    subscriber.onCompleted();
                }
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "ob:onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "ob: onError: " + e.getMessage());

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "ob:onNext: " + integer);
            }
        };


        Subscriber<Integer> showData = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "sd:onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "sd: onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "sd:onNext: " + integer);
            }
        };
        //observable.subscribe(showData);
        observable.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "un:onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "un:onError: " + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "un:onNext: " + integer);
            }
        });

        observable.subscribe(observer);
        observable.subscribe(showData);
        observable2.subscribe(observer);
        observable2.subscribe(showData);


    }


    /**
     * TODO 使用from方法实现Rx ：待实现
     * from 方法适用于传递容器、数组等
     */
    public static void fromObservable(final List<Person> persons) {
        //创建发布者
        Observable formObservable = Observable.from(persons);
        //创建订阅者 并关联
        formObservable.subscribe(new Subscriber<Person>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "from：onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "from:onError: ");
            }

            @Override
            public void onNext(Person person) {
                Log.d(TAG, "from：onNext: " + person);
            }
        });

    }


    /**
     * 使用Just创建Observable
     * Just类似于From，但是From会将数组或Iterable的元素具取出然后逐个发射，
     * 而Just只是简单的原样发射，将数组或Iterable当做单个数据。
     * Just接受一至九个参数，返回一个按参数列表顺序发射这些数据的Observable:::参数类型可以不一样，只要是有意义的变量
     */
    public static void justObservable(final List<Person> persons) {
        //1.创建发布者
        Observable<Object> justObservable = Observable.just(123, 'a', "a", persons, null);
        //2.创建订阅者
        Subscriber<Object> subscriber = new Subscriber<Object>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "just:onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "just:onError: ");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "just:onNext: " + o);
            }
        };
        //3.产生关联关系
        justObservable.subscribe(subscriber);

    }


    public static String getName() {
        return "getName";
    }


    /**
     * 使用timer实现Rx
     * timer()创建一个Observable，它在一个给定的延迟后发射一个特殊的值 设定执行方法在UI线程执行
     * 延时两秒后发射值
     * 实测 延时2s后发送了一个0
     */

    public static void timerObservable() {

        //1.订阅者
        /*
         每隔2个时间单位
         {@link TimeUnit.SECONDS}:时间单位，此处为秒
          参数3：mianThread:在安卓主线程（UI线程）执行
          timer有定时的作用，延时发送一个值0???::整个函数的作用就是2秒钟后发送一个‘0’，并结束
         */
        Observable timerObservable = Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread());
        //2.观察者
        Observer observer = new Observer() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "timer:onCompleted: ");

            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "timer:onError: ");
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "timer:onNext: " + o);
            }
        };

        timerObservable.subscribe(observer);

    }


    /**
     * 使用range实现Rx
     * range 发射从n到m的整数序列 可以指定Scheduler设置执行方法运行的线程
     * repeat方法可以指定重复触发的次数
     */

    public static void rangeObservable() {
        //整个函数的作用：从4开始，连续发送8个值，发送2次
        Observable rangeObservable = Observable.range(4, 8).repeat(2);
        //在不写观察者的情况下，可以使用Action1和Action0这两个接口来实现不完整定义的回调； 参见：{@link ActionSubscriber}
        //Action1<T>可以代替实现onNext(); Action1<Throwable>可以代替实现onError(); Action0可以代替实现onConplete()
        //lambda:函数式编程 java1.8及以上
        rangeObservable.subscribe(
                //可替代onNext()
                new Action1() {
                    @Override
                    public void call(Object o) {
                        Log.d(TAG, "call: " + o.toString());
                    }
                },
                //可替代onError()
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.d(TAG, "call: " + throwable.getMessage());
                    }
                },
                //可替代onCompleted()
                new Action0() {
                    @Override
                    public void call() {
                        Log.d(TAG, "call: completed");
                    }
                }
        );


    }

    /**
     * 指定某一个时刻发送数据::这家伙根本停不下来，一直在发送
     */
    public static void intervalObservable() {
        //TODO items没用到？
        Integer[] items = {1, 2, 3, 4, 5};
        //每隔一秒发送一个数据
        Observable observable = Observable.interval(1, 1, TimeUnit.SECONDS);
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                Log.d(TAG, "interval:call: " + o);
            }
        });
    }
}
