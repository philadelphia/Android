package com.example.atm.ui.troubleticket.presenter;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.TroubleTicket;
import com.example.atm.ui.troubleticket.view.ITroubleTicketList;
import com.example.atm.utils.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tao.ZT.Zhang on 2016/10/24.
 */

public class TroubleTicketListPresenterImpl implements ITroubleTicketListPresenter {
    private ITroubleTicketList troubleTicketListView;
    private static final String TAG = "TroubleTicketListPresenterImpl";
    public TroubleTicketListPresenterImpl(ITroubleTicketList troubleTicketListView) {
        this.troubleTicketListView = troubleTicketListView;
    }


    @Override
    public void getAllTroubleTicketes(String loginID) {
        ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
        Call<TroubleTicket> troubleTicketList = apiClient.getTroubleTicketList(loginID);
        troubleTicketList.enqueue(new Callback<TroubleTicket>() {
            @Override
            public void onResponse(Call<TroubleTicket> call, Response<TroubleTicket> response) {
//                Log.i(TAG, "onResponse:code ===  " + response.code());
//                Log.i(TAG, "onResponse: message == " + response.message());
//                Headers headers =  response.headers();
/*
                Map<String, List<String>> stringListMap = headers.toMultimap();
                for (Map.Entry<String,List<String>> entry : stringListMap.entrySet()
                        ) {
                    Log.i(TAG, "key== " + entry.getKey());
                    Log.i(TAG, "value'size ==: " + entry.getValue().size());
                    Log.i(TAG, "value== " + entry.getValue().toString());
                }*/
                troubleTicketListView.showData(response.body().getTTData());

            }

            @Override
            public void onFailure(Call<TroubleTicket> call, Throwable t) {

            }
        });
    }
}
