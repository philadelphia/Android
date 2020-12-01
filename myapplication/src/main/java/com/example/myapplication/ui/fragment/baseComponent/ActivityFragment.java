package com.example.myapplication.ui.fragment.baseComponent;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.TestActivity;
import com.example.myapplication.base.BaseLazyLoadFragment;
import com.example.myapplication.databinding.FragmentActivityBinding;
import com.example.myapplication.ui.activity.SecondActivity;

public class ActivityFragment extends BaseLazyLoadFragment implements View.OnClickListener {
    private FragmentActivityBinding binding;
    private final String TAG = ActivityFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentActivityBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void initView() {
        binding.btnLifeCircle.setOnClickListener(this);
        binding.btnJump.setOnClickListener(this);
        binding.btnStandard.setOnClickListener(this);
        binding.btnSingleTop.setOnClickListener(this);
        binding.btnSingleTask.setOnClickListener(this);
        binding.btnSingleInstance.setOnClickListener(this);
        binding.btnExplode.setOnClickListener(this);
        binding.btnSlide.setOnClickListener(this);
        binding.btnFade.setOnClickListener(this);
        binding.btnShowStatsBar.setOnClickListener(this);
        binding.btnHideStatusBar.setOnClickListener(this);
        binding.btnShowNavigationBar.setOnClickListener(this);
        binding.btnHideNavigationBar.setOnClickListener(this);
    }

    @Override
    protected void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void dismissProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDataLoadSucceed() {
        Log.i(TAG, "onDataLoadSucceed: ");
        binding.scrollView.setVisibility(View.VISIBLE);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            startActivity(intent);
        }
    }


    public void slideToSecondActivity() {
        Log.i(TAG, "slideToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag", "slide");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            startActivity(intent);
        }

    }


    public void fadeToSecondActivity() {
        Log.i(TAG, "fadeToSecondActivity: ");
        Intent intent = new Intent(getActivity(), SecondActivity.class);
        intent.putExtra("Flag", "fade");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        } else {
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_life_circle:
                Intent intent = new Intent(this.getContext(), TestActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_standard:
                Intent intentStand = new Intent(getActivity(), SecondActivity.class);
                intentStand.putExtra("Flag", "normal");
                startActivity(intentStand);
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
        if (flag) {
            binding.btnShowNavigationBar.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        } else {
            binding.btnShowNavigationBar.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }


}
