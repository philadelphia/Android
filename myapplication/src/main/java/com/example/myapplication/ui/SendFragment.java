package com.example.myapplication.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.activity.FourActivity;
import com.example.myapplication.ui.activity.SecondActivity;
import com.example.myapplication.ui.activity.ThirdActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendFragment extends Fragment {


    @BindView(R.id.second_activity)
    Button secondActivity;
    @BindView(R.id.third_activity)
    Button thirdActivity;

    public SendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_send, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.second_activity, R.id.third_activity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.second_activity:
                startActivity(new Intent(this.getContext(), FourActivity.class));
                break;
            case R.id.third_activity:
                startActivity(new Intent(this.getContext(), ThirdActivity.class));
                break;
        }
    }
}
