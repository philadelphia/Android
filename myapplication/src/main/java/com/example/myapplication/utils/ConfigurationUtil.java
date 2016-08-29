package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Tao.ZT.Zhang on 2016/8/29.
 */
public class ConfigurationUtil {

    public static String getManufacture() {
        return Build.MANUFACTURER;
    }

    public static String getProduct() {
        return Build.PRODUCT;
    }

    public static String getHardware() {
        return Build.HARDWARE;
    }

    public static String getDisplay() {
        return Build.DISPLAY;
    }

    public static int getWidth(Activity context) {
        return context.getResources().getDisplayMetrics().widthPixels;
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        return display.getWidth();
    }

    public static int getHeight(Activity context) {
        return  context.getResources().getDisplayMetrics().heightPixels;
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        return display.getHeight();
    }


    public static String getOSVersion() {
        return System.getProperty("os.version");
    }

    public static String getOSName() {
        return System.getProperty("os.name");
    }
}
