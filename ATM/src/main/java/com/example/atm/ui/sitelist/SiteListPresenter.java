package com.example.atm.ui.sitelist;

import android.util.Log;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.SiteData;
import com.example.atm.utils.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tao.ZT.Zhang on 2016/10/21.
 */

public class SiteListPresenter extends SiteListContract.Presenter {
    private SiteListContract.Model siteListModel;
    private static final String TAG = "SiteListPresenterImpl";

    public SiteListPresenter(SiteListContract.View siteListView) {
        attach(siteListView);
        siteListModel = new SiteListModel();
        siteListView.setPresenter(this);
    }

    @Override
    public void fetchSiteList(String userID) {
        baseView.showDialog();
        ApiClient siteList = MyRetrofit.getInstance().create(ApiClient.class);
        Call<SiteData> allSites = siteList.getAllSites(userID);
        allSites.enqueue(new Callback<SiteData>() {
            @Override
            public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                if(response.body() != null){
                    Log.i(TAG, "onResponse: "+response.body().toString());
                    baseView.hideDialog();
                    baseView.onSuccess();
                    baseView.showSiteList(response.body().getSiteData());
                }
            }

            @Override
            public void onFailure(Call<SiteData> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
                baseView.onFailed();
                baseView.hideDialog();
            }
        });
    }

    @Override
    public void start() {

    }
}
