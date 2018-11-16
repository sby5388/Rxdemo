package com.by5388.rxdemo.code2;

import java.util.HashSet;
import java.util.Set;

/**
 * @author by Administrator on 2018/3/19.
 */

public class MyWatched implements Watched {
    //TODO 使用Set比List防止重复添加
    //private List<Watcher> watchers = new ArrayList<>();
    private Set<Watcher> watchers = new HashSet<>();

    @Override
    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    @Override
    public void notifyWatchers(Object object) {
        for (Watcher watcher : watchers) {
            watcher.update(object);
        }
    }
}
