package com.example.atm.ui.login;

import com.example.atm.base.BasePresenter;
import com.example.atm.base.BaseView;

/**
 * Created by Tao.ZT.Zhang on 2016/10/25.
 */

public interface LoginContract {

    //Model
    public interface Model{
        public void saveUserInfo(String userid,String password);
        public String getUserID();
        public String getUserPassword();

    }

    //Presenter
    public interface View extends BaseView<Presenter> {
        public String getUserID();
        public String getPassword();
        public void attemptLogin();

    }

    //View
    abstract class Presenter extends BasePresenter<View> {
        public abstract void login();

    }
}
