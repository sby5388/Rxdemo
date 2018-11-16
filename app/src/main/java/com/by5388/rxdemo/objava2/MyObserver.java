package com.by5388.rxdemo.objava2;

import java.util.Observable;
import java.util.Observer;

/**
 * @author by Administrator on 2018/3/19.
 */

public class MyObserver implements Observer {
    private String name;

    public MyObserver(MyObservable observable, String name) {
        observable.addObserver(this);
        this.name = name;
    }

    public MyObserver(String name) {
        this.name = name;
    }


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MyObservable) {
            System.out.println(name + " : " + ((MyObservable) o).getMyNumber());
        }

    }


    public void deleteObservable(MyObservable observable) {
        observable.deleteObserver(this);
    }
}
