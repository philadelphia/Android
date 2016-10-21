package com.example.atm.login.module;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.atm.MainActivity;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.LoginResult;
import com.example.atm.login.view.LoginActivity;
import com.example.atm.utils.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

/**
 * Created by Tao.ZT.Zhang on 2016/10/20.
 */

public class LoginModelImpl implements LoginModel {

    @Override
    public void saveUserInfo(String userid, String password) {

    }

    @Override
    public String getUserID() {
        return null;
    }

    @Override
    public String getUserPassword() {
        return null;
    }

}
