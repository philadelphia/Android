package com.example.atm.utils;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tao.ZT.Zhang on 2016/9/22.
 */
public class MyRetrofit {
    private static final String TAG = "MyRetrofit";
    public static Retrofit initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Url.BASE_URL)//主机地址
                .build();

        return retrofit;
    }

    public static HttpLoggingInterceptor getHttpInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, message);
            }
        });

        return interceptor;
    }

    public static OkHttpClient getHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(getHttpInterceptor())
                .build();

        return client;
    }
}
