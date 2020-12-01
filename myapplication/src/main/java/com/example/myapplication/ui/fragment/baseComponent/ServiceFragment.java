package com.example.myapplication.ui.fragment.baseComponent;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseLazyLoadFragment;
import com.example.myapplication.databinding.FragmentServiceBinding;
import com.example.myapplication.service.MyService;

public class ServiceFragment extends BaseLazyLoadFragment implements View.OnClickListener {
    private MyService.MyBinder binder;
    private FragmentServiceBinding binding;

    private Intent intent;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentServiceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initView() {
        binding.btnStartService.setOnClickListener(this);
        binding.btnStopService.setOnClickListener(this);
        binding.btnBindService.setOnClickListener(this);
        binding.btnUnbindService.setOnClickListener(this);
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
        binding.llContent.setVisibility(View.VISIBLE);
    }

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

            default:
        }
    }


}

