package com.example.atm.base;

import java.lang.ref.WeakReference;

/**
 * Created by Tao.ZT.Zhang on 2016/10/24.
 */

public abstract class BasePresenter<T extends BaseView> {
    public   WeakReference<BaseView> weakReference;
    public   T baseView;
    public   abstract  void start();

    public void   attach(T baseView){
        this.baseView = baseView;
        this.weakReference = new WeakReference<BaseView>(baseView);
    }

    public void dettatch(T baseView){
        weakReference.clear();
    }
}
