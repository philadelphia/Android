package com.example.atm.login.presenter;

import android.util.Log;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.LoginResult;
import com.example.atm.login.module.LoginModel;
import com.example.atm.login.module.LoginModelImpl;
import com.example.atm.login.view.LoginView;
import com.example.atm.utils.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tao.ZT.Zhang on 2016/10/20.
 */

public class LoginPresenter implements  ILoginPresenter {
    private LoginView loginView;
    private LoginModel loginModel;
    private static final String TAG = "LoginPresenter";
    
    public LoginPresenter(LoginView loginView){
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
    }

    @Override
    public void login( ) {
        Log.i(TAG, "login: ");
        String userId = loginView.getUserID();
        String userPassword = loginView.getPassword();
        loginModel.saveUserInfo(userId, userPassword);
        loginView.showDialog();
        ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
        Call<LoginResult> login = apiClient.login(userId, userPassword);
        login.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.code() == 200 && response.message().equalsIgnoreCase("OK")) {
                    LoginResult loginResult = response.body();
                    if ("Successful".equals(loginResult
                            .getResult())) {
                       loginView.onSuccess();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
                    loginView.onFailed();
                    loginView.hideDialog();
            }
        });
    }

}
