package com.example.myapplication.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val count = 100
    private val binder = MyBinder()
    private val quit = false
    override fun onCreate() {
        Log.i(TAG, "onCreate: ${getCurrentThreadName()} ")

        super.onCreate()
        //        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                while ((!quit)){
//                    try {
//                        sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    count ++;
//                    if (count == 5){
//                        quit = true;
//                    }
//
//                }
//            }
//        }.start();
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "onBind: ${getCurrentThreadName()} ")
        return binder
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand: ${getCurrentThreadName()} ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onRebind(intent: Intent) {
        Log.i(TAG, "onRebind: ${getCurrentThreadName()}")
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.i(TAG, "onUnbind: ${getCurrentThreadName()} ")
        return true
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy:${getCurrentThreadName()} ")
        super.onDestroy()
    }

    inner class MyBinder : Binder() {
        fun getCount(): Int {
            return count
        }
    }

    private fun getCurrentThreadName(): String {
        return "CurrentThread is  ${Thread.currentThread().name}"
    }

    companion object {
        private const val TAG = "MyService"
    }
}