package com.example.atm.ui.search;


import android.util.Log;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.SiteData;
import com.example.atm.ui.search.model.ISearchResultModel;
import com.example.atm.ui.search.model.SearchResultModelImpl;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tao.ZT.Zhang on 2016/10/21.
 */

public class SearchResultPresenter extends SearchResultContract.Presenter {
    private ISearchResultModel searchResultModel;
    private static final String TAG = "SearchResultPresenter";
    public SearchResultPresenter(SearchResultContract.View view) {
       attach(view);
        baseView.setPresenter(this);
        searchResultModel = new SearchResultModelImpl();
    }

    @Override
    public void showSearchResult(String loginID, String siteName) {
        baseView.showDialog();
        ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
        Call<SiteData> siteResults = apiClient.getSiteResults(loginID, siteName);
        siteResults.enqueue(new Callback<SiteData>() {
            @Override
            public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                Log.i(TAG, "onResponse: " + response.code());
                Log.i(TAG, "onResponse: " + response.message());
                Log.i(TAG, "onResponse: " + response.body().toString());
                if (response.body().getSiteData().size() != 0){
                    baseView.onSuccess();
                    baseView.showSearchResult(response.body().getSiteData());

                    Log.i(TAG, "onResponse: size  " + response.body().getSiteData().size());
                }else {
                    Log.i(TAG, "onResponse: size == 0");
                    baseView.onFailed();
                }

            }

            @Override
            public void onFailure(Call<SiteData> call, Throwable t) {
                baseView.onFailed();

            }
        });
    }

    @Override
    public void start() {

    }
}
