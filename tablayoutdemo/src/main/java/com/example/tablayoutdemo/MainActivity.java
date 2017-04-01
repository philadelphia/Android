package com.example.tablayoutdemo;

import android.icu.text.BreakIterator;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLogTags;
import android.util.Log;
import android.view.View;

import com.example.tablayoutdemo.fragment.FiveFragment;
import com.example.tablayoutdemo.fragment.FourFragment;
import com.example.tablayoutdemo.fragment.OneFragment;
import com.example.tablayoutdemo.fragment.SevenFragment;
import com.example.tablayoutdemo.fragment.SixFragment;
import com.example.tablayoutdemo.fragment.ThreeFragment;
import com.example.tablayoutdemo.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private int flag;
    private static final String TAG = "MainActivity";
    private int[] drawables = new int[]{R.mipmap.ic_stars_white_36dp, R.mipmap.ic_trending_down_black_36dp};
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        flag = getIntent().getIntExtra("Flag", 1);
        Log.i(TAG, "flag == " + flag);
        setupTabLayout();
    }

    private void setupTabLayout() {
        switch (flag) {
            case 1:
                ShowSimpleTabLayout();
                break;
            case 2:
                ShowScrollableTabLayout();
                break;
            case 3:
                ShowIconForTabLayout();
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    private void ShowSimpleTabLayout() {
        Log.i(TAG, "ShowSimpleTabLayout: ");
        toolbar.setTitle("SimpleLayout");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
        adapter.addFragment(new FourFragment(), "FOUR");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void ShowScrollableTabLayout() {
        Log.i(TAG, "ShowScrollableTabLayout: ");
        toolbar.setTitle("ScrollTabLayout");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        adapter.addFragment(new ThreeFragment(), "THREE");
        adapter.addFragment(new FourFragment(), "FOUR");
        adapter.addFragment(new FiveFragment(), "FIVE");
        adapter.addFragment(new SixFragment(), "SIX");
        adapter.addFragment(new SevenFragment(), "SEVEN");
        viewPager.setAdapter(adapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(viewPager);

    }


    private void ShowIconForTabLayout() {
        Log.i(TAG, "ShowSimpleTabLayout: ");
        toolbar.setTitle("Icon & Title Layout");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "ONE");
        adapter.addFragment(new TwoFragment(), "TWO");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(drawables[0]);
        tabLayout.getTabAt(1).setIcon(drawables[1]);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList= new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
//          当返回null的时候就不返回title了，这时候就只有icon了
//            return  null;
        }
    }


}


