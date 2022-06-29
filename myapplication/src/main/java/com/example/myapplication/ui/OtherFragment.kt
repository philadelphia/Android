package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.databinding.FragmentOtherBinding
import com.example.myapplication.ui.fragment.other.AnimationFragment
import com.example.myapplication.ui.fragment.other.NotificationFragment
import com.example.myapplication.ui.fragment.other.WebViewFragment
import com.example.myapplication.ui.fragment.other.WindowFragment
import java.util.*

class OtherFragment : Fragment() {
    private val TAG = OtherFragment::class.java.simpleName
    private var mPagerAdapter: FragmentPagerAdapter? = null
    private val fragmentEntityList: MutableList<FragmentEntity> = ArrayList()
    private lateinit var binding: FragmentOtherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentOtherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        setUpViewPagerAndTabs()
    }

    fun initView(view: View?) {
        fragmentEntityList.clear()
        fragmentEntityList.add(FragmentEntity("Window", NotificationFragment()))
        fragmentEntityList.add(FragmentEntity("Notification", WindowFragment()))
        fragmentEntityList.add(FragmentEntity("Animation", AnimationFragment()))
        fragmentEntityList.add(FragmentEntity("WebView", WebViewFragment()))
    }

    private fun setUpViewPagerAndTabs() {
        mPagerAdapter = object : FragmentPagerAdapter(this.childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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
    }

}