package com.example.rxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private     Button btnTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        btnTest = (Button) findViewById(R.id.btn_test);
        btnTest.setOnClickListener(this);
    }

    public static void testRXAndroid() {
        Log.i(TAG, "testRXAndroid: ");
        final String[] words = {"Hello", "Hi", "Aloha"};
        //创建被观察对象
        Observable observable = Observable.from(words);
        Observable <List<String>> obs1 = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                Log.i(TAG, "call: ");
              subscriber.onNext(Arrays.asList(words));
            }
        });


        //创建观察者/订阅者
        Subscriber<List<String>> subscriber = new Subscriber<List<String>>() {
            @Override
            public void onNext(List<String> strings) {
                for (String str: strings) {
                    Log.i(TAG, "onNext: " + str);
                }
            }

            @Override
            public void onCompleted() {
                Log.d(TAG, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error!");
            }
        };

        //注册观察者/订阅者
        obs1.subscribe(subscriber);
    }
    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick: ");
        switch (view.getId()){
            case R.id.btn_test:
                testRXAndroid();
                break;

            default:
                break;
        }
    }
}
