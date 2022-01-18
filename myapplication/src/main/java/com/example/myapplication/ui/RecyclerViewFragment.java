package com.example.myapplication.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.MainActivity;
import com.example.myapplication.databinding.FragmentCustomViewBinding;
import com.example.myapplication.ui.fragment.customeview.CircleFragment;
import com.example.myapplication.ui.fragment.customeview.ColorFragment;
import com.example.myapplication.ui.fragment.customeview.RectFragment;
import com.example.myapplication.ui.fragment.customeview.ShapeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {
    private static final String TAG = "CustomViewFragment";
    private TabLayout mTablayout;

    private FragmentPagerAdapter mPagerAdapter;
    private final List<FragmentEntity> fragmentEntityList = new ArrayList<>();
    private FragmentCustomViewBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCustomViewBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setUpViewPagerAndTabs();
    }

    public void initView(View view) {
        mTablayout = ((MainActivity) getActivity()).getTabLayout();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        fragmentEntityList.clear();
        fragmentEntityList.add(new FragmentEntity("Color", new ColorFragment()));
        fragmentEntityList.add(new FragmentEntity("Circle", new CircleFragment()));
        fragmentEntityList.add(new FragmentEntity("Rect", new RectFragment()));
        fragmentEntityList.add(new FragmentEntity("Shape", new ShapeFragment()));

    }

    private void setUpViewPagerAndTabs() {
        mPagerAdapter = new FragmentPagerAdapter(this.getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

            @Override
            public int getCount() {
                return fragmentEntityList.size();
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentEntityList.get(position).getFragment();
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
