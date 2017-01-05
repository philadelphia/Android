package com.example.myapplication.ui.fragment.baseComponent;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.service.MyService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceFragment extends Fragment {
    @BindView(R.id.btn_startService)
    Button btnStartService;
    @BindView(R.id.btn_stopService)
    Button btnStopService;
    @BindView(R.id.btn_bindService)
    Button btnBindService;
    @BindView(R.id.btn_unbindService)
    Button btnUnbindService;
    private MyService.MyBinder binder;
    private static final String TAG = "ServiceFragment";
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

    public ServiceFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        intent = new Intent(getContext(), MyService.class);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
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

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}

