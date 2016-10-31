package com.example.atm.ui.search.view;

import com.example.atm.base.BaseView;
import com.example.atm.bean.SiteItem;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/10/21.
 */

public interface IQueryResultView extends BaseView {
    public void showQueryResult(List<SiteItem> siteList);
}
