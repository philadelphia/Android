package com.example.myapplication.ui;


import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.ui.fragment.materialdesign.CircularRevealFragment;
import com.example.myapplication.ui.fragment.materialdesign.RippleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialDesginFragment extends Fragment {
    private final String TAG = MaterialDesginFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private final String[] mTabTitles = {"Ripple","CircularReveal"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    private Toolbar toolbar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_material_desgin,null);
        initView(view);
        setUpViewPagerAndTabs();
        return  view;
    }


    public void initView(View view) {
        Log.i(TAG, "initView: ");
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager1);

    }

    public void setUpViewPagerAndTabs (){
        Log.i(TAG, "setUpViewPagerAndTabs: ");
        mTablayout =((MainActivity) getActivity()).getmTabLayout();
        toolbar = ((MainActivity) getActivity()).getToolbar();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode (TabLayout.MODE_FIXED);
        mTabFragments = new ArrayList<>();
        mTabFragments.add(new RippleFragment());
        mTabFragments.add(new CircularRevealFragment());
        mPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(),mTabFragments,mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }

}
