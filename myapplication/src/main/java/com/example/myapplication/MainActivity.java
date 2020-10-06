package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentManager;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.example.myapplication.ui.AndroidBaseFragment;
import com.example.myapplication.ui.CustomViewFragment;
import com.example.myapplication.ui.DatabaseFragment;
import com.example.myapplication.ui.ManagerFragment;
import com.example.myapplication.ui.MaterialDesginFragment;
import com.example.myapplication.ui.OtherFragment;
import com.example.myapplication.ui.SendFragment;
import com.example.myapplication.ui.ShareFragment;
import com.example.myapplication.ui.activity.FirstActivity;
import com.example.myapplication.ui.fragment.widget.AndroidBaseViewFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private TabLayout mTabLayout;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private ShareActionProvider mShareActionProvider;
    private Menu menu;
    private static final String TAG = "MainActivity";
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        initView();

        //default show android Base Fragment
        AndroidBaseFragment androidBaseFragment = new AndroidBaseFragment();
        ManagerFragment managerFragment = new ManagerFragment();
        OtherFragment otherFragment = new OtherFragment();
        AndroidBaseViewFragment androidBaseViewFragment = new AndroidBaseViewFragment();
//        DatabaseFragment databaseFragment = new DatabaseFragment();
        CustomViewFragment customViewFragment = new CustomViewFragment();

        fragmentManager.beginTransaction().replace(R.id.container, androidBaseViewFragment).commit();


    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i(TAG, "onSaveInstanceState: ");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                onFloatingActionButtonClick();
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
        toast = Toast.makeText(this, "toast", Toast.LENGTH_SHORT);

    }

    private void onFloatingActionButtonClick() {
//        int[] location = new int[2];
//        fab.getLocationInWindow(location);
//
////                location[0] += location[0] + flb.getWidth() / 2;
//        Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
//        intent.putExtra("location", location);
//        startActivity(intent);
//        int systemUiVisibility = fab.getSystemUiVisibility();
//        fab.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//        if ((systemUiVisibility & View.SYSTEM_UI_FLAG_LOW_PROFILE) == View.SYSTEM_UI_FLAG_LOW_PROFILE){
//            fab.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//        }else {
//            fab.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
//        }
//
//        fab.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        try {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        } catch (ActivityNotFoundException ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "TestActivity not found", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
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
        MenuItem itemSearch = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(itemSearch);

        //设置Search View一开始就是展开的
//        searchView.setIconified(false);
        //设置Search View一开始就是展开的,并且不能被隐藏
//        actionView.setIconifiedByDefault(false);
        ImageView goButton = (ImageView) searchView.findViewById(R.id.search_go_btn);
        final ImageView voiceButton = (ImageView) searchView.findViewById(R.id.search_voice_btn);
        searchView.setSubmitButtonEnabled(true);
        voiceButton.setVisibility(View.VISIBLE);
        voiceButton.setImageResource(R.mipmap.ic_settings_voice_white_24dp);
        voiceButton.setEnabled(true);
        //修改提交按钮的背景图片
//        goButton.setImageResource(R.mipmap.ic_settings_voice_white_24dp);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onClick: " + searchView.getQuery());
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Searching....", Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Toast.makeText(MainActivity.this, "Closing....", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "onQueryTextSubmit: " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "onQueryTextChange: " + newText);
                return false;
            }
        });
        searchView.setQueryHint("请输入商品名或者首字母，祝您购物愉快");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Log.i(TAG, "onOptionsItemSelected: setting");
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i(TAG, "add a new item: ");
//              手动添加一项MenuItem.

                Log.i(TAG, "onOptionsItemSelected: action_settings");
                toast.show();

                return true;
            case R.id.action_share:
                share();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Share Test");
        startActivity(Intent.createChooser(sharingIntent, "share"));
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setCheckable(true);
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_camera:
                AndroidBaseFragment androidBaseFragment = new AndroidBaseFragment();
                fragmentManager.beginTransaction().replace(R.id.container, androidBaseFragment).commit();

                break;
            case R.id.nav_BaseView:
                AndroidBaseViewFragment androidBaseViewFragment = new AndroidBaseViewFragment();
                fragmentManager.beginTransaction().replace(R.id.container, androidBaseViewFragment).commit();
                break;

            case R.id.nav_database:
                Log.e(TAG, "onNavigationItemSelected: database");
                DatabaseFragment databaseFragment = new DatabaseFragment();
                fragmentManager.beginTransaction().replace(R.id.container, databaseFragment).commit();
                break;

            case R.id.nav_other:
                OtherFragment otherFragment = new OtherFragment();
                fragmentManager.beginTransaction().replace(R.id.container, otherFragment).commit();
                break;

            case R.id.nav_Manager:
                ManagerFragment managerFragment = new ManagerFragment();
                fragmentManager.beginTransaction().replace(R.id.container, managerFragment).commit();
                break;

            case R.id.nav_CustomView:
                CustomViewFragment customViewFragment = new CustomViewFragment();
                fragmentManager.beginTransaction().replace(R.id.container, customViewFragment).commit();
                break;

            case R.id.nav_materialDesign:
                MaterialDesginFragment materialDesgin = new MaterialDesginFragment();
                fragmentManager.beginTransaction().replace(R.id.container, materialDesgin).commit();
                break;

            case R.id.nav_share:
                ShareFragment shareFragment = new ShareFragment();
                fragmentManager.beginTransaction().replace(R.id.container, shareFragment).commit();
                break;
            case R.id.nav_send:
                SendFragment sendFragment = new SendFragment();
                fragmentManager.beginTransaction().replace(R.id.container, sendFragment).commit();
                break;

            default:
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        toolbar.setTitle(item.getTitle());
        return true;
    }

    public TabLayout getmTabLayout() {
        return mTabLayout;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public FloatingActionButton getFloatingActionBar() {
        return fab;
    }


    public boolean onClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "onConfigurationChanged: ");
        Log.i(TAG, "onConfigurationChanged: " + (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? "Horizontal" : "Vertical"));
    }


}
