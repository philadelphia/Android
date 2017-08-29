package com.example.myapplication.network.volley;

import android.app.Application;
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

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by Tao.ZT.Zhang on 2017/8/29.
 */

public class XUtilProcessor implements IHttpProcessor {
    private static final String TAG ="VolleyProcessor";
    private RequestQueue requestQueue = null;

    public XUtilProcessor(Application application ) {
        x.Ext.init(application);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallBack callBack) {

    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        Log.i(TAG, "post: From XUtils");
        RequestParams requestParams = new RequestParams(url);
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                callBack.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callBack.onFailed(ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void put(String url, Map<String, Object> params, ICallBack callBack) {

    }
}
