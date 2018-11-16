package com.by5388.rxdemo.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * RxJava的变换操作
 * 可指定线程（包括运行、回调）
 *
 * @author by Administrator on 2018/3/22.
 */

public class RxUtilChange {

    private final static String TAG = RxUtilChange.class.getSimpleName();

    private Context context;
    private final static String BAIDUPATH = "https://www.baidu.com/s?wd=%E4%BB%8A%E6%97%A5%E6%96%B0%E9%B2%9C%E4%BA%8B&tn=SE_PclogoS_8whnvm25&sa=ire_dl_gh_logo&rsv_dl=igh_logo_pcs";

    public RxUtilChange(updateView view) {
        this.context = (Context) view;
    }


    public interface updateView {
        void updateView(Bitmap bitmap);
    }

    /*
     除了多样的Observable创建方式，RxJava还有一个神奇的操作就是变换。
     通过自己定义的方法，你可以将输入的值变换成另一
     种类型再输出(比如输入url，输出bitmap)，单一变换、批量变换、
     甚至实现双重变换，嵌套两重异步操作！
     并且代码格式一如既往的干净平整。是不是很牛？
     */

    //TODO 网络下载照片的应当通过新的线程去实现
    public void mapChange(String path) {

        Observable observable = Observable.just(path);
        observable.map(new Func1<String, Bitmap>() {
            @Override
            public Bitmap call(String path) {
                return null;
            }
        });
    }


    /**
     * 被观察者向观察者发送数据时，进行过滤
     * observable.filter.call 与subsribe.Action.call 相结合才有效果
     */

    public static void filter() {

        Integer[] items = {1, 2, 3, 4, 5, 6, 7};
//        Observable observable = Observable.just(1, 2, 3, 4, 5, 6, 7);
        Observable observable = Observable.from(items);
        //过滤器：
        // Interger:参数类型，Boolean：返回值类型
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer o) {
                return o > 3;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer o) {
                Log.d(TAG, "filter:call: " + o);
            }
        });

        //TODO  一个过滤失败的demo
        //使用just创建的发布者不适合filter??
        Observable observable2 = Observable.just(1, 2, 3, 4, 5, 6, 7);
        observable2.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer o) {
                return o > 3;
            }
        });

        observable2.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer o) {
                Log.d(TAG, "filter2:call: " + o);
            }
        });
    }


    public static void filter2() {
        Observable.range(1, 10).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer % 2 == 0;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }
}
