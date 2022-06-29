package com.example.myapplication.ui.fragment.materialdesign

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentCircularRevealBinding

/**
 * A simple [Fragment] subclass.
 */
class CircularRevealFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentCircularRevealBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentCircularRevealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.iv1.setOnClickListener(this)
        binding.iv2.setOnClickListener(this)
        binding.tv1.setOnClickListener(this)
        binding.tv2.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val flag = false
        when (view.id) {
            R.id.iv1 -> {
                Log.i(TAG, "onClick: + vi1")
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    val animator = ViewAnimationUtils.createCircularReveal(binding.iv1, binding.iv1.width / 2, binding.iv1.height / 2, binding.iv1.width.toFloat(), 0f)
                    animator.interpolator = AccelerateDecelerateInterpolator()
                    animator.duration = 1000
                    animator.start()
                }
            }
            R.id.iv2 -> {
                Log.i(TAG, "onClick: + vi2")
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    val animator1 = ViewAnimationUtils.createCircularReveal(binding.iv2, 0, 0, 0f, Math.hypot(binding.iv2.width.toDouble(), binding.iv2.height.toDouble()).toFloat())
                    animator1.interpolator = AccelerateDecelerateInterpolator()
                    animator1.duration = 1000
                    animator1.start()
                }
            }
            R.id.tv1 -> {
                Log.i(TAG, "onClick:tv1 ")
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    binding.tv1.translationZ = 100f
                }
            }
            R.id.tv2 -> {
                Log.i(TAG, "onClick:tv2 ")
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                    binding.tv2.translationZ = 100f
                }
            }
        }
    }

    companion object {
        private val TAG = CircularRevealFragment::class.java.canonicalName
    }
}