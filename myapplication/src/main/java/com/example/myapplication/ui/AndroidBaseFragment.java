package com.example.myapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.MainActivity;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.ui.fragment.ActivityFragment;
import com.example.myapplication.ui.fragment.BroadCastReceiverFragment;
import com.example.myapplication.ui.fragment.ContentProviderFragment;
import com.example.myapplication.ui.fragment.FragmentFragment;
import com.example.myapplication.ui.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/23.
 */
public class AndroidBaseFragment extends android.support.v4.app.Fragment {
    private final String TAG = AndroidBaseFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private final String[] mTabTitles = {"Activity","Service","BReceiver","CProvider"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_androidbase, null);
        initView(view);
        setUpViewPagerAndTabs();
        return view;
    }

    public void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mTablayout =((MainActivity) getActivity ()).mTabLayout;
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode (TabLayout.MODE_FIXED);
    }

    private void setUpViewPagerAndTabs (){
        mTabFragments = new ArrayList<>();
        mTabFragments.add(new ActivityFragment());
//        mTabFragments.add(new FragmentFragment());
        mTabFragments.add(new ServiceFragment());
        mTabFragments.add(new BroadCastReceiverFragment());
        mTabFragments.add(new ContentProviderFragment());
        mPagerAdapter = new MyViewPagerAdapter (this.getChildFragmentManager(), mTabFragments, mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }

    @Override
    public void onStart() {
        Log.i(TAG, "onStart");

        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume");

        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause");

        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop");

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
