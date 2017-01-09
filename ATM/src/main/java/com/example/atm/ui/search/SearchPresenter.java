package com.example.atm.ui.search;


import com.example.atm.apiInterface.ApiClient;
import com.example.atm.entities.PCFilter;
import com.example.atm.utils.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tao.ZT.Zhang on 2016/11/1.
 */

public class SearchPresenter extends SearchContract.Presenter {

    public SearchPresenter(SearchContract.View view){
        attach(view);
        view.setPresenter(this);

    }
    @Override
    public void fetchFilterData(String userID) {
        baseView.showDialog();
        ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
        Call<PCFilter> allProducts = apiClient.getAllProducts(userID);
        allProducts.enqueue(new Callback<PCFilter>() {
            @Override
            public void onResponse(Call<PCFilter> call, Response<PCFilter> response) {
                baseView.hideDialog();
                baseView.showData(response.body());
            }

            @Override
            public void onFailure(Call<PCFilter> call, Throwable t) {
                    baseView.hideDialog();
            }
        });

    }

    @Override
    public void start() {

    }
}
