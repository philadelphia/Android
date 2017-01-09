package com.example.atm.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.atm.MainActivity;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    FragmentManager fm;

    public List<Fragment> mTabFraments;

    private String[] mTabTitles;

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
    }

    public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.fm = fm;
        mTabTitles = titles;
        mTabFraments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mTabFraments.get(position);
    }

    @Override
    public int getCount() {
        return mTabFraments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return mTabTitles[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }

}