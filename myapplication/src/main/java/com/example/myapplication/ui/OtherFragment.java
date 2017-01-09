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
import com.example.myapplication.ui.fragment.other.AnimationFragment;
import com.example.myapplication.ui.fragment.other.CameraFragment;
import com.example.myapplication.ui.fragment.other.CustomViewFragment;
import com.example.myapplication.ui.fragment.other.MultiMediaFragment;
import com.example.myapplication.ui.fragment.other.NotificationFragment;
import com.example.myapplication.ui.fragment.other.WebViewFragment;

import java.util.ArrayList;
import java.util.List;


public class OtherFragment extends Fragment  {
    private final String TAG = OtherFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private final String[] mTabTitles = {"Notification", "Animation", "WebView" ,"Camera", "CustomView", "MultiMedia",  "MapService", "Dialog"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    private Toolbar toolbar;
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
        mTablayout =MainActivity.getmTabLayout();
        toolbar = MainActivity.getToolbar();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode (TabLayout.MODE_FIXED);
    }

    private void setUpViewPagerAndTabs (){
        toolbar.setTitle("其他");
        mTabFragments = new ArrayList<>();
        mTabFragments.add(new NotificationFragment());
        mTabFragments.add(new AnimationFragment());
        mTabFragments.add(new WebViewFragment());
        mTabFragments.add(new CameraFragment());
        mTabFragments.add(new CustomViewFragment());
//        mTabFragments.add(new MultiMediaFragment());
        mPagerAdapter = new MyViewPagerAdapter (this.getChildFragmentManager(), mTabFragments, mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
