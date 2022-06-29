package com.example.myapplication.ui.fragment.materialdesign

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRippleBinding

class RippleFragment : Fragment(), View.OnClickListener {
    private val TAG = RippleFragment::class.java.simpleName
    private lateinit var binding: FragmentRippleBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentRippleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.btn1.setOnClickListener(this)
        binding.btn2.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn1 -> {
            }
            R.id.btn2 -> {
            }
            else -> {
            }
        }
    }
}