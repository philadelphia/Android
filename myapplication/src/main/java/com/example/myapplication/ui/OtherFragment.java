package com.example.myapplication.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.ui.fragment.ActivityFragment;
import com.example.myapplication.ui.fragment.BroadCastReceiverFragment;
import com.example.myapplication.ui.fragment.ContentProviderFragment;
import com.example.myapplication.ui.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;


public class OtherFragment extends Fragment {
    private final String TAG = OtherFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private final String[] mTabTitles = {"Notification","Dialog","BReceiver","CProvider"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other, container, false);

        initView(view);
        setUpViewPagerAndTabs();
        return  view;
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
    public void onDetach() {
        super.onDetach();
    }

}
