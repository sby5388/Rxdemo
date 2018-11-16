package com.by5388.rxdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.by5388.rxdemo.kotlin.KotlinClassActivity;
import com.by5388.rxdemo.net.NetWorkActivity;
import com.by5388.rxdemo.utils.RxUtilChange;
import com.by5388.rxdemo.utils.RxUtils;
import com.by5388.rxdemo.utils2.RxUtils2;
import com.by5388.rxdemo.wether.WeatherActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RxUtilChange.updateView {

    /**
     * 测试数据
     */
    List<Person> persons;

    ImageView imageView;

    RxUtilChange change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    void initData() {
        persons = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            persons.add(new Person(i, "person" + i));
        }
        change = new RxUtilChange(this);
    }

    void initView() {

        imageView = this.findViewById(R.id.image);
        this.findViewById(R.id.button1).setOnClickListener(this);
        this.findViewById(R.id.button2).setOnClickListener(this);
        this.findViewById(R.id.button3).setOnClickListener(this);
        this.findViewById(R.id.button4).setOnClickListener(this);
        this.findViewById(R.id.button5).setOnClickListener(this);
        this.findViewById(R.id.button50).setOnClickListener(this);
        this.findViewById(R.id.button6).setOnClickListener(this);
        this.findViewById(R.id.button7).setOnClickListener(this);
        //变换操作
        this.findViewById(R.id.button8).setOnClickListener(this);
        this.findViewById(R.id.button9).setOnClickListener(this);
        this.findViewById(R.id.button10).setOnClickListener(this);
        this.findViewById(R.id.button11).setOnClickListener(this);
        this.findViewById(R.id.button12).setOnClickListener(this);
        this.findViewById(R.id.button13).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                RxUtils.createObservable();
                break;
            case R.id.button2:
                RxUtils2.createObservable();
                break;
            case R.id.button3:
                RxUtils.createObservable2();
                break;
            case R.id.button4:
                fromObservable();
                break;
            case R.id.button5:
                justObservable();
                break;
            case R.id.button50:
                RxUtils.intervalObservable();
                break;
            case R.id.button6:
                RxUtils.timerObservable();
                break;
            case R.id.button7:
                RxUtils.rangeObservable();
                break;
            case R.id.button8:
                //change.mapChange("https://by5388.wodemo.net/down/313034/%E4%BA%9A%E9%A9%AC%E9%80%8A.jpg");
                break;
            case R.id.button9:
                RxUtilChange.filter();
                break;
            case R.id.button10:
                Intent intent = new Intent(MainActivity.this, DownloadImageActivity.class);
                startActivity(intent);
                break;
            case R.id.button11:
                startActivity(new Intent(this, NetWorkActivity.class));
                break;
            case R.id.button12:
                startActivity(new Intent(this, KotlinClassActivity.class));
                break;
            case R.id.button13:
                startActivity(new Intent(this, WeatherActivity.class));
                break;
            default:
                break;

        }
    }


    /**
     * 使用just实现Rx
     */

    void justObservable() {
        RxUtils.justObservable(persons);
    }


    /**
     * 使用from实现Rx
     */
    void fromObservable() {
        RxUtils.fromObservable(persons);
    }


    @Override
    public void updateView(Bitmap bitmap) {
        //TODO 待实现
        imageView.setImageBitmap(bitmap);
    }
}
