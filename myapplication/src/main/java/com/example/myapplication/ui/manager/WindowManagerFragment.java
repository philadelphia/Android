package com.example.myapplication.ui.manager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.utils.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WindowManagerFragment extends BaseFragment {


    public WindowManagerFragment() {
        // Required empty public constructor
    }


    @Override
    public int getNormalLayout() {
        return R.layout.fragment_window_manager;
    }
}
