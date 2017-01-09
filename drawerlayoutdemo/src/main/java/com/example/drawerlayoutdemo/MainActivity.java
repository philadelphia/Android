package com.example.drawerlayoutdemo;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout mDrawerLayout;
    private Button btn1;
    private Button btn2;
    private Toolbar mToolBar;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getT().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolBar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        DrawerLayout.DrawerListener drawerListener = new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("open");
                getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle("close");
                getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_map_black_48dp);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.i(TAG, "onDrawerStateChanged: changing");
            }
        };

//
        DrawerLayout.DrawerListener drawerListener1 = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("open");
                getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("close");
                getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_map_black_48dp);
            }
        };

        mDrawerLayout.addDrawerListener(drawerListener1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this,SecondActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this,ThirdActivity.class));
                break;
            default:
                break;
        }
    }
}
