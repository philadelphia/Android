package com.example.myapplication.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.MainActivity;
import com.example.myapplication.databinding.FragmentMaterialDesginBinding;
import com.example.myapplication.ui.fragment.materialdesign.CircularRevealFragment;
import com.example.myapplication.ui.fragment.materialdesign.RippleFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialDesginFragment extends Fragment {
    private final String TAG = MaterialDesginFragment.class.getSimpleName();
    private TabLayout mTablayout;
    private Toolbar toolbar;
    private FragmentPagerAdapter mPagerAdapter;
    private final List<FragmentEntity> fragmentEntityList = new ArrayList<>();
    private FragmentMaterialDesginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        binding = FragmentMaterialDesginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        setUpViewPagerAndTabs();
    }

    public void initView(View view) {
        Log.i(TAG, "initView: ");
    }

    public void setUpViewPagerAndTabs() {
        Log.i(TAG, "setUpViewPagerAndTabs: ");
        mTablayout = ((MainActivity) getActivity()).getmTabLayout();
        toolbar = ((MainActivity) getActivity()).getToolbar();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode(TabLayout.MODE_FIXED);

        fragmentEntityList.clear();
        fragmentEntityList.add(new FragmentEntity("Ripple", new RippleFragment()));
        fragmentEntityList.add(new FragmentEntity("CircularReveal", new CircularRevealFragment()));


        mPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentEntityList.get(position).getFragment();
            }

            @Override
            public int getCount() {
                return fragmentEntityList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return fragmentEntityList.get(position).getTitle();
            }
        };
        binding.viewPager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(binding.viewPager);
    }

}
