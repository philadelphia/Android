package com.example.myapplication.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.View;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.ui.fragment.baseComponent.ActivityFragment;
import com.example.myapplication.ui.fragment.baseComponent.BroadCastReceiverFragment;
import com.example.myapplication.ui.fragment.baseComponent.ContentProviderFragment;
import com.example.myapplication.ui.fragment.baseComponent.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Tao.ZT.Zhang on 2016/7/23.
 */
public class AndroidBaseFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    private final String[] mTabTitles = {"Activity", "Service", "BReceiver", "CProvider"};
    private List<Fragment> mTabFragments;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        setUpViewPagerAndTabs();
    }


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_androidbase;
    }

    @Override
    public void initView() {
        mTablayout = ((MainActivity) getActivity()).getmTabLayout();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);
//        mTablayout.addTab(new TabLayout.Tab());
    }

    private void setUpViewPagerAndTabs() {
        mTabFragments = new ArrayList<>();
        mTabFragments.add(new ActivityFragment());
        mTabFragments.add(new ServiceFragment());
        mTabFragments.add(new BroadCastReceiverFragment());
        mTabFragments.add(new ContentProviderFragment());
        mPagerAdapter = new MyViewPagerAdapter (this.getChildFragmentManager(), mTabFragments, mTabTitles);
        viewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (viewPager);
        viewPager.setOffscreenPageLimit(mTabFragments.size());
    }

}
