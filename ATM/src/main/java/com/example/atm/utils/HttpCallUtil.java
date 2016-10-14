package com.example.atm.utils;

import com.example.atm.bean.TroubleTicket;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Tao.ZT.Zhang on 2016/10/13.
 */

public class HttpCallUtil {
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

}
