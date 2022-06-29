package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.databinding.FragmentCustomViewBinding
import com.example.myapplication.ui.fragment.customeview.*

/**
@Author zhang tao
@Date   6/29/22 11:58 PM
@Desc
 */

class CustomViewFragment : Fragment() {
    private lateinit var mFragmentPagerAdapter: FragmentPagerAdapter
    private val mFragmentEntityList = mutableListOf<FragmentEntity>()
    private lateinit var mBinding: FragmentCustomViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = FragmentCustomViewBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpViewPager()
    }

    private fun initView() {
        mFragmentEntityList.add(FragmentEntity("CustomViewGroup", CustomViewGroupFragment.newInstance()))
        mFragmentEntityList.add(FragmentEntity("Color", ColorFragment.newInstance()))
        mFragmentEntityList.add(FragmentEntity("Circle", CircleFragment.newInstance()))
        mFragmentEntityList.add(FragmentEntity("Rect", RectFragment.newInstance()))
        mFragmentEntityList.add(FragmentEntity("Shape", ShapeFragment.newInstance()))
    }

    private fun setUpViewPager() {
        mFragmentPagerAdapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return mFragmentEntityList[position].fragment
            }

            override fun getCount(): Int {
                return mFragmentEntityList.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return mFragmentEntityList[position].title
            }
        }

        mBinding.viewPager.adapter = mFragmentPagerAdapter
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager)
    }
}