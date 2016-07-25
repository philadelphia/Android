package com.example.myapplication.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

public class ActivityFragment extends Fragment implements View.OnClickListener {

    private Button btn1;
    private final String LOG = ActivityFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    public void initView(View view){
        btn1 =(Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                jumpToSecondActivity();
        }

    }

    private void jumpToSecondActivity() {

    }
}
