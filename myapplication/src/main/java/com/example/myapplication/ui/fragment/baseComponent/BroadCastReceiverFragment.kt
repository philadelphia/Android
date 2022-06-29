package com.example.myapplication.ui.fragment.baseComponent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.base.BaseLazyLoadFragment
import com.example.myapplication.databinding.FragmentBroadCastReceiverBinding

class BroadCastReceiverFragment : BaseLazyLoadFragment(), View.OnClickListener {
    private lateinit var intentFilter: IntentFilter
    private var myNetworkChangeReceiver: MyNetworkChangeReceiver? = null
    private var wifiManager: WifiManager? = null
    private lateinit var binding: FragmentBroadCastReceiverBinding

    companion object {
        fun newInstance(): BroadCastReceiverFragment {
            return BroadCastReceiverFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        myNetworkChangeReceiver = MyNetworkChangeReceiver()
        activity?.registerReceiver(myNetworkChangeReceiver, intentFilter)
        wifiManager = activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBroadCastReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(myNetworkChangeReceiver, intentFilter)
    }

    override fun onDestroy() {
        activity?.unregisterReceiver(myNetworkChangeReceiver)
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_broad_cast_receiver
    }

    override fun initView() {
        binding.btnOpenWiFi.setOnClickListener(this)
        binding.btnStopWiFi.setOnClickListener(this)
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun dismissProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDataLoadSucceed() {
        Log.i(TAG, "onDataLoadSucceed: ")
        binding.scrollView.visibility = View.VISIBLE
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_openWiFi -> wifiManager?.isWifiEnabled = true
            R.id.btn_stopWiFi -> wifiManager?.isWifiEnabled = false
        }
    }

    inner class MyNetworkChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Toast.makeText(context, "network status changed", Toast.LENGTH_SHORT).show()
            var wifiStatus: String? = null
            when (wifiManager?.wifiState) {
                0 -> wifiStatus = "WIFI_STATE_DISABLING"
                1 -> wifiStatus = "WIFI_STATE_DISABLED"
                2 -> wifiStatus = "WIFI_STATE_ENABLING"
                3 -> wifiStatus = "WIFI_STATE_ENABLED"
                4 -> wifiStatus = "WIFI_STATE_UNKNOWN"
            }
            binding.btnWifiStatus.text = "wifiStatus :$wifiStatus"
        }
    }
}