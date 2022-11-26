package com.example.myapplication.ui.fragment.manager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentPowerBinding

/**
 * A simple [Fragment] subclass.
 */
class PowerFragment : Fragment() {
    private var batteryStatus: Intent? = null
    private var intentFilter: IntentFilter? = null
    private var receiver: PowerConnectionReceiver? = null
    private var binding: FragmentPowerBinding? = null
    private var ifilter: IntentFilter? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPowerBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    private fun setData(intent: Intent?) {
//        显示电池百分比
        val level = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
        val batteryPct = level / scale.toFloat()
        val percent = (batteryPct * 100).toInt()
        binding!!.tvPower.text = "$percent%"

        // Are we charging / charged?
        val status = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL
        binding!!.tvIsCharging.text = if (isCharging) "正在充电" else "已充满或者没有充电"

        // How are we charging?
        val chargePlug = batteryStatus!!.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        if (usbCharge) binding!!.tvChargeMethod.text = "Usb 充电"
        val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
        if (acCharge) binding!!.tvChargeMethod.text = "交流电充电"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    private fun initView(view: View) {
        ifilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        batteryStatus = context?.registerReceiver(null, ifilter)
        setData(batteryStatus)
        receiver = PowerConnectionReceiver()
        val s = "dsfgf"
        s.replace("".toRegex(), "")
        intentFilter = IntentFilter()
        intentFilter!!.addAction(Intent.ACTION_POWER_USAGE_SUMMARY)
        intentFilter!!.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter!!.addAction(Intent.ACTION_POWER_DISCONNECTED)
    }

    class PowerConnectionReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.i(TAG, "onReceive: ")
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            val isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                    status == BatteryManager.BATTERY_STATUS_FULL
            Log.i(TAG, "onReceive: isCharging $isCharging")
            Toast.makeText(context, if (isCharging) "正在充电" else "已充满或者没有充电", Toast.LENGTH_SHORT).show()
            val chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
            val usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
            val acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
            if (usbCharge) {
                Toast.makeText(context, "Usb charging", Toast.LENGTH_SHORT).show()
            }
            if (acCharge) {
                Toast.makeText(context, "AC charging", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        context?.registerReceiver(receiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        context?.unregisterReceiver(receiver)
    }

    companion object {
        private const val TAG = "PowerFragment"
    }
}