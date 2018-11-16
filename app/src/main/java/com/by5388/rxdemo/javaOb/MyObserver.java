package com.by5388.rxdemo.javaOb;

import java.util.Observable;
import java.util.Observer;

/**
 * Java自带的观察者模式：观察者
 *
 * @author by5388
 *         Administrator on 2018/3/18.
 */

public class MyObserver implements Observer {


    public  MyObserver(Observable observable) {
        //把自身作为观察者添加到被观察者的“观众容器”中
        observable.addObserver(this);
    }


    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof MyObservable) {
            System.out.println(((MyObservable) observable).getData());
        }
    }
}
