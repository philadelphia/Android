package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.customwidget.RevealBackGround;
import com.example.myapplication.utils.MyItemDecoration;
import com.example.myapplication.utils.MyItemDecoration1;
import com.example.myapplication.utils.MyItemDecoration2;
import com.example.myapplication.utils.TimeLineItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ThirdActivity extends AppCompatActivity implements RevealBackGround.OnStateChangedListener {
    private static final String TAG = "ThirdActivity";
    @BindView(R.id.revealBackground)
    RevealBackGround revealBackground;
    @BindView(R.id.appLayout)
    AppBarLayout appLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private PackageManager packageManager;
    private final List<PackageInfo> dataList = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private Interpolator INTERPOLATOR = new DecelerateInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);
        packageManager = getPackageManager();
        initToolBar();
        initData();
        //开启水波纹动画

        startBackgroundAnimation();

    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
    }

    private void startBackgroundAnimation() {
        revealBackground.setOnStateChangedListener(this);
        final int[] location = getIntent().getIntArrayExtra("location");
        revealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                revealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                revealBackground.startbackGroundAnimation(location);
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
            appLayout.setVisibility(View.VISIBLE);
            toolbar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter = new RecyclerViewAdapter(dataList, null);
//            recyclerView.addItemDecoration(new MyItemDecoration(this, MyItemDecoration.VERTICAL_LIST));
            recyclerView.addItemDecoration(new TimeLineItemDecoration());
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(adapter);
            animatorOtherView();
        } else {
            //水波纹动画没有结束。其他view不能显示
            appLayout.setVisibility(View.INVISIBLE);
            toolbar.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
    }

    private void animatorOtherView() {
        toolbar.setTranslationY(-toolbar.getHeight());
        toolbar.animate().translationY(0).setDuration(300).setStartDelay(300).setInterpolator(INTERPOLATOR).start();
        appLayout.setTranslationY(-appLayout.getHeight());
        appLayout.animate().translationY(0).setDuration(200).setStartDelay(300).setInterpolator(INTERPOLATOR).start();
        recyclerView.setTranslationY(-recyclerView.getHeight());
        recyclerView.animate().translationY(0).setDuration(300).setStartDelay(300).setInterpolator(INTERPOLATOR).start();
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        startActivity(new Intent(this, FifthActivity.class));
    }
}
