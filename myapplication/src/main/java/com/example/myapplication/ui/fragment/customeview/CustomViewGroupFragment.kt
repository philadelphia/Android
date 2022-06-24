package com.example.myapplication.ui.fragment.customeview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentCustomViewgroupBinding

/**
@Author zhang tao
@Date   6/23/22 11:33 PM
@Desc
 */
class CustomViewGroupFragment():Fragment(){
    private lateinit var mBinding:FragmentCustomViewgroupBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentCustomViewgroupBinding.inflate(inflater, container, false)
        return  mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object{

        fun newInstance():CustomViewGroupFragment{
            return CustomViewGroupFragment()
        }
    }
}