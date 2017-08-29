package com.example.myapplication.network;

import java.util.Map;

/**
 * Created by Tao.ZT.Zhang on 2017/8/29.
 */

public interface IHttpProcessor {
    void get(String url, Map<String, Object> params, ICallBack callBack);

    void post(String url, Map<String, Object> params, ICallBack callBack);

    void put(String url, Map<String, Object> params, ICallBack callBack);
}
