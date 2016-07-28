package com.example.myapplication.ui;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.ui.fragment.BroadCastReceiverFragment;
import com.example.myapplication.ui.fragment.ContentProviderFragment;
import com.example.myapplication.ui.fragment.NotificationFragment;
import com.example.myapplication.ui.fragment.ServiceFragment;
import com.example.myapplication.ui.manager.ActivityManagerFragment;
import com.example.myapplication.ui.manager.PackageManagerFragment;
import com.example.myapplication.ui.manager.WindowManagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerFragment extends Fragment {
    private final String TAG = OtherFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private final String[] mTabTitles = {"Package","Activity","Window"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    private Toolbar toolbar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manager, null);
        initView(view);
        setUpViewPagerAndTabs();
        return  view;
    }



    public void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mTablayout = MainActivity.getmTabLayout();
        toolbar = MainActivity.getToolbar();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode (TabLayout.MODE_FIXED);
    }

    private void setUpViewPagerAndTabs (){
        toolbar.setTitle("Manager");
        mTabFragments = new ArrayList<>();
        mTabFragments.add(new PackageManagerFragment());
        mTabFragments.add(new ActivityManagerFragment());
        mTabFragments.add(new WindowManagerFragment());
        mPagerAdapter = new MyViewPagerAdapter (this.getChildFragmentManager(), mTabFragments, mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }

}
