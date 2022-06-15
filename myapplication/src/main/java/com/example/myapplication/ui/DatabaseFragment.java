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
import com.example.myapplication.databinding.FragmentDatabaseBinding;
import com.example.myapplication.ui.fragment.database.CreateDBFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatabaseFragment extends Fragment {

    private static final String TAG = "DatabaseFragment";
    private FragmentPagerAdapter mPagerAdapter;
    private final List<FragmentEntity> fragmentEntityList = new ArrayList<>();
    private FragmentDatabaseBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDatabaseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        setUpViewPagerAndTabs();
    }

    public void initView(View view) {
        fragmentEntityList.clear();
        fragmentEntityList.add(new FragmentEntity("CreateDB", new CreateDBFragment()));
    }

    private void setUpViewPagerAndTabs() {

        mPagerAdapter = new FragmentPagerAdapter(this.getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }
}
