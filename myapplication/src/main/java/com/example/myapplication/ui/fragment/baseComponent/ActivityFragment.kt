package com.example.myapplication.ui.fragment.baseComponent

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.TestActivity
import com.example.myapplication.base.BaseLazyLoadFragment
import com.example.myapplication.databinding.FragmentActivityBinding
import com.example.myapplication.ui.activity.SecondActivity

private const val TAG = "ActivityFragment"

class ActivityFragment : BaseLazyLoadFragment(), View.OnClickListener {
    private lateinit var binding: FragmentActivityBinding

    companion object {
        fun newInstance(): ActivityFragment {
            return ActivityFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_activity
    }

    override fun initView() {
        binding.btnLifecycle.setOnClickListener(this)
        binding.btnJump.setOnClickListener(this)
        binding.btnStandard.setOnClickListener(this)
        binding.btnSingleTop.setOnClickListener(this)
        binding.btnSingleTask.setOnClickListener(this)
        binding.btnSingleInstance.setOnClickListener(this)
        binding.btnExplode.setOnClickListener(this)
        binding.btnSlide.setOnClickListener(this)
        binding.btnFade.setOnClickListener(this)
        binding.btnShowStatsBar.setOnClickListener(this)
        binding.btnHideStatusBar.setOnClickListener(this)
        binding.btnShowNavigationBar.setOnClickListener(this)
        binding.btnHideNavigationBar.setOnClickListener(this)
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

    private fun jumpToSecondActivity() {
        Log.i(TAG, "jumpToSecondActivity: ")
        val intent = Intent(activity, SecondActivity::class.java)
        intent.putExtra("Flag", "normal")
        startActivity(intent)
    }

    private fun explodeToSecondActivity() {
        Log.i(TAG, "explodeToSecondActivity: ")
        val intent = Intent(activity, SecondActivity::class.java)
        intent.putExtra("Flag", "explode")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        } else {
            startActivity(intent)
        }
    }

    private fun slideToSecondActivity() {
        Log.i(TAG, "slideToSecondActivity: ")
        val intent = Intent(activity, SecondActivity::class.java)
        intent.putExtra("Flag", "slide")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        } else {
            startActivity(intent)
        }
    }

    private fun fadeToSecondActivity() {
        Log.i(TAG, "fadeToSecondActivity: ")
        val intent = Intent(activity, SecondActivity::class.java)
        intent.putExtra("Flag", "fade")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
        } else {
            startActivity(intent)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_lifecycle -> {
                val intent = Intent(this.context, TestActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_standard -> {
                val intentStand = Intent(activity, SecondActivity::class.java)
                intentStand.putExtra("Flag", "normal")
                startActivity(intentStand)
            }
            R.id.btn_singleTop -> {
                val intentTop = Intent(activity, SecondActivity::class.java)
                intentTop.putExtra("Flag", "singleTop")
                startActivity(intentTop)
            }
            R.id.btn_singleTask -> {
                val intentTask = Intent(activity, SecondActivity::class.java)
                intentTask.putExtra("Flag", "singleTask")
                startActivity(intentTask)
            }
            R.id.btn_singleInstance -> {
                val intentInstance = Intent(activity, SecondActivity::class.java)
                intentInstance.putExtra("Flag", "singleInstance")
                startActivity(intentInstance)
            }
            R.id.btn_explode -> explodeToSecondActivity()
            R.id.btn_slide -> slideToSecondActivity()
            R.id.btn_fade -> fadeToSecondActivity()
            R.id.btn_showStatsBar -> {
                Log.i(TAG, "onClick: btn_showStatsBar")
                setStatusBarVisible(true)
            }
            R.id.btn_hideStatusBar -> {
                Log.i(TAG, "onClick: btn_hideStatusBar")
                setStatusBarVisible(false)
            }
            R.id.btn_showNavigationBar -> {
                Log.i(TAG, "onClick: btn_showNavigationBar")
                setNavigationBarVisible(true)
            }
            R.id.btn_hideNavigationBar -> setNavigationBarVisible(false)
            else -> {
            }
        }
    }

    private fun setStatusBarVisible(flag: Boolean) {
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        //        if (flag){
//            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//        }else{
//            getActivity().getActionBar().hide();
//        }
    }

    private fun setNavigationBarVisible(flag: Boolean) {
        if (flag) {
            binding.btnShowNavigationBar.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        } else {
            binding.btnShowNavigationBar.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }
}