package com.by5388.rxdemo;

import com.by5388.rxdemo.javaOb.MyObservable;
import com.by5388.rxdemo.javaOb.MyObserver;

import org.junit.Test;

/**
 * @author by5388
 * Administrator on 2018/3/18.
 */

public class Test2 {
    @Test
    public void main() {
        MyObservable observable = new MyObservable();
        MyObserver observer = new MyObserver(observable);
        observable.setData(1);
        observable.setData(2);
        observable.setData(3);
        observable.setData(3);
    }
}
