package com.example.atm.bean;

/**
 * Created by Tao.ZT.Zhang on 2016/9/22.
 */
public class User {
    private String LoginID;

    public User(String loginid){
        this.LoginID = loginid;
    }
    public String getLoginID() {
        return LoginID;
    }

    public void setLoginID(String loginID) {
        LoginID = loginID;
    }
}
