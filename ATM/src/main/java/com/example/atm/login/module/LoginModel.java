package com.example.atm.login.module;

/**
 * Created by Tao.ZT.Zhang on 2016/10/20.
 */

public interface LoginModel {
    public void saveUserInfo(String userid,String password);
    public String getUserID();
    public String getUserPassword();

}
