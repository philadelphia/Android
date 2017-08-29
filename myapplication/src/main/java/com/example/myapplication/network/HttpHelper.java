package com.example.myapplication.network;

import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Tao.ZT.Zhang on 2017/8/29.
 */

public class HttpHelper implements IHttpProcessor {
    private static IHttpProcessor mHttpProcessor = null;
    private static volatile HttpHelper instance;
    private static final String TAG = "HttpHelper";

    private HttpHelper(){

    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            synchronized (HttpHelper.class) {
                if (instance == null) {
                    return new HttpHelper();
                }
            }
        }
        return instance;
    }

    public static void init(IHttpProcessor httpProcessor) {
        mHttpProcessor = httpProcessor;
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {
        Log.i(TAG, "get: url==" + url);
        final String finalUrl = appendParams(url,params);
        mHttpProcessor.get(url,params,callBack);
    }

    @Override
    public void post(String url, Map<String, Object> params, ICallBack callBack) {
        Log.i(TAG, "post: url ==" + url);
        final String finalUrl = appendParams(url,params);
        mHttpProcessor.post(url, params, callBack);
    }

    private String appendParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()){
            return url;
        }

        StringBuilder sb = new StringBuilder(url);
        if (sb.indexOf("?") <= 0){
            sb.append("?");
        }else {
            if (!sb.toString().endsWith("?")){
                sb.append("&");
            }
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append(entry.getKey()).append("=").append(enCode(entry.getValue().toString()));
        }

        return sb.toString();
    }

    private static String  enCode(String s) {
        try {
            return URLEncoder.encode(s,"utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.i(TAG, "enCode: 转码异常");
            throw  new RuntimeException(e);

        }
    }

    @Override
    public void put(String url, Map<String, Object> params, ICallBack callBack) {
        final String finalUrl = appendParams(url,params);
        mHttpProcessor.get(url,params,callBack);
    }
}
