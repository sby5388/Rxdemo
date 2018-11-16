package com.by5388.rxdemo;

import com.by5388.rxdemo.code2.MyWatched;
import com.by5388.rxdemo.code2.MyWatcher;
import com.by5388.rxdemo.code2.Watched;
import com.by5388.rxdemo.code2.Watcher;

/**
 * @author by Administrator on 2018/3/19.
 */

public class Test {
    @org.junit.Test
    public void main() {
        Watched watched = new MyWatched();
        Watcher watcher1 = new MyWatcher();
        Watcher watcher2 = new MyWatcher();
        Watcher watcher3 = new MyWatcher();
        watched.addWatcher(watcher1);
        watched.addWatcher(watcher1);
        watched.addWatcher(watcher1);
        watched.addWatcher(watcher2);
        watched.addWatcher(watcher3);
        watched.notifyWatchers(123);
        watched.removeWatcher(watcher1);
        watched.removeWatcher(watcher1);
        watched.notifyWatchers(234);
    }
}
