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

public class QueryResultPresenter extends QueryResultContract.Presenter {
    private ISearchResultModel searchResultModel;
    private static final String TAG = "QueryResultPresenter";
    public QueryResultPresenter(QueryResultContract.View view) {
        attach(view);
        baseView.setPresenter(this);
        searchResultModel = new SearchResultModelImpl();
    }

    @Override
    public void QueryResult(String loginID, int productid, int circleid, int clusterid) {
        Log.i(TAG, "showQueryResult: ");
        Log.i(TAG, "productid: " + productid);
        Log.i(TAG, "circleid: " + circleid);
        Log.i(TAG, "clusterid: " + clusterid);
        baseView.showDialog();
        ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);

        if (productid != 0 && clusterid == 0 && circleid == 0)
        {
            Log.i (TAG, "1 parameters");
            Log.i(TAG, "productid==:  " +productid);
            Call<SiteData> queryProductCall = apiClient.queryProduct(loginID, String.valueOf(productid));
            queryProductCall.enqueue(new Callback<SiteData>() {
                @Override
                public void onResponse(Call<SiteData> call, Response<SiteData> response) {

                    if (HttpCallUtil.isResponseValid(response)){
                        baseView.onSuccess();
                        baseView.showQueryResult(response.body().getSiteData());
                    }
                }

                @Override
                public void onFailure(Call<SiteData> call, Throwable t) {
                   
                }
            });

        }
        else if (productid != 0 && circleid != 0 && clusterid == 0)
        {
            Log.i (TAG, "2 parameters");
            Call<SiteData> queryCircleCall = apiClient.queryCircle(loginID, String.valueOf(productid), String.valueOf(circleid));
            queryCircleCall.enqueue(new Callback<SiteData>() {
                @Override
                public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                    if (HttpCallUtil.isResponseValid(response)){
                        baseView.onSuccess();
                        baseView.showQueryResult(response.body().getSiteData());
                    }
                }

                @Override
                public void onFailure(Call<SiteData> call, Throwable t) {
                    baseView.onFailed();
                }
            });

        }
        else if (productid != 0 && circleid != 0 && clusterid != 0)
        {
            Log.i (TAG, "3 parameters");
            Call<SiteData> queryClusterCall = apiClient.queryCluster(loginID, String.valueOf(productid), String.valueOf(circleid), String.valueOf(clusterid));
            queryClusterCall.enqueue(new Callback<SiteData>() {
                @Override
                public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                    if (HttpCallUtil.isResponseValid(response)){
                        baseView.onSuccess();
                        baseView.showQueryResult(response.body().getSiteData());
                    }
                }

                @Override
                public void onFailure(Call<SiteData> call, Throwable t) {
                    baseView.onFailed();
                }
            });
        }
    }

    @Override
    public void start() {

    }
}
