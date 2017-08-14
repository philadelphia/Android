package com.example.myapplication.ui.fragment.other;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.ui.fragment.customeview.CircleFragment;
import com.example.myapplication.ui.fragment.customeview.ColorFragment;
import com.example.myapplication.ui.fragment.customeview.RectFragment;
import com.example.myapplication.ui.fragment.manager.ActivityManagerFragment;
import com.example.myapplication.ui.fragment.manager.PackageManagerFragment;
import com.example.myapplication.ui.fragment.manager.PowerFragment;
import com.example.myapplication.ui.fragment.manager.TestFragment;
import com.example.myapplication.ui.fragment.manager.WindowManagerFragment;
import com.example.myapplication.utils.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomViewFragment extends Fragment {

    private static final String TAG = "CustomViewFragment";
    private ViewPager mViewPager;
    private final String[] mTabTitles = {"Color", "Circle", "Rect"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view = inflater.inflate(R.layout.fragment_custom_view, container,false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
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
        mTabFragments.add(new ColorFragment());
        mTabFragments.add(new CircleFragment());
        mTabFragments.add(new RectFragment());
//        mTabFragments.add(new PowerFragment());
//        mTabFragments.add(new WindowManagerFragment());
        mPagerAdapter = new MyViewPagerAdapter(this.getChildFragmentManager(), mTabFragments, mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }


}
