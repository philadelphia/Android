package com.example.myapplication.ui.fragment.baseComponent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BroadCastReceiverFragment extends Fragment {
    @BindView(R.id.btn_openWiFi)
    Button btnOpenWiFi;
    @BindView(R.id.btn_stopWiFi)
    Button btnStopWiFi;

    @BindView(R.id.btn_wifiStatus)
    Button btnWifiStatus;
    private IntentFilter intentFilter;
    private MyNetworkChangeReceiver myNetworkChangeReceiver;
    private WifiManager wifiManager;
    private static final String TAG = "BroadReceiverFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        myNetworkChangeReceiver = new MyNetworkChangeReceiver();
        Log.i(TAG, "onCreate: registerReceiver");
        getActivity().registerReceiver(myNetworkChangeReceiver, intentFilter);
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_broad_cast_receiver, container, false);
        ButterKnife.bind(this, view);
//        tvWifiStatus.setText(wifiManager.getWifiState() == 3 ? "open"+wifiManager.getWifiState():"close"+wifiManager.getWifiState());
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
        Log.i(TAG, "onResume: ");
        super.onResume();
        getActivity().registerReceiver(myNetworkChangeReceiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(myNetworkChangeReceiver);
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
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


    @OnClick({R.id.btn_openWiFi, R.id.btn_stopWiFi, R.id.btn_wifiStatus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_openWiFi:
                wifiManager.setWifiEnabled(true);
                break;

            case R.id.btn_stopWiFi:
                wifiManager.setWifiEnabled(false);
                break;

        }
    }



    public class MyNetworkChangeReceiver extends BroadcastReceiver {
        public MyNetworkChangeReceiver() {
        }


        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "network status changed"  , Toast.LENGTH_SHORT).show();
            String wifiStatus = null;
            switch (wifiManager.getWifiState()) {
                case 0:
                    wifiStatus = "WIFI_STATE_DISABLING";
                    break;

                case 1:
                    wifiStatus = "WIFI_STATE_DISABLED";
                    break;
                case 2:
                    wifiStatus = "WIFI_STATE_ENABLING";
                    break;
                case 3:
                    wifiStatus = "WIFI_STATE_ENABLED";
                    break;
                case 4:
                    wifiStatus = "WIFI_STATE_UNKNOWN";
                    break;

            }
            btnWifiStatus.setText("wifiStatus :" + wifiStatus);

        }
    }


}

