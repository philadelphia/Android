package com.example.atm.ui.login;

import android.util.Log;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.LoginResult;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Tao.ZT.Zhang on 2016/10/20.
 */

public class LoginPresenter extends LoginContract.Presenter {
    private LoginContract.Model loginModel;
    private static final String TAG = "LoginPresenter";
    
    public LoginPresenter(LoginContract.View view){
        attach(view);
        loginModel = new LoginModel();
        view.setPresenter(this);
    }


    @Override
    public void login( ) {
        baseView.attemptLogin();
        Log.i(TAG, "login: ");
        String userId = baseView.getUserID();
        String userPassword = baseView.getPassword();
//        loginModel.saveUserInfo(userId, userPassword);
        loginModel.saveUserInfo("drc", "drc@123");
//        baseView.showDialog();
        ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
        Call<LoginResult> login = apiClient.login(userId, userPassword);
        login.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Log.i(TAG, "onResponse: ");
                HttpCallUtil.printResponseInfomortion(response);
                if (response.code() == 200 && response.message().equalsIgnoreCase("OK")) {
                    LoginResult loginResult = response.body();
                    Log.i(TAG, "onResponse: " + loginResult.toString());
                    if ("Successful".equals(loginResult
                            .getResult())) {
                        baseView.onSuccess();
                    }
                }else {
                    baseView.onFailed();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
                baseView.onFailed();
            }
        });
    }

    @Override
    public void start() {

    }




}
