package com.example.myapplication.ui.fragment.baseComponent

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.example.myapplication.base.BaseLazyLoadFragment
import com.example.myapplication.databinding.FragmentContentProviderBinding

class ContentProviderFragment : BaseLazyLoadFragment() {
    private lateinit var binding: FragmentContentProviderBinding

    companion object {
        fun newInstance(): ContentProviderFragment {
            return ContentProviderFragment()
        }
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_content_provider
    }

    override fun initView() {}
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentContentProviderBinding.inflate(inflater, container, false)
        return binding.root
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

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri?) {}
}