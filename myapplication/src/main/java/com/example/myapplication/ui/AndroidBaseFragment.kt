package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentAndroidbaseBinding
import com.example.myapplication.ui.fragment.baseComponent.ActivityFragment
import com.example.myapplication.ui.fragment.baseComponent.BroadCastReceiverFragment
import com.example.myapplication.ui.fragment.baseComponent.ContentProviderFragment
import com.example.myapplication.ui.fragment.baseComponent.ServiceFragment
import com.google.android.material.tabs.TabLayout
import java.util.*

/**
 * Created by Tao.ZT.Zhang on 2016/7/23.
 */
class AndroidBaseFragment : BaseFragment() {
    private lateinit var mPagerAdapter: FragmentPagerAdapter
    private val fragmentEntityList: MutableList<FragmentEntity> = ArrayList()
    private lateinit var binding: FragmentAndroidbaseBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAndroidbaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpViewPagerAndTabs()
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_androidbase
    }

    public override fun initView() {
        binding.tabLayout.tabMode = TabLayout.MODE_FIXED
        fragmentEntityList.clear()
        fragmentEntityList.add(FragmentEntity("Activity", ActivityFragment.newInstance()))
        fragmentEntityList.add(FragmentEntity("Service", ServiceFragment.newInstance()))
        fragmentEntityList.add(FragmentEntity("BReceiver", BroadCastReceiverFragment.newInstance()))
        fragmentEntityList.add(FragmentEntity("CProvider", ContentProviderFragment.newInstance()))
    }

    private fun setUpViewPagerAndTabs() {
        mPagerAdapter = object : FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int): Fragment {
                return fragmentEntityList[position].fragment
            }

            override fun getCount(): Int {
                return fragmentEntityList.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return fragmentEntityList[position].title
            }
        }
        binding.viewPager.adapter = mPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        binding.viewPager.offscreenPageLimit = fragmentEntityList.size
    }
}