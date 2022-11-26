package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import com.example.myapplication.databinding.ActivityTestBinding
import com.example.myapplication.service.LocationService
import com.example.myapplication.ui.activity.FirstActivity

/**
 * 此页面是为了验证ViewGroup布局变化时的动画效果
 */
class TestActivity : BaseActivity<ActivityTestBinding>(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        Log.e(TAG, "onCreate: " + lifecycle.currentState.name)
    }

    override fun initBinding(): ActivityTestBinding {
        return ActivityTestBinding.inflate(layoutInflater)
    }

    private fun initView() {
        mBinding!!.btnAdd.setOnClickListener(this)
        mBinding!!.btnAnimator.setOnClickListener(this)
        mBinding!!.btnFirstActivity.setOnClickListener(this)
        mBinding!!.btnLaunchSelf.setOnClickListener(this)
        mBinding!!.btnStartService.setOnClickListener(this)
        mBinding!!.btnStopService.setOnClickListener(this)
        lifecycle.addObserver(mBinding!!.chronometer)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_add -> addView()
            R.id.btn_animator -> performAnimation()
            R.id.btn_launch_self -> launchSelf()
            R.id.btn_first_activity -> launchFirstActivity()
            R.id.btn_start_service -> startService(Intent(this, LocationService::class.java))
            R.id.btn_stop_service -> stopService(Intent(this, LocationService::class.java))
            else -> {
            }
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun addView() {
        val layoutTransition = mBinding!!.linearLayout.layoutTransition
        //通过翻转动画取代默认的动画效果
        val animatorIn: Animator = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f)
                .setDuration(layoutTransition.getDuration(LayoutTransition.APPEARING))
        layoutTransition.setAnimator(LayoutTransition.APPEARING, animatorIn)
        val animatorOut: Animator = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f)
                .setDuration(layoutTransition.getDuration(LayoutTransition.DISAPPEARING))
        val animatorAlphaOut: Animator = ObjectAnimator.ofFloat(null, "alpha", 1.0f, 0.0f)
                .setDuration(layoutTransition.getDuration(LayoutTransition.DISAPPEARING))
        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(animatorOut, animatorAlphaOut)
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, animatorSet)
        val button = Button(this)
        button.text = "i am a new Button"
        button.setOnClickListener { mBinding!!.linearLayout.removeView(button) }
        mBinding!!.linearLayout.addView(button, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
    }

    private fun performAnimation() {
        if (mBinding!!.linearLayout.visibility == View.VISIBLE) {
//            Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
//            Animation animationOut = AnimationUtils.makeOutAnimation(this, true);
            val animationOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_child_bottom)
            mBinding!!.linearLayout.animation = animationOut
            animationOut.start()
            mBinding!!.linearLayout.visibility = View.INVISIBLE
        } else {
//            Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
//            Animation animationIn = AnimationUtils.makeInAnimation(this, true);
            val animationIn = AnimationUtils.makeInChildBottomAnimation(this)
            mBinding!!.linearLayout.startAnimation(animationIn)
            mBinding!!.linearLayout.visibility = View.VISIBLE
        }
    }

    private fun launchFirstActivity() {
        val intent = Intent(this, FirstActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }

    private fun launchSelf() {
        val intent = Intent(this, TestActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: " + lifecycle.currentState.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: " + lifecycle.currentState.name)
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: " + lifecycle.currentState.name)
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: " + lifecycle.currentState.name)
    }

    override fun onResume() {
        super.onResume()
        val displayMetrics = resources.displayMetrics
        //        displayMetrics.density
        Log.e(TAG, "onResume:widthPixels ==  " + displayMetrics.widthPixels)
        Log.e(TAG, "onResume:heightPixels ==  " + displayMetrics.heightPixels)
        Log.e(TAG, "onResume:xdpi ==  " + displayMetrics.xdpi + "ydpi == " + displayMetrics.ydpi)
        Log.e(TAG, "onResume:density ==  " + displayMetrics.density)
        Log.e(TAG, "onResume:densityDpi ==  " + displayMetrics.densityDpi)
        Log.e(TAG, "onResume:scaledDensity ==  " + displayMetrics.scaledDensity)
        Log.e(TAG, "onResume: " + lifecycle.currentState.name)
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: " + lifecycle.currentState.name)
    }
}