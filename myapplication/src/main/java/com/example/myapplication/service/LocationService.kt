package com.example.myapplication.service

import androidx.lifecycle.LifecycleService
import com.example.myapplication.observer.MyLocationObserver

/**
 * @Author zhang tao
 * @Date 9/21/21 10:28 PM
 * @Desc
 */
class LocationService : LifecycleService() {
    init {
        lifecycle.addObserver(MyLocationObserver(this))
    }
}