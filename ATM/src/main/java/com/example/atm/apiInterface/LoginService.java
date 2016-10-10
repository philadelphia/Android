package com.example.atm.apiInterface;

import com.example.atm.bean.LoginResult;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Tao.ZT.Zhang on 2016/8/16.
 */
public interface LoginService {
    @POST("/WebAPI/API/Login")
    Call<LoginResult> login(@Header("LoginID") String id, @Header("Password") String password);
}
