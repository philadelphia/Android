package com.example.atm.bean;

/**
 * Created by Tao.ZT.Zhang on 2017/1/6.
 */

public class Test {
    private String msg;
    private String data;

    public Test(String data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Test{" +
                "data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
