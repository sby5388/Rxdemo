package com.by5388.rxdemo.objava2;

/**
 * @author by Administrator on 2018/3/19.
 */

public class Test {
    public static void main(String[] agrs) {
        MyObservable observable = MyObservable.getInstance();
        MyObserver observer1 = new MyObserver(observable, "A");
        MyObserver observer2 = new MyObserver(observable, "B");
        MyObserver observer3 = new MyObserver(observable, "C");


        observable.setMyNumber(20);
        observable.setMyNumber(21);
        observable.setMyNumber(21);

        //MyObserver observer4 = new MyObserver(observable, "D");
        MyObserver observer4 = new MyObserver("D");
        observable.addObserver(observer4);

        observable.setMyNumber(22);
        observable.deleteObserver(observer1);
        observable.deleteObserver(observer1);
        observable.setMyNumber(23);
        observer2.deleteObservable(observable);
        observable.setMyNumber(24);
    }


}
