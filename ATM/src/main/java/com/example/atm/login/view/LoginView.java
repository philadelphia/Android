package com.example.atm.login.view;

/**
 * Created by Tao.ZT.Zhang on 2016/10/20.
 */

public interface LoginView {
    public String getUserID();
    public String getPassword();
    public void showDialog();
    public void hideDialog();
    public void onSuccess();
    public void onFailed();
}
