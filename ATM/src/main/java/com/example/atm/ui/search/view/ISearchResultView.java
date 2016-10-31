package com.example.atm.ui.search.view;

import com.example.atm.base.BaseView;
import com.example.atm.bean.SiteItem;
import com.example.atm.ui.search.presenter.ISearchPresenter;
import com.example.atm.ui.search.presenter.ISearchResultPresenter;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/10/21.
 */

public interface ISearchResultView extends BaseView <ISearchResultPresenter>{
    public void showSearchResult(List<SiteItem> siteList);
}
