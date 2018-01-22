package com.eallcn.rentcustomer.util;

import android.util.Log;

/**
 * Author：Hades on 8/5/2015 11:33
 * E_mail：li710611@163.com
 */
public class LogUtils {

//    public static boolean debug = BuildConfig.DEBUG;
    public static boolean debug = true;

    public static void d(String TAG,String info){
        if(LogUtils.debug){
            Log.d(TAG,info);
        }
    }

    public static void i(String TAG,String info){
        if(LogUtils.debug){
            Log.i(TAG, info);
        }
    }

    public static void e(String TAG,String info){
        if(LogUtils.debug){
            Log.e(TAG,info);
        }
    }

}
