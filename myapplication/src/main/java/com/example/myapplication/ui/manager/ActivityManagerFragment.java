package com.example.myapplication.ui.manager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.utils.BaseFragment;
import com.example.myapplication.utils.ConfigurationUtil;
import com.example.myapplication.utils.ConvertUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityManagerFragment extends BaseFragment {

    private static final String TAG = "ActivityManagerFragment";

    public ActivityManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public int getNormalLayout() {
        return R.layout.fragment_activity_manager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Log.i(TAG, "onCreateView:"  + ConvertUtil.convertStringToBoolean("true"));

        Log.i(TAG, "getManufacture: " + ConfigurationUtil.getManufacture());
        Log.i(TAG, "getHardware: " + ConfigurationUtil.getHardware());
        Log.i(TAG, "getProduct: " + ConfigurationUtil.getProduct());
        Log.i(TAG, "getDisplay: " + ConfigurationUtil.getDisplay());
        Log.i(TAG, "getWidth: " + ConfigurationUtil.getWidth(getActivity()));
        Log.i(TAG, "getHeight: " + ConfigurationUtil.getHeight(getActivity()));

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
