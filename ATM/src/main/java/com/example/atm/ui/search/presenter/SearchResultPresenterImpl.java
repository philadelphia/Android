package com.example.atm.ui.search.presenter;

import android.util.Log;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.SiteData;
import com.example.atm.ui.search.model.ISearchResultModel;
import com.example.atm.ui.search.model.SearchResultModelImpl;
import com.example.atm.ui.search.view.ISearchResultView;
import com.example.atm.utils.MyRetrofit;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tao.ZT.Zhang on 2016/10/21.
 */

public class SearchResultPresenterImpl implements ISearchResultPresenter {
    private ISearchResultView searchResultView;
    private ISearchResultModel searchResultModel;

    public SearchResultPresenterImpl(ISearchResultView searchResultView) {
        this.searchResultView = searchResultView;
        searchResultModel = new SearchResultModelImpl();
    }

    @Override
    public void showSearchResult(String loginID, String siteName) {
        searchResultView.showDialog();
        ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
        Call<SiteData> siteResults = apiClient.getSiteResults(loginID, siteName);
        siteResults.enqueue(new Callback<SiteData>() {
            @Override
            public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                searchResultView.onSuccess();
                searchResultView.hideDialog();
                searchResultView.showSearchResult(response.body().getSiteData());

            }

            @Override
            public void onFailure(Call<SiteData> call, Throwable t) {

                searchResultView.onFailed();
            }
        });
    }
}
