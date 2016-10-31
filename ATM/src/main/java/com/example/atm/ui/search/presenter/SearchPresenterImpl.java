package com.example.atm.ui.search.presenter;

import android.util.Log;
import android.widget.SearchView;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.entities.PCFilter;
import com.example.atm.ui.search.model.ISearchModel;
import com.example.atm.ui.search.model.SearchModelImpl;
import com.example.atm.ui.search.view.ISearchView;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tao.ZT.Zhang on 2016/10/21.
 */

public class SearchPresenterImpl implements  ISearchPresenter {
    private ISearchModel searchModel;
    private ISearchView searchView;

   public SearchPresenterImpl(ISearchView searchView){
       this.searchView = searchView;
       searchModel = new SearchModelImpl();
   }
    @Override
    public void fetchFilterData(String userID) {
        searchView.showDialog();
        ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
        Call<PCFilter> allProducts = apiClient.getAllProducts(userID);
        allProducts.enqueue(new Callback<PCFilter>() {
            @Override
            public void onResponse(Call<PCFilter> call, Response<PCFilter> response) {
                if (HttpCallUtil.isResponseValid(response)) {
                    searchView.hideDialog();
                    searchView.onSuccess();
                    searchView.showData(response.body());
                }
            }

            @Override
            public void onFailure(Call<PCFilter> call, Throwable t) {
                searchView.onFailed();
                searchView.hideDialog();
            }
        });
    }
}
