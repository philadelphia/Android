package com.example.atm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.atm.ui.sitelist.SiteListFragment;
import com.example.atm.ui.troubleticket.TroubleTicketListFragment;
import com.example.atm.ui.userinfo.UserInfoFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static TabLayout mTabLayout;
    private static Toolbar mToolBar;
    private static FloatingActionButton mFab;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
//        UserInfoFragment userInfoFragment = new UserInfoFragment();
//        SiteListFragment siteListFragment = new SiteListFragment();
        TroubleTicketListFragment troubleTicketListFragment = new TroubleTicketListFragment();
        fragmentManager.beginTransaction().replace(R.id.container, troubleTicketListFragment).commit();
        initView();
    }

    public void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    public static void setActionBarTitle(String title,String subTitle) {
        mToolBar.setTitle(title);
        mToolBar.setSubtitle(subTitle);
    }

    public static Toolbar getmToolBar(){
        return  mToolBar;
    }
    public static TabLayout getTabLayout(){
        return  mTabLayout;
    }

    public static FloatingActionButton getFloatingActionButton(){
        return  mFab;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_userInfo:
                fragmentManager.beginTransaction().replace(R.id.container, new UserInfoFragment()).commit();
                break;
            case R.id.nav_siteList:
                fragmentManager.beginTransaction().replace(R.id.container, new SiteListFragment()).commit();
                break;

            case R.id.nav_siteMap:
                break;

            case R.id.nav_bookmark:
                break;
            case R.id.nav_troubleTicket:
                fragmentManager.beginTransaction().replace(R.id.container, new TroubleTicketListFragment()).commit();
                break;
            default:
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
