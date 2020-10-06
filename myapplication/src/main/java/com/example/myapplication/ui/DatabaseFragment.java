package com.example.myapplication.ui;


import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.ui.fragment.database.CreateDBFragment;
import com.example.myapplication.ui.fragment.manager.TestFragment;
import com.example.myapplication.ui.fragment.manager.WindowManagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatabaseFragment extends Fragment {

    private static final String TAG = "DatabaseFragment";
    private ViewPager mViewPager;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    private Toolbar toolbar;
    private List<Fragment> mTabFragments ;
    private String[] mTabTitles =  {"CreateDB", "Test2", "Test3", "Test4"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_database, container, false);
        initView(view);
        setUpViewPagerAndTabs();
        return  view;
    }


    public void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mTablayout = ((MainActivity) getActivity()).getmTabLayout();
        toolbar = ((MainActivity) getActivity()).getToolbar();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode (TabLayout.MODE_FIXED);
    }

    private void setUpViewPagerAndTabs (){
        mTabFragments = new ArrayList<>();
        mTabFragments.add(new CreateDBFragment());
        mTabFragments.add(new TestFragment());
        mTabFragments.add(new WindowManagerFragment());
        mTabFragments.add(new WindowManagerFragment());
        mPagerAdapter = new MyViewPagerAdapter (this.getChildFragmentManager(), mTabFragments, mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }
}
