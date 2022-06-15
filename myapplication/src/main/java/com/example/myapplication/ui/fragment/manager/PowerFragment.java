package com.example.myapplication.ui.fragment.manager;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentPowerBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class PowerFragment extends Fragment {
    private static final String TAG = "PowerFragment";
    private Intent batteryStatus;
    private IntentFilter intentFilter;
    private PowerConnectionReceiver receiver;
    private FragmentPowerBinding binding;
    private IntentFilter ifilter;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPowerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void setData(Intent intent) {
//        显示电池百分比
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPct = level / (float) scale;
        int percent = (int) (batteryPct * 100);
        binding.tvPower.setText(String.valueOf(percent) + "%");

        // Are we charging / charged?
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        binding.tvIsCharging.setText(isCharging ? "正在充电" : "已充满或者没有充电");

        // How are we charging?
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        if (usbCharge)
            binding.tvChargeMethod.setText("Usb 充电");

        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        if (acCharge)
            binding.tvChargeMethod.setText("交流电充电");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
    }

    private void initView(View view) {
        ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryStatus = getContext().registerReceiver(null, ifilter);
        setData(batteryStatus);
        receiver = new PowerConnectionReceiver();

        String s = "dsfgf";
        s.replaceAll("", "");
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_USAGE_SUMMARY);
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

    }


    public static class PowerConnectionReceiver extends BroadcastReceiver {

        public PowerConnectionReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "onReceive: ");
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL;
            Log.i(TAG, "onReceive: isCharging " + isCharging);
            Toast.makeText(context, isCharging ? "正在充电" : "已充满或者没有充电", Toast.LENGTH_SHORT).show();

            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if (usbCharge) {
                Toast.makeText(context, "Usb charging", Toast.LENGTH_SHORT).show();
            }
            if (acCharge) {
                Toast.makeText(context, "AC charging", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        context.registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onStop() {
        super.onStop();
        context.unregisterReceiver(receiver);
    }
}
