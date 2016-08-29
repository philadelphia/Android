package com.example.myapplication.utils;

import android.text.TextUtils;

/**
 * this Util Class is Designed for converting String Object To JAVA Base Data type
 * etc, int, long, double, float, boolean
 */
public class ConvertUtil {

    /**
     * convert String to int
     * @param string the argument need to be converted
     * @return result
     */
    public  static int convertStringToInt(String string){
        return TextUtils.isEmpty(string) ? 0 : Integer.parseInt(string.trim());
    }


    public  static long convertStringToLong(String string){
        return TextUtils.isEmpty(string) ? 0L : Long.parseLong(string.trim());
    }

    public  static float convertStringToFloat(String string){
        return TextUtils.isEmpty(string) ? 0f : Float.parseFloat(string.trim());
    }

    public  static double convertStringToDouble(String string){
        return TextUtils.isEmpty(string) ? 0f : Double.parseDouble(string.trim());
    }


    public  static boolean convertStringToBoolean(String string){
        return !TextUtils.isEmpty(string) && Boolean.parseBoolean(string.trim());
    }
}
