package com.by5388.rxdemo.javaOb;

import java.util.Observable;

/**
 * Java自带的观察者模式：被观察者
 *
 * @author by5388
 * Administrator on 2018/3/18.
 */

public class MyObservable extends Observable {
    private int data = 0;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        if (this.data != data) {
            this.data = data;
            //更改状态 ：已发生变化
            setChanged();
            //通知观察者
            notifyObservers();
        }
    }

    public MyObservable() {

    }

}
