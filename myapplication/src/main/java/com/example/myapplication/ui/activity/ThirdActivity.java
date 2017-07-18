package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivity";
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private PackageManager packageManager;
    private final List<PackageInfo> dataList = new ArrayList<>();
    private RecyclerViewAdapter adapter;
    private FloatingActionButton flb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        flb = (FloatingActionButton) findViewById(R.id.fab);
//        flb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ThirdActivity.this, FourActivity.class));
//            }
//        });
        packageManager = getPackageManager();
        initData();

    }

    public void initData(){
        dataList.addAll(packageManager.getInstalledPackages(0).subList(0,20));
        adapter = new RecyclerViewAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }
}
