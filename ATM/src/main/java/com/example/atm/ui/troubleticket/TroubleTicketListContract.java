package com.example.atm.ui.troubleticket;

import com.example.atm.base.BasePresenter;
import com.example.atm.base.BaseView;
import com.example.atm.bean.TroubleTicket;
import com.example.atm.ui.sitelist.SiteListContract;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/10/25.
 */

public interface TroubleTicketListContract {

    interface View extends BaseView<Presenter> {
        public void showData(List<TroubleTicket.TTDataBean> data);
    }


    //Presenter
    abstract class Presenter extends BasePresenter<View> {
        public abstract void getAllTroubleTicketes(String loginID);
    }
}
