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
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.myapplication.IRemoteService
import com.example.myapplication.R
import com.example.myapplication.base.BaseLazyLoadFragment
import com.example.myapplication.databinding.FragmentServiceBinding
import com.example.myapplication.recevier.BootReceiver
import com.example.myapplication.service.MyService
import com.example.myapplication.service.MyService.MyBinder
import com.example.myapplication.service.RemoteService

class ServiceFragment : BaseLazyLoadFragment(), View.OnClickListener {
    private var binder: MyBinder? = null
    private lateinit var binding: FragmentServiceBinding
    private lateinit var mIntent: Intent
    private var mRemoteBinder: IRemoteService? = null
    private var mRemoteServiceConnection: RemoteServiceConnection? = null

    companion object {
        fun newInstance(): ServiceFragment {
            return ServiceFragment()
        }
    }

    val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder?) {
            binder = iBinder as? MyBinder
            Log.i(TAG, "onServiceConnected: count == " + binder?.getCount())
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            Log.i(TAG, "onServiceDisconnected: ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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
        binding.btnBindRemoteService.setOnClickListener(this)
        binding.btnUnbindRemoteService.setOnClickListener(this)
        binding.btnRemoteInfo.setOnClickListener(this)
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
            R.id.btn_startService -> startService()
            R.id.btn_stopService -> stopService()
            R.id.btn_bindService -> bindService()
            R.id.btn_unbindService -> unBindService()
            R.id.btn_bindRemoteService -> bindRemoteService()
            R.id.btn_unbindRemoteService -> unbindRemoteService()
            R.id.btn_remote_info -> getRemoteInfo()
            else -> Log.d(TAG, "onClick: ")
        }
    }

    private fun startService() {
        mIntent = Intent(context, MyService::class.java)
        context?.startService(mIntent)
    }

    private fun sendBroadCast() {
        val intent = Intent("test")
        LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        context?.sendBroadcast(intent)
        val intent1 = Intent(context, BootReceiver::class.java)
        context?.sendBroadcast(intent1)
    }

    private fun stopService() {
        context?.stopService(mIntent)
    }

    private fun bindService() {
        context?.bindService(mIntent, serviceConnection, Service.BIND_AUTO_CREATE)
    }

    private fun unBindService() {
        context?.unbindService(serviceConnection)
    }

    private fun bindRemoteService() {
        val intent = Intent(context, RemoteService::class.java)
        mRemoteServiceConnection = RemoteServiceConnection()
        context?.bindService(intent, mRemoteServiceConnection!!, Service.BIND_AUTO_CREATE)
    }

    private fun unbindRemoteService() {
        context?.unbindService(mRemoteServiceConnection!!)
    }

    private fun getRemoteInfo() {
        mRemoteBinder?.let {
            var basicInfo = it.basicInfo
            Log.d(TAG, "getRemoteInfo: $basicInfo")
        }
    }

    inner class RemoteServiceConnection : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mRemoteBinder = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mRemoteBinder = IRemoteService.Stub.asInterface(service)
        }


    }
}