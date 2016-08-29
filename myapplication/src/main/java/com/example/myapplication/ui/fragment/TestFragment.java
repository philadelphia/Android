package com.example.myapplication.ui.fragment;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.test.LoaderTestCase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements AdapterView.OnItemClickListener {
    private PackageManager packageManager;
    private List<PackageInfo> installedPackages = new ArrayList<PackageInfo>();
    private ListView listView;
    private  MyBaseAdapter myAdapter;
    private static final String TAG = "TestFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        packageManager = getContext().getPackageManager();
        initDatas();
        initView(view);
        myAdapter = new MyBaseAdapter(installedPackages, LayoutInflater.from(getContext()));
        listView.setAdapter(myAdapter);
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);

    }

    public List<PackageInfo> initDatas(){
        installedPackages.clear();
        installedPackages = packageManager.getInstalledPackages(0);
        Log.i(TAG, "initDatas:  size == " + initDatas().size());

        return  installedPackages;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getContext(), "第" + position + "item was clicked", Toast.LENGTH_SHORT).show();
    }
}
