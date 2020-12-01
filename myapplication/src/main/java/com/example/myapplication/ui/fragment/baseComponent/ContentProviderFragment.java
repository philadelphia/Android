package com.example.myapplication.ui.fragment.baseComponent;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseLazyLoadFragment;
import com.example.myapplication.databinding.FragmentContentProviderBinding;

public class ContentProviderFragment extends BaseLazyLoadFragment {
    private FragmentContentProviderBinding binding;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_content_provider;
    }

    @Override
    protected void initView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentContentProviderBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    protected void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void dismissProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDataLoadSucceed() {
        Log.i(TAG, "onDataLoadSucceed: ");
        binding.scrollView.setVisibility(View.VISIBLE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }


}
