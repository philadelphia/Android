package com.example.myapplication.recevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class MyNetworkChangeReceiver extends BroadcastReceiver {
    public MyNetworkChangeReceiver() {
    }









    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "network status changed" , Toast.LENGTH_SHORT).show();
    }
}
