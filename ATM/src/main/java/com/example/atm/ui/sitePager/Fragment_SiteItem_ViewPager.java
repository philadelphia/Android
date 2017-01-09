package com.example.atm.ui.sitePager;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.adapter.MyViewPagerAdapter;
import com.example.atm.utils.Constatnts;


public class Fragment_SiteItem_ViewPager extends Fragment implements ViewPager.OnPageChangeListener  {
	private final String TAG = Fragment_SiteItem_ViewPager.class.getSimpleName();
    private ViewPager mViewPager;
//    private final String[] mTabTitles = {"ALARM","MEASUREMENT","IMAGE","CONTROL","REPORT TO NOC","INFO"};
    private final String[] mTabTitles = {"ALARM","REPORT TO NOC","INFO"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
    }

    @SuppressLint("InflateParams") @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_siteitem_viewpager, container,false);
        Log.i(TAG, "onCreateView: view" + view.getClass().getSimpleName());
        Log.i(TAG, "onCreateView: container" + container.getClass().getSimpleName());
        initView(view);
        setUpViewPagerAndTabs();
        return view;
    }

    public void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.addOnPageChangeListener(this);
        mTablayout = MainActivity.getTabLayout();
        mTablayout.setVisibility(View.VISIBLE);
        
    }

    private void setUpViewPagerAndTabs (){
        mTabFragments = new ArrayList<>();
        SiteAlarmFragment siteAlarmFragment = new SiteAlarmFragment();
        siteAlarmFragment.setArguments(getArguments());
        
//        EnergyChartFragment energyChartFragment = new EnergyChartFragment();
//        energyChartFragment.setArguments(getArguments());
        
//        SiteImageFragment siteImageFragment = new SiteImageFragment();
//        siteImageFragment.setArguments(getArguments());
//
//        ControlFragment controlFragment = new ControlFragment();
//        controlFragment.setArguments(getArguments());

        ReportFragment reportFragment = new ReportFragment();
        reportFragment.setArguments(getArguments());
//
        SiteInfoFragment siteInfoFragment = new SiteInfoFragment();
        siteInfoFragment.setArguments(getArguments());
//
        mTabFragments.add(siteAlarmFragment);
//        mTabFragments.add(energyChartFragment);
//        mTabFragments.add(siteImageFragment);
//        mTabFragments.add(controlFragment);
        mTabFragments.add(reportFragment);
        mTabFragments.add(siteInfoFragment);
//
        MainActivity.setActionBarTitle(getArguments().getString(Constatnts.SITENAME),getArguments().getString(Constatnts.SITEID));
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
        MainActivity.getTabLayout().setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        mTablayout.setVisibility(View.GONE);
        super.onDestroy();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.i(TAG, "onPageSelected: position" + position);
        hideFloatingButton(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void hideFloatingButton(int position) {
        if (position ==1){
            MainActivity.getFloatingActionButton().setVisibility(View.VISIBLE);
        }else{
            MainActivity.getFloatingActionButton().setVisibility(View.INVISIBLE);
        }
    }

//    public void hideOptionsMenu(int position) {
//       if(position != 1){
////           MainActivity.getmToolBar().getMenu().getItem(R.id.send).setVisible(false);
////           MainActivity.getmToolBar().getMenu().getItem(R.id.camera).setVisible(false);
//           MainActivity.getmToolBar().getMenu().removeItem(R.id.camera);
//           MainActivity.getmToolBar().getMenu().removeItem(R.id.send);
//        }
//    }
}
