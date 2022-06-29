package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.databinding.FragmentMaterialDesginBinding
import com.example.myapplication.ui.fragment.materialdesign.CircularRevealFragment
import com.example.myapplication.ui.fragment.materialdesign.RippleFragment
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class MaterialDesignFragment : Fragment() {
    private val TAG = MaterialDesignFragment::class.java.simpleName
    private var mPagerAdapter: FragmentPagerAdapter? = null
    private val fragmentEntityList: MutableList<FragmentEntity> = ArrayList()
    private lateinit var binding: FragmentMaterialDesginBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentMaterialDesginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        setUpViewPagerAndTabs()
    }

    fun initView(view: View?) {
        Log.i(TAG, "initView: ")
    }

    fun setUpViewPagerAndTabs() {
        Log.i(TAG, "setUpViewPagerAndTabs: ")
        fragmentEntityList.clear()
        fragmentEntityList.add(FragmentEntity("Ripple", RippleFragment()))
        fragmentEntityList.add(FragmentEntity("CircularReveal", CircularRevealFragment()))
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
    }
}