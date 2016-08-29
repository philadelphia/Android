package com.example.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Tao.ZT.Zhang  on 2016/8/29.
 */

public class NetworkUtil {

    public NetworkUtil() {

    }

    public  static ConnectivityManager getConectivityManager(Context context){
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public static  boolean isNetworkAvailable(Context context){
        if (context != null) {
            NetworkInfo activeNetworkInfo = getConectivityManager(context).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting();

            }

        }

        return  false;
    }

    public  static boolean isWifiNetwork(Context context){
        if (context != null) {
            NetworkInfo activeNetworkInfo = getConectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (activeNetworkInfo != null){
                return  activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting();
            }
        }

        return  false;
    }

    public  static boolean isMobileNetwork(Context context){
        if (context != null) {

            NetworkInfo activeNetworkInfo = getConectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (activeNetworkInfo != null){
                return  activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting();
            }
        }

        return  false;
    }

    public  static boolean isMobileRoamNetwork(Context context){
        if (context != null) {
            NetworkInfo activeNetworkInfo = getConectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (activeNetworkInfo != null){
                return  activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.isRoaming();
            }
        }

        return  false;
    }

    public  static boolean isBluetoothAvailable(Context context){
        if (context != null) {
            NetworkInfo activeNetworkInfo = getConectivityManager(context).getNetworkInfo(ConnectivityManager.TYPE_BLUETOOTH);

            if (activeNetworkInfo != null){
                return  activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting() && activeNetworkInfo.isRoaming();
            }
        }

        return  false;
    }
}
