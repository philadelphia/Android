package com.example.myapplication.utils;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public abstract class BaseFragment extends Fragment {


    public abstract int getNormalLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!NetworkUtil.isNetworkAvailable(getContext())) {
            return inflater.inflate(R.layout.fragment_base, container, false);
        } else {
            return inflater.inflate(getNormalLayout(), container, false);
        }

    }

}
