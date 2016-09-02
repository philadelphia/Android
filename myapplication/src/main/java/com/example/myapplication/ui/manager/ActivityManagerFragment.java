package com.example.myapplication.ui.manager;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;

import com.example.myapplication.R;

public class ActivityManagerFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ActivityManagerFragment";

    private Button btnTest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_activity_manager, null);
        initView(view);
        return view;

    }

    private void initView(View view) {
        btnTest = (Button) view.findViewById(R.id.btn_test);
        btnTest.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_test:
                testScroll();
                break;
        }
    }

    private void testScroll() {
        int[] locations = new int[2];
        btnTest.getLocationOnScreen(locations);
        Log.i(TAG, "testScroll: locations x---" + locations[0] + "\t" + "y--- " + locations[1]);
        Log.i(TAG, "testScroll: rawx" + btnTest.getRight());
//        ((View)btnTest.getParent()).scrollTo(-50,-600);
        btnTest.offsetLeftAndRight(400);
        Log.i(TAG, "testScroll: margin left" + btnTest.getLeft());
    }


}
