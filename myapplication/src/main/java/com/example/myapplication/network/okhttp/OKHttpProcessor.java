package com.example.myapplication.network.okhttp;

import android.os.Handler;
import android.util.Log;

import com.example.myapplication.network.ICallBack;
import com.example.myapplication.network.IHttpProcessor;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Tao.ZT.Zhang on 2017/8/29.
 */

public class OKHttpProcessor implements IHttpProcessor {
    private OkHttpClient okHttpClient;
    private Handler handler;
    private static final String TAG = "OKHttpProcessor";

    public OKHttpProcessor() {
        okHttpClient = new OkHttpClient();
        handler = new Handler();
    }

    @Override
    public void get(String url, Map<String, Object> params, final ICallBack callBack) {
        Log.i(TAG, "get: url==" + url);

        final Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: ");
                callBack.onFailed(e.toString());
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                Log.i(TAG, "onResponse: ");
                Log.i(TAG, "code==: " + response.code());
                if (response.isSuccessful()){
                    Log.i(TAG, "isSuccessful: ");
                    Log.i(TAG, "response.body(): "+ response.body().toString());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSuccess(response.body().toString());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void post(String url, Map<String, Object> params, final ICallBack callBack) {
        Log.i(TAG, "post:from Okhttp");
        RequestBody requestBody = appendBody(params);
        final Request request = new Request.Builder().url(url)
                .post(requestBody)
                .header("User-Agent", "a")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = response.body().string();
                    Log.i(TAG, "onResponse: code == " + response.code());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "run:result == "+ result);
                            callBack.onSuccess(result);
                        }
                    });

                }
            }
        });
    }

    private RequestBody appendBody(Map<String, Object> params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params == null || params.isEmpty()){
            return  builder.build();
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getKey().toString());
        }
        return  builder.build();
    }

    @Override
    public void put(String url, Map<String, Object> params, ICallBack callBack) {

    }
}
