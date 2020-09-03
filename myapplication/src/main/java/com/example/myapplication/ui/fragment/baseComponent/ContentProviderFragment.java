package com.example.myapplication.ui.fragment.baseComponent;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseLazyLoadFragment;

import butterknife.BindView;

public class ContentProviderFragment extends BaseLazyLoadFragment {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_content_provider;
    }

    @Override
    protected void initView() {

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void dismissProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDataLoadSucceed() {
        Log.i(TAG, "onDataLoadSucceed: ");
        scrollView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean getUserVisibleHint() {
        Log.i(TAG, "getUserVisibleHint: " + super.getUserVisibleHint());
        return super.getUserVisibleHint();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }


}
