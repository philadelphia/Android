package com.example.myapplication.ui.fragment.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentAnimationBinding

/**
 * A simple [Fragment] subclass.
 */
class AnimationFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentAnimationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.img.setOnClickListener(this)
        binding.btnAlpha.setOnClickListener(this)
        binding.btnScale.setOnClickListener(this)
        binding.btnTranslate.setOnClickListener(this)
        binding.btnRotate.setOnClickListener(this)
        binding.btnAnimationSet.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.img -> {
            }
            R.id.btn_alpha -> {
                val alphaAnimation = AlphaAnimation(0.toFloat(), 1.toFloat())
                alphaAnimation.duration = 2000
                binding.img.startAnimation(alphaAnimation)
            }
            R.id.btn_scale -> {
                val scaleAnimation = ScaleAnimation(0.toFloat(), 2.toFloat(), 0.toFloat(), 3.toFloat())
                scaleAnimation.duration = 2000
                binding.img.startAnimation(scaleAnimation)
            }
            R.id.btn_translate -> {
                val translateAnimation = TranslateAnimation(0.toFloat(), 200.toFloat(), 100.toFloat(), 300.toFloat())
                translateAnimation.duration = 2000
                binding.img.startAnimation(translateAnimation)
            }
            R.id.btn_rotate -> {
                val rotateAnimation = RotateAnimation(0.toFloat(), 0.toFloat(), 0.toFloat(), 360.toFloat())
                rotateAnimation.duration = 2000
                binding.img.startAnimation(rotateAnimation)
            }
            R.id.btn_animationSet -> {
                val animationSet = AnimationSet(true)
                animationSet.duration = 2000
                val scaleAnimation1 = ScaleAnimation(0.toFloat(), 2.toFloat(), 0.toFloat(), 3.toFloat())
                scaleAnimation1.duration = 2000
                val translateAnimation1 = TranslateAnimation(0.toFloat(), 200.toFloat(), 0.toFloat(), 300.toFloat())
                translateAnimation1.duration = 2000
                animationSet.addAnimation(scaleAnimation1)
                animationSet.addAnimation(translateAnimation1)
                binding.img.startAnimation(animationSet)
            }
            else -> {
            }
        }
    }

    companion object {
        private const val TAG = "AnimationFragment"
    }
}