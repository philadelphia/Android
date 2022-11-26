package com.example.myapplication

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.example.myapplication.databinding.ActivityKeyboardBinding

/**
 * @Author zhang tao
 * @Date 9/20/21 11:20 PM
 * @Desc
 */
class KeyBoardActivity : BaseActivity<ActivityKeyboardBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initBinding(): ActivityKeyboardBinding {
        return ActivityKeyboardBinding.inflate(layoutInflater)
    }

    private fun initView() {
        mBinding!!.radioScroll.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.unScrollable) {
                mBinding!!.llContent.visibility = View.VISIBLE
                mBinding!!.scrollView.visibility = View.GONE
            } else {
                mBinding!!.llContent.visibility = View.GONE
                mBinding!!.scrollView.visibility = View.VISIBLE
            }
        }
        mBinding!!.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.adjustNothing) {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
            } else if (checkedId == R.id.adjustUnspecified) {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED)
            } else if (checkedId == R.id.adjustResize) {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            } else if (checkedId == R.id.adjustPan) {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            }
        }
        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)
        Log.e(TAG, "状态栏信息 initView: $rect")
        mBinding!!.root.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            //获取root在窗体的可视区域
            mBinding!!.root.getWindowVisibleDisplayFrame(rect)
            Log.e(TAG, "onGlobalLayout: $rect")
            val height = mBinding!!.root.height
            Log.e(TAG, " Root 高度: $height")
            Log.e(TAG, " status bar 高度: " + rect.top)
            Log.e(TAG, " 窗口 bottom 坐标 : " + rect.bottom)
        }

//        mBinding.getRoot().getViewTreeObserver().addOnWindowFocusChangeListener(new View.OnWindowFocusChangeListener() {
//
//        });
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)
        Log.e(TAG, "状态栏信息 initView: $rect")
    }

    companion object {
        private const val TAG = "KeyBoardActivity"
    }
}