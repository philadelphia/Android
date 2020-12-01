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
import com.example.myapplication.R;
import com.example.myapplication.base.BaseFragment;
import com.example.myapplication.databinding.FragmentAndroidbaseBinding;
import com.example.myapplication.ui.fragment.baseComponent.ActivityFragment;
import com.example.myapplication.ui.fragment.baseComponent.BroadCastReceiverFragment;
import com.example.myapplication.ui.fragment.baseComponent.ContentProviderFragment;
import com.example.myapplication.ui.fragment.baseComponent.ServiceFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/23.
 */
public class AndroidBaseFragment extends BaseFragment {
    private FragmentPagerAdapter mPagerAdapter;
    private final List<FragmentEntity> fragmentEntityList = new ArrayList<>();
    private FragmentAndroidbaseBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAndroidbaseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

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
        ((MainActivity) getActivity()).getmTabLayout().setVisibility(View.GONE);
        binding.tabLayout.setTabMode(TabLayout.MODE_FIXED);
        fragmentEntityList.clear();
        fragmentEntityList.add(new FragmentEntity("Activity", new ActivityFragment()));
        fragmentEntityList.add(new FragmentEntity("Service", new ServiceFragment()));
        fragmentEntityList.add(new FragmentEntity("BReceiver", new BroadCastReceiverFragment()));
        fragmentEntityList.add(new FragmentEntity("CProvider", new ContentProviderFragment()));
    }

    private void setUpViewPagerAndTabs() {
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

            @Override
            public CharSequence getPageTitle(int position) {
                return fragmentEntityList.get(position).getTitle();
            }

        };
        binding.viewPager.setAdapter(mPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(fragmentEntityList.size());
    }

}
