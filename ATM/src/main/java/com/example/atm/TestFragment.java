package com.example.atm;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.LoginResult;

import com.example.atm.bean.SiteData;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements View.OnClickListener {

    private final String BASE_URL = "http://220.225.253.146";
    private Button btnLogin;
    private Button btn_siteData;
    private TextView tvLoginresult;
    private TextView tvSiteDatas;
    View view;
    /**
     * Result : Successful
     */

    private String Result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        btn_siteData  = (Button) view.findViewById(R.id.btn_getsites);

        tvLoginresult = (TextView) view.findViewById(R.id.tv_loginresult);
        tvSiteDatas = (TextView) view.findViewById(R.id.tv_sitedatas);

        btnLogin.setOnClickListener(this);
        btn_siteData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                try {
                    login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn_getsites:
                getSiteDatas();
                break;
            default:
                break;
        }

    }


public Retrofit initRetrofit(){
    //1.创建Retrofit对象
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())//解析方法
            .baseUrl(BASE_URL)//主机地址
            .build();

    return  retrofit;
}

    private void login() throws IOException {

        Retrofit retrofit = initRetrofit();
        //2.创建访问API的请求
        ApiClient service = retrofit.create(ApiClient.class);
        Call<LoginResult> call = service.login("drc", "drc@123");



        //同步请求
//        try {
//            Response<LoginResult> execute = call.execute();
//            Log.i(TAG, "login: " + execute.body().toString());
//
//        }catch (Exception e){
//
//        }

        //3.发送请求
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                //4.处理结果
                if (response.isSuccessful()){
                    LoginResult result = response.body();
                    if (result != null){
                        tvLoginresult.setText(result.getResult());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
            }
        });




    }


    public void getSiteDatas() {
       Retrofit retrofit = initRetrofit();

        //2.创建访问API的请求
        ApiClient siteList = retrofit.create(ApiClient.class);
        Call<SiteData> call = siteList.getAllSites("drc");

        call.enqueue(new Callback<SiteData>() {
            @Override
            public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                if (response.isSuccessful()){
                }else {
                }

            }

            @Override
            public void onFailure(Call<SiteData> call, Throwable t) {

            }
        });

    }
}
