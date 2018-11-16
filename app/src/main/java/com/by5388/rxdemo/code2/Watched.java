package com.by5388.rxdemo.code2;

/**
 * @author by Administrator on 2018/3/19.
 */

public interface Watched {
    void addWatcher(Watcher watcher);

    void removeWatcher(Watcher watcher);

    void notifyWatchers(Object o);

}
