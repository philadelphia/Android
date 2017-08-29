package com.example.myapplication.network.okhttp;

import android.util.Log;

import com.example.myapplication.network.ICallBack;
import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Tao.ZT.Zhang on 2017/8/29.
 */

public abstract class HttpCallBack<T> implements ICallBack {
    private static final String TAG = "HttpCallBack";
    @Override
    public void onSuccess(String result) {
        Log.i(TAG, "onSuccess: " + result);
        Gson gson = new Gson();
        Class<?> clazz = analysisClassInfo(this);
        T objectResult = (T) gson.fromJson(result, clazz);
        onSuccess(objectResult);
    }

    private  Class<?> analysisClassInfo(Object object) {
        Type type = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        return (Class<?>) params[0];
    }


    @Override
    public void onFailed(String error) {
    }

    public abstract void onSuccess(T objectResult);
}

