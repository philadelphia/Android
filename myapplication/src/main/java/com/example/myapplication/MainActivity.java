package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import com.example.myapplication.ui.AndroidBaseFragment;
import com.example.myapplication.ui.AndroidBaseViewFragment;
import com.example.myapplication.ui.ManagerFragment;
import com.example.myapplication.ui.MaterialDesginFragment;
import com.example.myapplication.ui.OtherFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager ;
    private static Toolbar toolbar ;
    private static FloatingActionButton fab;
    private static TabLayout mTabLayout;
    private static DrawerLayout drawer;
    private static ActionBarDrawerToggle toggle;
    private static NavigationView navigationView;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        initView();

        //default show android Base Fragment
        AndroidBaseViewFragment androidBaseViewFragment = new AndroidBaseViewFragment();
        fragmentManager.beginTransaction().replace(R.id.container, androidBaseViewFragment).commit();


    }

    public  void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setVisibility(View.INVISIBLE);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.i(TAG, "onOptionsItemSelected: setting");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setCheckable(true);
        int id = item.getItemId();
        switch (id){
            case R.id.nav_camera:
            AndroidBaseFragment androidBaseFragment = new AndroidBaseFragment();
            fragmentManager.beginTransaction().replace(R.id.container, androidBaseFragment).commit();

            break;
            case R.id.nav_BaseView:
                AndroidBaseViewFragment androidBaseViewFragment = new AndroidBaseViewFragment();
                fragmentManager.beginTransaction().replace(R.id.container, androidBaseViewFragment).commit();
            break;

            case R.id.nav_other:
            OtherFragment otherFragment = new OtherFragment();
            fragmentManager.beginTransaction().replace(R.id.container, otherFragment).commit();
            break;

           case  R.id.nav_Manager:
               ManagerFragment managerFragment = new ManagerFragment();
               fragmentManager.beginTransaction().replace(R.id.container, managerFragment).commit();
            break;

            case R.id.nav_send:

            case R.id.nav_materialDesign:
                MaterialDesginFragment materialDesgin = new MaterialDesginFragment();
                fragmentManager.beginTransaction().replace(R.id.container, materialDesgin).commit();
            break;
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static TabLayout getmTabLayout() {
        return mTabLayout;
    }

    public static Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: " );
        super.onRestart();
    }


    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

}
