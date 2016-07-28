package com.example.myapplication.ui.manager;


import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.utils.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackageManagerFragment extends Fragment implements CustomItemClickListener{
    private PackageManager packageManager;
    private List<PackageInfo> installedPackages;
    private List<String > pkgNameList;
    private RecyclerViewAdapter myAdapter ;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    private  RecyclerView recyclerView;
    private static final String TAG = "PackageManagerFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_package_manager, null);
        packageManager = getContext().getPackageManager();
        ButterKnife.bind(this, view);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        initData();
        myAdapter = new RecyclerViewAdapter(installedPackages);
        myAdapter.setOnCustomeItemClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);
        return view;

    }
    public void initData(){
        Log.i(TAG, "initData: ");
        installedPackages = packageManager.getInstalledPackages(0);
        Log.i(TAG, "installedPackages: " + installedPackages.size());
//        pkgNameList = new ArrayList<>();
//        for (PackageInfo pkginfo :installedPackages
//             ) {
//            String packageName = pkginfo.packageName;
//            pkgNameList.add(packageName);
//        }
//
//        Log.i(TAG, "pkgNameList: " + pkgNameList.size());
    }

    @OnClick(R.id.recyclerView)
    public void onClick() {
    }

    @Override
    public void onItemClick(View v, int position) {
        Snackbar.make(recyclerView,"ddd",Snackbar.LENGTH_SHORT).show();
    }
}
