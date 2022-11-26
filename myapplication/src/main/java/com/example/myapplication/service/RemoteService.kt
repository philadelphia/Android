package com.example.myapplication.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.myapplication.IRemoteService

/**
@Author zhang tao
@Date   7/13/22 10:52 PM
@Desc
 */

private const val TAG = "RemoteService"

class RemoteService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind: ")
        return RemoteBinder()
    }

    inner class RemoteBinder : IRemoteService.Stub() {
        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {
            Log.d(TAG, "basicTypes: ")
        }

        override fun getBasicInfo(): String {
            return "name name is lily, age == 18"
        }
    }
}