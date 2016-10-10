package com.example.atm.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tao.ZT.Zhang on 2016/9/22.
 */
public class MyRetrofit {
    public static Retrofit initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl(Url.BASE_URL)//主机地址
                .build();

        return retrofit;
    }
}
