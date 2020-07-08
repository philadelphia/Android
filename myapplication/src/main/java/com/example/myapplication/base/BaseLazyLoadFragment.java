package com.example.myapplication.base;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

/**
 * @author zhangtao
 * @date 2020/07/08
 */
public abstract class BaseLazyLoadFragment extends BaseFragment {
    protected boolean isViewInitialized;
    protected boolean isDataInitialized;
    protected boolean isVisibleToUser;

    public BaseLazyLoadFragment() {
        // Required empty public constructor

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewInitialized = true;
        prepareLoadData();
        initView();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint: " + isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser) {
            prepareLoadData();
        }
    }

    protected void prepareLoadData() {
        loadData();
    }

    protected void loadData() {
        if (isVisibleToUser && isViewInitialized && !isDataInitialized) {
            Log.i(TAG, "loadData: ");
            showProgressBar();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isDataInitialized = true;
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismissProgressBar();
                            onDataLoadSucceed();
                        }
                    });

                }
            }).start();
        }
    }

    protected abstract void showProgressBar();

    protected abstract void dismissProgressBar();

    protected abstract void onDataLoadSucceed();



    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}


