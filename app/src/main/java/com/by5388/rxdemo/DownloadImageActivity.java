package com.by5388.rxdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.by5388.rxdemo.utils.DownLoadUtil;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DownloadImageActivity extends AppCompatActivity {

    final static String TAG = DownloadImageActivity.class.getSimpleName();
    private Button button;
    private ImageView imageView;
    DownLoadUtil util;
    public static final String IMAGEPATH = "https://by5388.wodemo.net/down/313034/%E4%BA%9A%E9%A9%AC%E9%80%8A.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "是否关闭该页面？", Snackbar.LENGTH_LONG)
                    .setAction("关闭", v -> {
                        finish();
                    }).show();
        });
        util = new DownLoadUtil();
        initView();


    }


    void initView() {
        imageView = findViewById(R.id.download_imageView);
        button = findViewById(R.id.download_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //subscribeOn(Schedulers.io()):后台(被观察者)运行在网络线程上
                //observeOn(AndroidSchedulers.mainThread()):观察者运行在主线程（UI线程）上
                //subscribe():订阅关系-->观察者
                //类似于智能客厅中 无线场景界面的更新操作
                util.downloadImage(IMAGEPATH)

                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<byte[]>() {
                            @Override
                            public void onCompleted() {
                                //一般是关闭下载对话框/进度条
                                Log.d(TAG, "onCompleted: ");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError: " + e.getMessage());
                            }

                            @Override
                            public void onNext(byte[] bytes) {
                                //调用完成
                                //TODO byte[] 转成图片？？？
                                Log.d(TAG, "onNext: " + bytes.length);
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                imageView.setImageBitmap(bitmap);
                            }
                        });
            }
        });

    }

}
