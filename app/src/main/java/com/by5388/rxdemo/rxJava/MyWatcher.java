package com.by5388.rxdemo.rxJava;

/**
 * @author by5388
 *         Administrator on 2018/3/18.
 */

public class MyWatcher implements Watcher {

    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
