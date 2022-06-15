package com.example.myapplication.ui;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.databinding.FragmentManagerBinding;
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
    private FragmentPagerAdapter mPagerAdapter;
    private final List<FragmentEntity> fragmentEntityList = new ArrayList<>();
    private FragmentManagerBinding binding;

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
        Log.i(TAG, "onCreateView: ");
        binding = com.example.myapplication.databinding.FragmentManagerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        setUpViewPagerAndTabs();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }


    public void initView(View view) {
    }

    private void setUpViewPagerAndTabs() {
        fragmentEntityList.clear();
        fragmentEntityList.add(new FragmentEntity("Package", new PackageManagerFragment()));
        fragmentEntityList.add(new FragmentEntity("Test", new TestFragment()));
        fragmentEntityList.add(new FragmentEntity("Activity", new ActivityManagerFragment()));
        fragmentEntityList.add(new FragmentEntity("Power", new PowerFragment()));
        fragmentEntityList.add(new FragmentEntity("PowWindowManager", new WindowManagerFragment()));


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
