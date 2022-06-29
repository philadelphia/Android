package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSendBinding
import com.example.myapplication.ui.activity.FourActivity
import com.example.myapplication.ui.activity.ThirdActivity

/**
 * A simple [Fragment] subclass.
 */
class SendFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentSendBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.secondActivity.setOnClickListener(this)
        binding.thirdActivity.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.second_activity -> startActivity(Intent(this.context, FourActivity::class.java))
            R.id.third_activity -> startActivity(Intent(this.context, ThirdActivity::class.java))
        }
    }
}