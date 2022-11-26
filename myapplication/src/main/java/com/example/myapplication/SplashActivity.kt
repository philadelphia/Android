package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.myapplication.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countDownTimer.start()
    }

    override fun initBinding(): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        mBinding!!.imgBackGround.setOnClickListener(this)
        mBinding!!.btnTimter.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.img_backGround -> {
            }
            R.id.btn_timter -> goToLogInOrMainActivity()
        }
    }

    private val countDownTimer: CountDownTimer = object : CountDownTimer(3200, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            mBinding!!.btnTimter.text = "跳过(" + millisUntilFinished / 1000 + "s)"
        }

        override fun onFinish() {
            mBinding!!.btnTimter.text = "跳过( 0 s)"
            goToLogInOrMainActivity()
        }
    }

    private fun goToLogInOrMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}