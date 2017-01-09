package com.example.myapplication.ui.fragment.other;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.utils.NetworkUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomViewFragment extends Fragment {

    private static final String TAG = "CustomViewFragment";
    public CustomViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       LinearLayout  linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_custom_view, container,false);
        View view = inflater.inflate(R.layout.layout_test,linearLayout);
        Log.i(TAG, "onCreateView: getRootView  " + view.getRootView().getClass().getSimpleName());
        Intent intent = new Intent();
        return  view;
    }

}
