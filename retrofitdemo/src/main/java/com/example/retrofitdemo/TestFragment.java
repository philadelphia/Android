package com.example.retrofitdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrofitdemo.bean.LoginResult;
import com.example.retrofitdemo.serviceInterface.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "TestFragment";
    private final String BASE_URL = "http://220.225.253.146";
    Button btnLogin;
    TextView tvLoginresult;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_test, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        tvLoginresult = (TextView) view.findViewById(R.id.tv_loginresult);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                Log.i(TAG, "onClick: btn");
                login();
                break;

            default:
                break;
        }

    }



    private void login() {
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .baseUrl(BASE_URL)//主机地址
                .build();

        //2.创建访问API的请求
        LoginService service = retrofit.create(LoginService.class);
        Call<LoginResult> call = service.login("drc", "drc@123");

        //3.发送请求
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                //4.处理结果
                if (response.isSuccessful()){
                    LoginResult result = response.body();
                    Log.i(TAG, "header: " + response.headers().toString());

                    if (result != null){
                        Log.i(TAG, "onResponse: " + result.toString());
                        tvLoginresult.setText(result.getResult());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }
        });
    }
}
