package com.example.atm.ui.sitelist;

import com.example.atm.base.BasePresenter;
import com.example.atm.base.BaseView;
import com.example.atm.bean.SiteItem;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/10/25.
 */

public interface SiteListContract {
    //Model
    public interface Model {}

    //View

    public interface View extends BaseView<SiteListContract.Presenter> {
        public void showSiteList(List<SiteItem> siteList);

    }

    //Presenter
    public abstract class Presenter extends BasePresenter<View> {
        public abstract  void fetchSiteList(String userID);
    }

}
