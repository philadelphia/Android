package com.example.myapplication.ui.fragment.baseComponent;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseLazyLoadFragment;
import com.example.myapplication.service.MyService;

import butterknife.BindView;
import butterknife.OnClick;

public class ServiceFragment extends BaseLazyLoadFragment {
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.btn_startService)
    Button btnStartService;
    @BindView(R.id.btn_stopService)
    Button btnStopService;
    @BindView(R.id.btn_bindService)
    Button btnBindService;
    @BindView(R.id.btn_unbindService)
    Button btnUnbindService;
    private MyService.MyBinder binder;
    Intent intent;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (MyService.MyBinder) iBinder;
            Log.i(TAG, "onServiceConnected: count == " + binder.getCount());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        intent = new Intent(getContext(), MyService.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void dismissProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onDataLoadSucceed() {
        Log.i(TAG, "onDataLoadSucceed: ");
        llContent.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean getUserVisibleHint() {
        Log.i(TAG, "getUserVisibleHint: " + super.getUserVisibleHint());
        return super.getUserVisibleHint();

    }

    @OnClick({R.id.btn_startService, R.id.btn_stopService, R.id.btn_bindService, R.id.btn_unbindService})
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btn_startService:
                getContext().startService(intent);
                break;
            case R.id.btn_stopService:
                getContext().stopService(intent);
                break;
            case R.id.btn_bindService:
                getContext().bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbindService:
                getContext().unbindService(serviceConnection);
                break;

        }
    }


}

