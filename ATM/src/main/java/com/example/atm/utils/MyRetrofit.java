package com.example.atm.utils;


import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
    private Retrofit retrofit;
    private static volatile  MyRetrofit instance;
    private MyRetrofit(){
        Log.i(TAG, "MyRetrofit: ----begin to new A class---");
        retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)//主机地址
                .client(getHttpClient())
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

   public static MyRetrofit getInstance(){
        return  RetrofitHolder.myRetrofit;
   }

    public static MyRetrofit getInstance2(){
        Log.i(TAG, "getInstance: myretorfit's hashcode==  " + RetrofitHolder.myRetrofit.hashCode());
       if (instance == null){
           synchronized (MyRetrofit.class){
               if (instance == null){
                   instance = new MyRetrofit();
                   return  instance;
               }
           }
       }
        return instance;


    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

    public static HttpLoggingInterceptor getHttpInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i(TAG, message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public static OkHttpClient getHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(getHttpInterceptor())
                .cache(getCache())
                .build();

        return client;
    }

    public  static class RetrofitHolder{
        private static MyRetrofit myRetrofit = new MyRetrofit();

    }

    public static Cache getCache(){
//        File cacheFile = new File(Environment.getExternalStoragePublicDirectory(""), "[缓存目录ATM]");
        File cacheFile = Environment.getExternalStoragePublicDirectory("[缓存目录ATM]");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        return  cache;
    }

}
