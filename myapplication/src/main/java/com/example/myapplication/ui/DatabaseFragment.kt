package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.databinding.FragmentDatabaseBinding
import com.example.myapplication.ui.fragment.database.CreateDBFragment
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DatabaseFragment : Fragment() {
    private var mPagerAdapter: FragmentPagerAdapter? = null
    private val fragmentEntityList: MutableList<FragmentEntity> = ArrayList()
    private lateinit var binding: FragmentDatabaseBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDatabaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
        setUpViewPagerAndTabs()
    }

    fun initView(view: View?) {
        fragmentEntityList.clear()
        fragmentEntityList.add(FragmentEntity("CreateDB", CreateDBFragment()))
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

    companion object {
        private const val TAG = "DatabaseFragment"
    }
}