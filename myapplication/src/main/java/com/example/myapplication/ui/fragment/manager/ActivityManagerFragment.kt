package com.example.myapplication.ui.fragment.manager

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentActivityManagerBinding

class ActivityManagerFragment : Fragment(), View.OnClickListener {
    private var binding: FragmentActivityManagerBinding? = null
    override fun onAttach(context: Context) {
        Log.i(TAG, "onAttach: ")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentActivityManagerBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i(TAG, "onActivityCreated: ")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    private fun initView() {
        binding!!.btnTestBy.setOnClickListener(this)
        binding!!.btnTestTo.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_testBy -> testScrollBy()
            R.id.btn_testTo -> testeScrollTo()
            else -> {
            }
        }
    }

    private fun testScrollBy() {
        val locations = IntArray(2)
        binding!!.btnTestBy.getLocationOnScreen(locations)
        Log.i(TAG, "testScroll: locations x---" + locations[0] + "\t" + "y--- " + locations[1])
        Log.i(TAG, "testScroll: rawx" + binding!!.btnTestBy.right)
        binding!!.btnTestBy.offsetLeftAndRight(400)
        Log.i(TAG, "testScroll: margin left" + binding!!.btnTestBy.left)
    }

    private fun testeScrollTo() {
        val locations = IntArray(2)
        binding!!.btnTestTo.getLocationOnScreen(locations)
        Log.i(TAG, "testScroll: locations x--- " + locations[0] + "\t" + "y--- " + locations[1])
        Log.i(TAG, "testScroll: rawx" + binding!!.btnTestTo.right)
        binding!!.btnTestTo.scrollTo(-10, -10)
        Log.i(TAG, "testScroll: margin left " + binding!!.btnTestTo.left)
    }

    companion object {
        private const val TAG = "ActivityManagerFragment"
    }
}