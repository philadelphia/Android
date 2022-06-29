package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.databinding.FragmentManagerBinding
import com.example.myapplication.ui.fragment.manager.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
private const val TAG = "ManagerFragment"

class ManagerFragment : Fragment() {
    private var mPagerAdapter: FragmentPagerAdapter? = null
    private val fragmentEntityList: MutableList<FragmentEntity> = ArrayList()
    private lateinit var binding: FragmentManagerBinding
    override fun onAttach(context: Context) {
        Log.i(TAG, "onAttach: ")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentManagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        setUpViewPagerAndTabs()
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    fun initView(view: View?) {}

    private fun setUpViewPagerAndTabs() {
        fragmentEntityList.clear()
        fragmentEntityList.add(FragmentEntity("Package", PackageManagerFragment()))
        fragmentEntityList.add(FragmentEntity("Test", TestFragment()))
        fragmentEntityList.add(FragmentEntity("Activity", ActivityManagerFragment()))
        fragmentEntityList.add(FragmentEntity("Power", PowerFragment()))
        fragmentEntityList.add(FragmentEntity("PowWindowManager", WindowManagerFragment()))
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