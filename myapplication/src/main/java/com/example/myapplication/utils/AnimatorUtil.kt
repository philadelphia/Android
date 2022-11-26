package com.example.myapplication.utils

import android.animation.ValueAnimator
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

/**
 * Created by Tao.ZT.Zhang on 2017/8/14.
 */
object AnimatorUtil {
    const val TAG = "xujun"
    val FAST_OUT_SLOW_IN_INTERPOLATOR = LinearOutSlowInInterpolator()

    // 显示view
    @JvmStatic
    fun scaleShow(view: View, viewPropertyAnimatorListener: ViewPropertyAnimatorListener?) {
        view.visibility = View.VISIBLE
        ViewCompat.animate(view)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .alpha(1.0f)
                .setDuration(800)
                .setListener(viewPropertyAnimatorListener)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .start()
    }

    // 隐藏view
    @JvmStatic
    fun scaleHide(view: View?, viewPropertyAnimatorListener: ViewPropertyAnimatorListener?) {
        ViewCompat.animate(view!!)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .alpha(0.0f)
                .setDuration(800)
                .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                .setListener(viewPropertyAnimatorListener)
                .start()
    }

    fun tanslation(view: View, start: Float, end: Float) {
        val animator = ValueAnimator.ofFloat(start, end)
        view.visibility = View.VISIBLE
        animator.addUpdateListener {
            val value = animator.animatedValue as Float
            Log.i(TAG, "tanslation: value=$value")
            view.translationY = value
        }
        animator.duration = 200
        animator.interpolator = FAST_OUT_SLOW_IN_INTERPOLATOR
        animator.start()
    }

    fun showHeight(view: View, start: Float, end: Float) {
        val animator = ValueAnimator.ofFloat(start, end)
        val layoutParams = view.layoutParams
        animator.addUpdateListener {
            val value = animator.animatedValue as Float
            layoutParams.height = value.toInt()
            view.layoutParams = layoutParams
            Log.i(TAG, "onAnimationUpdate: value=$value")
        }
        animator.duration = 500
        animator.interpolator = AccelerateInterpolator()
        animator.start()
    }

    fun show(view: View, start: Int, end: Int) {
        val height = view.height
        val animator = ValueAnimator.ofInt(start, end)
        animator.addUpdateListener {
            val value = animator.animatedValue as Int
            Log.i(TAG, "onAnimationUpdate: value=$value")
            view.top = value
            view.bottom = value + height
        }
        view.visibility = View.VISIBLE
        animator.duration = 200
        animator.interpolator = FAST_OUT_SLOW_IN_INTERPOLATOR
        animator.start()
    }
}