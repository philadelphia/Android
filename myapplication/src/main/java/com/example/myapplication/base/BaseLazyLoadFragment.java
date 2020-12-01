package com.example.myapplication.base;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zhangtao
 * @date 2020/07/08
 */
public abstract class BaseLazyLoadFragment extends BaseFragment {
    protected boolean isViewInitialized;
    protected boolean isDataInitialized;
    protected boolean isVisibleToUser;
    private boolean isFirstLoad = true;

    public BaseLazyLoadFragment() {
        // Required empty public constructor

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            isFirstLoad = false;
            loadData();
        }
    }

    protected void loadData() {
        Log.i(TAG, "loadData: ");
        showProgressBar();
        new Thread(() -> {
            try {
                Thread.sleep(300L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dismissProgressBar();
                    onDataLoadSucceed();
                }
            });

        }).start();

    }

    protected abstract void showProgressBar();

    protected abstract void dismissProgressBar();

    protected abstract void onDataLoadSucceed();


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}


