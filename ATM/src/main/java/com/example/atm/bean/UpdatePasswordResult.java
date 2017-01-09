package com.example.atm.bean;

/**
 * Created by Tao.ZT.Zhang on 2016/10/13.
 */

public class UpdatePasswordResult {

    /**
     * Message : Successfully Updated
     */

    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    @Override
    public String toString() {
        return "UpdatePasswordResult{" +
                "Message='" + Message + '\'' +
                '}';
    }
}
