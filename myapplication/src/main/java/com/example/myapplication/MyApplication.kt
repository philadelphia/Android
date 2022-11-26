package com.example.myapplication

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.didichuxing.doraemonkit.DoraemonKit.install
import com.example.myapplication.network.HttpHelper
import com.example.myapplication.network.volley.XUtilProcessor
import com.example.myapplication.observer.ApplicationObserver

/**
 * Created by Tao.ZT.Zhang on 2017/8/29.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        //        HttpHelper.init(new VolleyProcessor(this));
        HttpHelper.init(XUtilProcessor(this))
        //        HttpHelper.init(new OKHttpProcessor());
//        LeakCanary.install(this);d
        install(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationObserver())
    }
}