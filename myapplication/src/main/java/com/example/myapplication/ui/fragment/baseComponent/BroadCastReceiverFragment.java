package com.example.myapplication.ui.fragment.baseComponent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseLazyLoadFragment;
import com.example.myapplication.databinding.FragmentBroadCastReceiverBinding;


public class BroadCastReceiverFragment extends BaseLazyLoadFragment implements View.OnClickListener {
    private IntentFilter intentFilter;
    private MyNetworkChangeReceiver myNetworkChangeReceiver;
    private WifiManager wifiManager;
    private FragmentBroadCastReceiverBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        myNetworkChangeReceiver = new MyNetworkChangeReceiver();
        getActivity().registerReceiver(myNetworkChangeReceiver, intentFilter);
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBroadCastReceiverBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(myNetworkChangeReceiver, intentFilter);
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

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_broad_cast_receiver;
    }

    @Override
    protected void initView() {
        binding.btnOpenWiFi.setOnClickListener(this);
        binding.btnStopWiFi.setOnClickListener(this);
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
            Toast.makeText(context, "network status changed", Toast.LENGTH_SHORT).show();
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
            binding.btnWifiStatus.setText("wifiStatus :" + wifiStatus);

        }
    }


}

