package com.by5388.rxdemo.rxJava;

/**
 * @author by5388
 *         Administrator on 2018/3/18.
 */

public class Test {

    public static void main(String[] agrs) {
        Watched watched = new MyWatched();
        Watcher watcher = new MyWatcher();
        Watcher watcher2 = new MyWatcher();
        watched.addWatcher(watcher);
        watched.addWatcher(watcher2);
        watched.notifyWatchers("abc");
    }
}
