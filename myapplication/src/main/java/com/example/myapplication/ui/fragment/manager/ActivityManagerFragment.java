package com.example.myapplication.ui.fragment.manager;


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

    private Button btn_ScrollBy;
    private Button btn_ScrollTo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_activity_manager, null);
        initView(view);
        return view;

    }

    private void initView(View view) {
        btn_ScrollBy = (Button) view.findViewById(R.id.btn_testBy);
        btn_ScrollTo = (Button) view.findViewById(R.id.btn_testTo);
        btn_ScrollBy.setOnClickListener(this);
        btn_ScrollTo.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_testBy:
                testScrollBy();
                break;
            case R.id.btn_testTo:
                testeScrollTo();
                break;
            default:
                break;
        }
    }



    private void testScrollBy() {
        int[] locations = new int[2];
        btn_ScrollBy.getLocationOnScreen(locations);
        Log.i(TAG, "testScroll: locations x---" + locations[0] + "\t" + "y--- " + locations[1]);
        Log.i(TAG, "testScroll: rawx" + btn_ScrollBy.getRight());
        btn_ScrollBy.offsetLeftAndRight(400);
        Log.i(TAG, "testScroll: margin left" + btn_ScrollBy.getLeft());
    }


    private void testeScrollTo() {
        int[] locations = new int[2];
        btn_ScrollTo.getLocationOnScreen(locations);
        Log.i(TAG, "testScroll: locations x--- " + locations[0] + "\t" + "y--- " + locations[1]);
        Log.i(TAG, "testScroll: rawx" + btn_ScrollTo.getRight());
        btn_ScrollTo.scrollTo(-10,-10);
        Log.i(TAG, "testScroll: margin left " + btn_ScrollTo.getLeft());

    }

}
