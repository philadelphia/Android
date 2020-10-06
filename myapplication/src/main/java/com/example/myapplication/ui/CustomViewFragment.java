package com.example.myapplication.ui;


import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MyViewPagerAdapter;
import com.example.myapplication.ui.fragment.customeview.CircleFragment;
import com.example.myapplication.ui.fragment.customeview.ColorFragment;
import com.example.myapplication.ui.fragment.customeview.RectFragment;
import com.example.myapplication.ui.fragment.customeview.ShapeFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomViewFragment extends Fragment {

    private static final String TAG = "CustomViewFragment";
    private ViewPager mViewPager;
//    private final String[] mTabTitles = {"Color", "Circle", "Rect", "Point", "Line", "Oval", "Arc", "Histogram", "PeiChart", "Path"};
    private final String[] mTabTitles = {"Color", "Circle", "Rect", "Shape"};
    private List<Fragment> mTabFragments ;
    private MyViewPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view = inflater.inflate(R.layout.fragment_custom_view, container,false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        initView(view);
        setUpViewPagerAndTabs();
        return  view;
    }

    public void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mTablayout = ((MainActivity) getActivity()).getmTabLayout();
        toolbar = ((MainActivity) getActivity()).getToolbar();
        mTablayout.setVisibility(View.VISIBLE);
        mTablayout.setTabMode (TabLayout.MODE_SCROLLABLE);
    }

    private void setUpViewPagerAndTabs (){
        mTabFragments = new ArrayList<>();
        mTabFragments.add(new ColorFragment());
        mTabFragments.add(new CircleFragment());
        mTabFragments.add(new RectFragment());
        mTabFragments.add(new ShapeFragment());
//        mTabFragments.add(new PointFragment());
//        mTabFragments.add(new LineFragment());
//        mTabFragments.add(new OvalFragment());
//        mTabFragments.add(new ArcFragment());
//        mTabFragments.add(new HistoGramFragment());
//        mTabFragments.add(new PieChartViewFragment());
//        mTabFragments.add(new PathFragment());
        mPagerAdapter = new MyViewPagerAdapter(this.getChildFragmentManager(), mTabFragments, mTabTitles);
        mViewPager.setAdapter (mPagerAdapter);
        mTablayout.setupWithViewPager (mViewPager);
    }


}
