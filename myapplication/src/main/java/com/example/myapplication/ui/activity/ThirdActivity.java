package com.example.myapplication.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.customwidget.RevealBackGround;
import com.example.myapplication.databinding.ActivityThirdBinding;
import com.example.myapplication.utils.TimeLineItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class ThirdActivity extends AppCompatActivity implements RevealBackGround.OnStateChangedListener {
    private static final String TAG = "ThirdActivity";
    private PackageManager packageManager;
    private final List<PackageInfo> dataList = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Interpolator INTERPOLATOR = new DecelerateInterpolator();
    private ActivityThirdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        packageManager = getPackageManager();
        initToolBar();
        initData();
        //开启水波纹动画
        startBackgroundAnimation();
        binding.fab.setOnClickListener((View view) -> {
            onViewClicked();
        });

    }

    private void initToolBar() {
        setSupportActionBar(binding.toolbar);
    }

    private void startBackgroundAnimation() {
        binding.revealBackground.setOnStateChangedListener(this);
        final int[] location = getIntent().getIntArrayExtra("location");
        binding.revealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                binding.revealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                binding.revealBackground.startBackgroundAnimation(location);
                return true;
            }
        });
    }

    public void initData() {
        dataList.addAll(packageManager.getInstalledPackages(0).subList(0, 20));
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    public void onStateChange(int state) {
        if (state == RevealBackGround.STATE_FINISHED) {
            binding.appLayout.setVisibility(View.VISIBLE);
            binding.toolbar.setVisibility(View.VISIBLE);
            binding.recyclerView.setVisibility(View.VISIBLE);
            adapter = new RecyclerViewAdapter(dataList, null);
//            recyclerView.addItemDecoration(new MyItemDecoration(this, MyItemDecoration.VERTICAL_LIST));
            binding.recyclerView.addItemDecoration(new TimeLineItemDecoration());
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            binding.recyclerView.setAdapter(adapter);
            animatorOtherView();
        } else {
            //水波纹动画没有结束。其他view不能显示
            binding.appLayout.setVisibility(View.INVISIBLE);
            binding.toolbar.setVisibility(View.INVISIBLE);
            binding.recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    private void animatorOtherView() {
        binding.toolbar.setTranslationY(-binding.toolbar.getHeight());
        binding.toolbar.animate().translationY(0).setDuration(300).setStartDelay(300).setInterpolator(INTERPOLATOR).start();
        binding.appLayout.setTranslationY(-binding.appLayout.getHeight());


        binding.appLayout.animate().translationY(0).setDuration(200).setStartDelay(300).setInterpolator(INTERPOLATOR).start();
        binding.recyclerView.setTranslationY(-(binding.recyclerView.getHeight() + binding.toolbar.getHeight()));
        ObjectAnimator translationY = ObjectAnimator.ofInt(binding.recyclerView, "translationY", -(binding.recyclerView.getHeight() + binding.toolbar.getHeight()), 0);
//        ObjectAnimator translationY = ObjectAnimator.ofInt(binding.recyclerView, "translationY", -(binding.recyclerView.getHeight() + binding.toolbar.getHeight()), 0);
        translationY.setDuration(300).setInterpolator(INTERPOLATOR);
        translationY.start();
        binding.recyclerView.animate().translationY(0).setDuration(300).setStartDelay(300).setInterpolator(INTERPOLATOR).start();
    }

    public void onViewClicked() {
        startActivity(new Intent(this, FifthActivity.class));
    }
}
