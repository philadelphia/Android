package com.example.myapplication.ui.fragment.manager;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PowerFragment extends Fragment {
    private TextView tv_Power;
    private TextView tv_IsCharging;
    private TextView tv_ChargeMethode;
    private IntentFilter ifilter;
    private Intent batteryStatus;
    private Context context;
    private static final String TAG = "PowerFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_power, container, false);
        context = getContext();
        initView(view);
        ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = getContext().registerReceiver(null, ifilter);
        setData(batteryStatus);

        String s = "dsfgf";
        s.replaceAll("","");

        return  view;
    }

    private  void setData(Intent intent) {
//        显示电池百分比
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float)scale;
        int percent= (int) (batteryPct * 100);
        tv_Power.setText(String.valueOf(percent) + "%");

        // Are we charging / charged?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        tv_IsCharging.setText(isCharging ? "正在充电" : "已充满或者没有充电");

        // How are we charging?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        if (usbCharge)
            tv_ChargeMethode.setText("Usb 充电");

        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        if (acCharge)
            tv_ChargeMethode.setText("交流电充电");
    }

    private void initView(View view) {
        tv_Power = (TextView) view.findViewById(R.id.tv_power);
        tv_IsCharging = (TextView) view.findViewById(R.id.tv_isCharging);
        tv_ChargeMethode = (TextView) view.findViewById(R.id.tv_chargeMethod);
    }


    public static class PowerConnectionReceiver extends BroadcastReceiver {

        public  PowerConnectionReceiver(){

        }
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: ");
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            Log.i(TAG, "onReceive: isCharging "  + isCharging);
            Toast.makeText(context, isCharging ? "正在充电" : "已充满或者没有充电", Toast.LENGTH_SHORT).show();

            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if (usbCharge){
                Toast.makeText(context, "Usb charging", Toast.LENGTH_SHORT).show();
            }
            if (acCharge){
                Toast.makeText(context, "AC charging", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(null);
    }
}
