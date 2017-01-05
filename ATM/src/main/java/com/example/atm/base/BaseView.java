package com.example.atm.base;

/**
 * Created by Tao.ZT.Zhang on 2016/10/21.
 */

public interface BaseView <T>{
    public void setPresenter(T presenter);
    public void onSuccess();
    public void onFailed();
    public void showDialog();
    public void hideDialog();
}
