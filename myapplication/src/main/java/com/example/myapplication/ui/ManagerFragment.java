package com.example.myapplication.ui;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
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
import com.example.myapplication.ui.fragment.manager.ActivityManagerFragment;
import com.example.myapplication.ui.fragment.manager.PackageManagerFragment;
import com.example.myapplication.ui.fragment.manager.PowerFragment;
import com.example.myapplication.ui.fragment.manager.TestFragment;
import com.example.myapplication.ui.fragment.manager.WindowManagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManagerFragment extends Fragment {
    private final String TAG = OtherFragment.class.getSimpleName();
    private ViewPager mViewPager;
    private final String[] mTabTitles = {"Package", "Test", "Activity", "Power", "WindowManager"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    private Toolbar toolbar;


    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manager, null);
        initView(view);
        setUpViewPagerAndTabs();
        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
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
        mTabFragments.add(new PackageManagerFragment());
        mTabFragments.add(new TestFragment());
        mTabFragments.add(new ActivityManagerFragment());
        mTabFragments.add(new PowerFragment());
        mTabFragments.add(new WindowManagerFragment());
        mPagerAdapter = new MyViewPagerAdapter (this.getChildFragmentManager(), mTabFragments, mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }

}
