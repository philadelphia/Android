package com.example.myapplication.ui;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.ui.fragment.BroadCastReceiverFragment;
import com.example.myapplication.ui.fragment.CircularRevealFragment;
import com.example.myapplication.ui.fragment.ContentProviderFragment;
import com.example.myapplication.ui.fragment.NotificationFragment;
import com.example.myapplication.ui.fragment.RippleFragment;
import com.example.myapplication.ui.fragment.ServiceFragment;

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
        mTablayout =MainActivity.getmTabLayout();
        toolbar = MainActivity.getToolbar();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode (TabLayout.MODE_FIXED);
        toolbar.setTitle("Material Design");
        mTabFragments = new ArrayList<>();
        mTabFragments.add(new RippleFragment());
        mTabFragments.add(new CircularRevealFragment());
        mPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(),mTabFragments,mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }

}
