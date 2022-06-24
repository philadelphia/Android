package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;

import com.didichuxing.doraemonkit.DoraemonKit;
import com.example.myapplication.network.HttpHelper;
import com.example.myapplication.network.volley.XUtilProcessor;
import com.example.myapplication.observer.ApplicationObserver;


/**
 * Created by Tao.ZT.Zhang on 2017/8/29.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        //        HttpHelper.init(new VolleyProcessor(this));
        HttpHelper.init(new XUtilProcessor(this));
//        HttpHelper.init(new OKHttpProcessor());
//        LeakCanary.install(this);d
        DoraemonKit.install(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ApplicationObserver());
    }

}
