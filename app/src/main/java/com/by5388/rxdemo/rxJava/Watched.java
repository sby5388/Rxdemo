package com.by5388.rxdemo.rxJava;

/**
 * 被观察者
 *
 * @author by5388
 *         Administrator on 2018/3/18.
 */

public interface Watched {

    /**
     * 添加观察者
     *
     * @param watcher 观察者
     */
    void addWatcher(Watcher watcher);

    /**
     * 移除观察者
     *
     * @param watcher 观察者
     */
    void removeWatcher(Watcher watcher);

    /**
     * 通知 观察者
     *
     * @param str 通知内容
     */
    void notifyWatchers(String str);
}
