package com.example.myapplication.ui.fragment.other

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.adapter.MyViewPagerAdapter
import com.example.myapplication.ui.fragment.customeview.CircleFragment
import com.example.myapplication.ui.fragment.customeview.ColorFragment
import com.example.myapplication.ui.fragment.customeview.RectFragment
import com.google.android.material.tabs.TabLayout
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class CustomViewFragment : Fragment() {
    private var mViewPager: ViewPager? = null
    private val mTabTitles = arrayOf("Color", "Circle", "Rect")
    private var mTabFragments: MutableList<Fragment> = mutableListOf()
    private var mPagerAdapter: MyViewPagerAdapter? = null
    private var mTablayout: TabLayout? = null
    private var toolbar: Toolbar? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_custom_view, container, false)
        mViewPager = view.findViewById<View>(R.id.viewPager) as ViewPager
        initView(view)
        setUpViewPagerAndTabs()
        return view
    }

    fun initView(view: View) {
        mViewPager = view.findViewById<View>(R.id.viewPager) as ViewPager
        mTablayout = view.findViewById(R.id.tabLayout)
        toolbar = (activity as MainActivity?)!!.toolbar
//        mTablayout.setVisibility(View.VISIBLE)
//        mTablayout.setTabMode(TabLayout.MODE_FIXED)
    }

    private fun setUpViewPagerAndTabs() {
        mTabFragments.add(ColorFragment())
        mTabFragments.add(CircleFragment())
        mTabFragments.add(RectFragment())
        //        mTabFragments.add(new PowerFragment());
//        mTabFragments.add(new WindowManagerFragment());
        mPagerAdapter = MyViewPagerAdapter(this.childFragmentManager, mTabFragments, mTabTitles)
        mViewPager!!.adapter = mPagerAdapter
        mTablayout!!.setupWithViewPager(mViewPager)
    }

    companion object {
        private const val TAG = "CustomViewFragment"
    }
}