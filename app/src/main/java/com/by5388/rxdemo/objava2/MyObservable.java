package com.by5388.rxdemo.objava2;

import java.util.Observable;

/**
 * Java内置的观察者模式
 *
 * @author by Administrator on 2018/3/19.
 */

public class MyObservable extends Observable {
    private static MyObservable instance = null;

    public static MyObservable getInstance() {
        if (null == instance) {
            instance = new MyObservable();
        }

        return instance;
    }

    private MyObservable() {

    }


    private int myNumber = 10;

    public int getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(int myNumber) {
        if (this.myNumber != myNumber) {
            this.myNumber = myNumber;
            setChanged();
            notifyObservers();
        }
    }
}
