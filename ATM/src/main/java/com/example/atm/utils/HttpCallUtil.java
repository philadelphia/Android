package com.example.atm.utils;

import android.util.Log;

import com.example.atm.bean.TroubleTicket;

import java.util.Set;

import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Tao.ZT.Zhang on 2016/10/13.
 */

public class HttpCallUtil {
    private static final String TAG = "HttpCallUtil";
    public  static <T> void cancelCall(Call <T> call){
        if (null != call){
            if (!call.isCanceled())
                call.cancel();
        }
    }


    public static boolean isResponseValid(retrofit2.Response response){
        if (response.code() == 200 && response.message().equalsIgnoreCase("OK")){
            return  true;
        }
        return false;
    }
    public static void printResponseInfomortion(retrofit2.Response response){
        Log.i(TAG, "code: " + response.code());
        Log.i(TAG, "message: " + response.message());
        Log.i(TAG, "isSuccessful: " + response.isSuccessful());
        Headers headers = response.headers();
        Set<String> names = headers.names();
        for (String name : names) {
            Log.i(TAG, "name==: " + name);
            Log.i(TAG, "header: " + headers.get(name));
        }



    }

}
