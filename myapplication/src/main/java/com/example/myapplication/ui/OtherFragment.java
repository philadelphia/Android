package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myapplication.databinding.FragmentOtherBinding;
import com.example.myapplication.ui.fragment.other.AnimationFragment;
import com.example.myapplication.ui.fragment.other.NotificationFragment;
import com.example.myapplication.ui.fragment.other.WebViewFragment;
import com.example.myapplication.ui.fragment.other.WindowFragment;

import java.util.ArrayList;
import java.util.List;


public class OtherFragment extends Fragment {
    private final String TAG = OtherFragment.class.getSimpleName();
    private FragmentPagerAdapter mPagerAdapter;
    private final List<FragmentEntity> fragmentEntityList = new ArrayList<>();
    private FragmentOtherBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOtherBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setUpViewPagerAndTabs();
    }

    public void initView(View view) {
        fragmentEntityList.clear();
        fragmentEntityList.add(new FragmentEntity("Window", new NotificationFragment()));
        fragmentEntityList.add(new FragmentEntity("Notification", new WindowFragment()));
        fragmentEntityList.add(new FragmentEntity("Animation", new AnimationFragment()));
        fragmentEntityList.add(new FragmentEntity("WebView", new WebViewFragment()));

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

            @Override
            public CharSequence getPageTitle(int position) {
                return fragmentEntityList.get(position).getTitle();
            }

        };
        binding.viewPager.setAdapter(mPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
