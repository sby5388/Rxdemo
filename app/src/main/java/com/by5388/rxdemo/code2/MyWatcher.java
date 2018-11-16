package com.by5388.rxdemo.code2;

/**
 * @author by Administrator on 2018/3/19.
 */

public class MyWatcher implements Watcher {
    @Override
    public void update(Object o) {
        System.out.println(o);
    }
}
