package com.example.myapplication.ui.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.ui.activity.SecondActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityFragment extends Fragment implements  View.OnClickListener {


    private final String TAG = ActivityFragment.class.getSimpleName();
    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.btn_explode)
    Button btnExplode;
    @BindView(R.id.btn_slide)
    Button btnSlide;
    @BindView(R.id.btn_fade)
    Button btnFade;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_activity, null);
        ButterKnife.bind(this, view);
        return view;
    }


    public void jumpToSecondActivity() {
        Log.i(TAG, "jumpToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag","normal");
        startActivity(intent);
    }

    public void explodeToSecondActivity() {
        Log.i(TAG, "explodeToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag","explode");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }


    public void slideToSecondActivity() {
        Log.i(TAG, "slideToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag","slide");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

    }


    public void fadeToSecondActivity() {
        Log.i(TAG, "fadeToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag","fade");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

    }


    @OnClick({R.id.btn_jump, R.id.btn_explode, R.id.btn_slide, R.id.btn_fade})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                jumpToSecondActivity();
                break;
            case R.id.btn_explode:
                explodeToSecondActivity();
                break;
            case R.id.btn_slide:
                slideToSecondActivity();
                break;
            case R.id.btn_fade:
                fadeToSecondActivity();
                break;
        }
    }
}
