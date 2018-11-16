package com.by5388.rxdemo.rxJava;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by5388
 *         Administrator on 2018/3/18.
 */

public class MyWatched implements Watched {

    private List<Watcher> watchers = new ArrayList<>();


    @Override
    public void addWatcher(Watcher watcher) {
        this.watchers.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        this.watchers.remove(watcher);
    }

    @Override
    public void notifyWatchers(String str) {
        for (Watcher watcher : watchers) {
            watcher.update(str);
        }
    }
}
