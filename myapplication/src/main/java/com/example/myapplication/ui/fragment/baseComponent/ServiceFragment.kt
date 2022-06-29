package com.example.myapplication.ui.fragment.baseComponent

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.BaseLazyLoadFragment
import com.example.myapplication.databinding.FragmentServiceBinding
import com.example.myapplication.service.MyService
import com.example.myapplication.service.MyService.MyBinder

class ServiceFragment : BaseLazyLoadFragment(), View.OnClickListener {
    private var binder: MyBinder? = null
    private lateinit var binding: FragmentServiceBinding
    private lateinit var intent: Intent

    companion object {
        fun newInstance(): ServiceFragment {
            return ServiceFragment()
        }
    }

    val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder?) {
            binder = iBinder as? MyBinder
            Log.i(TAG, "onServiceConnected: count == " + binder?.count)
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            Log.i(TAG, "onServiceDisconnected: ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        intent = Intent(context, MyService::class.java)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_service
    }

    override fun initView() {
        binding.btnStartService.setOnClickListener(this)
        binding.btnStopService.setOnClickListener(this)
        binding.btnBindService.setOnClickListener(this)
        binding.btnUnbindService.setOnClickListener(this)
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun dismissProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    override fun onDataLoadSucceed() {
        Log.i(TAG, "onDataLoadSucceed: ")
        binding.llContent.visibility = View.VISIBLE
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_startService -> context?.startService(intent)
            R.id.btn_stopService -> context?.stopService(intent)
            R.id.btn_bindService -> context?.bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE)
            R.id.btn_unbindService -> context?.unbindService(serviceConnection)
            else -> {
            }
        }
    }
}