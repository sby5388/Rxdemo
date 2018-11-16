package com.by5388.rxdemo.rxJava;

/**
 * 观察者
 *
 * @author by5388
 *         Administrator on 2018/3/18.
 */

public interface Watcher {
    /**
     * 更新数据
     *
     * @param str 更新内容
     */
    void update(String str);
}
