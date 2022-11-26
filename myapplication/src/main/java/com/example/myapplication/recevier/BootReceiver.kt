package com.example.myapplication.recevier

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
@Author zhang tao
@Date   7/7/22 9:45 PM
@Desc
 */
private const val TAG = "BootReceiver"

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_BOOT_COMPLETED -> Log.e(TAG, "onReceive: boot completed")
            Intent.ACTION_SHUTDOWN -> Log.e(TAG, "onReceive: shutdown ")
            else ->{
                Log.e(TAG, "onReceive: ${intent?.action}" )
            }
        }
    }
}