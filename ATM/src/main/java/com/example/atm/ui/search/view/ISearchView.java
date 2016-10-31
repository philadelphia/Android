package com.example.atm.ui.search.view;

import com.example.atm.base.BaseView;
import com.example.atm.entities.PCFilter;
import com.example.atm.ui.search.presenter.ISearchPresenter;

/**
 * Created by Tao.ZT.Zhang on 2016/10/21.
 */

public interface ISearchView extends BaseView<ISearchPresenter> {
    public void showData(PCFilter pcFilter);

}
