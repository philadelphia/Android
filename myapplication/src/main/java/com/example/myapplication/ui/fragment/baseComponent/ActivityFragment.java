package com.example.myapplication.ui.fragment.baseComponent;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.entity.Result;
import com.example.myapplication.ui.activity.SecondActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class ActivityFragment extends Fragment implements View.OnClickListener {


    private final String TAG = ActivityFragment.class.getSimpleName();
    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.btn_standard)
    Button btnStandard;
    @BindView(R.id.btn_singleTop)
    Button btnSingleTop;
    @BindView(R.id.btn_singleTask)
    Button btnSingleTask;
    @BindView(R.id.btn_singleInstance)
    Button btnSingleInstance;
    @BindView(R.id.btn_explode)
    Button btnExplode;
    @BindView(R.id.btn_slide)
    Button btnSlide;
    @BindView(R.id.btn_fade)
    Button btnFade;
    @BindView(R.id.btn_showStatsBar)
    Button btnShowStatsBar;
    @BindView(R.id.btn_hideStatusBar)
    Button btnHideStatusBar;
    @BindView(R.id.btn_showNavigationBar)
    Button btnShowNavigationBar;
    @BindView(R.id.btn_hideNavigationBar)
    Button btnHideNavigationBar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_activity, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: s");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint: " + isVisibleToUser);
    }

    @Override
    public boolean getUserVisibleHint() {
        Log.i(TAG, "getUserVisibleHint: " + super.getUserVisibleHint());
        return super.getUserVisibleHint();

    }

    public void jumpToSecondActivity() {
        Log.i(TAG, "jumpToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag", "normal");
        startActivity(intent);
    }

    public void explodeToSecondActivity() {
        Log.i(TAG, "explodeToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag", "explode");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }


    public void slideToSecondActivity() {
        Log.i(TAG, "slideToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag", "slide");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

    }


    public void fadeToSecondActivity() {
        Log.i(TAG, "fadeToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag", "fade");
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());

    }


    @OnClick({R.id.btn_jump, R.id.btn_standard, R.id.btn_singleTop, R.id.btn_singleTask, R.id.btn_singleInstance,
            R.id.btn_explode, R.id.btn_slide, R.id.btn_fade,R.id.btn_showStatsBar, R.id.btn_hideStatusBar,R.id.btn_showNavigationBar,R.id.btn_hideNavigationBar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                jumpToSecondActivity();
                break;

            case R.id.btn_standard:
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("Flag", "normal");
                startActivity(intent);
                break;

            case R.id.btn_singleTop:
                Intent intentTop = new Intent(getActivity(), SecondActivity.class);
                intentTop.putExtra("Flag", "singleTop");
                startActivity(intentTop);
                break;

            case R.id.btn_singleTask:
                Intent intentTask = new Intent(getActivity(), SecondActivity.class);
                intentTask.putExtra("Flag", "singleTask");
                startActivity(intentTask);
                break;

            case R.id.btn_singleInstance:
                Intent intentInstance = new Intent(getActivity(), SecondActivity.class);
                intentInstance.putExtra("Flag", "singleInstance");
                startActivity(intentInstance);
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
            case R.id.btn_showStatsBar:
                Log.i(TAG, "onClick: btn_showStatsBar");
                setStatusBarVisiable(true);
                break;
            case R.id.btn_hideStatusBar:
                Log.i(TAG, "onClick: btn_hideStatusBar");
                setStatusBarVisiable(false);
                break;
            case R.id.btn_showNavigationBar:
                Log.i(TAG, "onClick: btn_showNavigationBar");
                setNavigationBarVisiable(true);
                break;
            case R.id.btn_hideNavigationBar:
                setNavigationBarVisiable(false);
                break;
            default:
                break;
        }
    }

    private void setStatusBarVisiable(boolean flag) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//        if (flag){
//            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//
//        }else{
//            getActivity().getActionBar().hide();
//        }

    }

    private void setNavigationBarVisiable(boolean flag) {
        if (flag){
            btnShowNavigationBar.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        }else {
            btnShowNavigationBar.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            getActivity().getActionBar().hide();
        }
    }


}
