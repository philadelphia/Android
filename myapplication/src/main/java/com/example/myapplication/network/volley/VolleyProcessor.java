package com.example.myapplication.network.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.network.ICallBack;
import com.example.myapplication.network.IHttpProcessor;

import java.util.Map;

/**
 * Created by Tao.ZT.Zhang on 2017/8/29.
 */

public class VolleyProcessor implements IHttpProcessor {
    private static final String TAG ="VolleyProcessor";
    private RequestQueue requestQueue = null;

    public VolleyProcessor(Context context ) {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {

    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        Log.i(TAG, "post: From Volley");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailed(error.toString());
            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    public void put(String url, Map<String, Object> params, ICallBack callBack) {

    }
}
