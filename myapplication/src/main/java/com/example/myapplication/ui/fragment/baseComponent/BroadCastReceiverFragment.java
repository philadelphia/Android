package com.example.myapplication.ui.fragment.baseComponent;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.recevier.MyNetworkChangeReceiver;

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
    private static final String TAG = "BroadCastReceiverFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        myNetworkChangeReceiver = new MyNetworkChangeReceiver();
        getActivity().registerReceiver(myNetworkChangeReceiver, intentFilter);
        wifiManager = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_broad_cast_receiver, container, false);
        ButterKnife.bind(this, view);
//        tvWifiStatus.setText(wifiManager.getWifiState() == 3 ? "open"+wifiManager.getWifiState():"close"+wifiManager.getWifiState());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        getActivity().unregisterReceiver(myNetworkChangeReceiver);
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

            case R.id.btn_wifiStatus:
                String status = null;
                switch (wifiManager.getWifiState()) {
                    case 1:
                        status = "closed";
                        break;
                    case 3:
                        status = "open";
                        break;
                    case 4:
                        status = "unkonwn";
                        break;

                }
                btnWifiStatus.setText("status :" + status);
                break;
        }
    }


}

