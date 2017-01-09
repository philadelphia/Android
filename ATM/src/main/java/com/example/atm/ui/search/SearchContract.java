package com.example.atm.ui.search;

import com.example.atm.base.BasePresenter;
import com.example.atm.base.BaseView;
import com.example.atm.entities.PCFilter;
import com.example.atm.ui.troubleticket.TroubleTicketListContract;

/**
 * Created by Tao.ZT.Zhang on 2016/11/1.
 */

public interface SearchContract {
    //View
    public  interface View extends BaseView<Presenter>{
        public void showData(PCFilter pcFilter);
    }

    public abstract class Presenter extends BasePresenter<View>{
        public abstract void fetchFilterData(String drc);

    }
}
