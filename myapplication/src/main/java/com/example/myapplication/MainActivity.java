package com.example.myapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.FileObserver;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.ui.AndroidBaseFragment;
import com.example.myapplication.ui.CustomViewFragment;
import com.example.myapplication.ui.DatabaseFragment;
import com.example.myapplication.ui.ManagerFragment;
import com.example.myapplication.ui.MaterialDesignFragment;
import com.example.myapplication.ui.OtherFragment;
import com.example.myapplication.ui.SendFragment;
import com.example.myapplication.ui.ShareFragment;
import com.example.myapplication.ui.fragment.widget.AndroidWidgetFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.File;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle toggle;
    private static final String TAG = "MainActivity";
    private Toast toast;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

        //default show android Base Fragment
        AndroidBaseFragment androidBaseFragment = new AndroidBaseFragment();
        ManagerFragment managerFragment = new ManagerFragment();
        OtherFragment otherFragment = new OtherFragment();
        AndroidWidgetFragment androidBaseViewFragment = new AndroidWidgetFragment();
//        DatabaseFragment databaseFragment = new DatabaseFragment();
        CustomViewFragment customViewFragment = new CustomViewFragment();
        fragmentManager.beginTransaction().replace(R.id.container, managerFragment).commit();
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
        setSupportActionBar(binding.main.toolbar);
        fragmentManager = getSupportFragmentManager();
        binding.main.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFloatingActionButtonClick();
            }
        });

        toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.main.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        binding.main.slidingTabs.setVisibility(View.GONE);
        binding.navView.setNavigationItemSelectedListener(this);
        toast = Toast.makeText(this, "toast", Toast.LENGTH_SHORT);

        binding.main.container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                binding.getRoot().getWindowVisibleDisplayFrame(rect);
//                if (rect.bottom < 1920) {
//                    binding.main.contentMain.btnTest.setVisibility(View.GONE);
//                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.main.contentMain.container.getLayoutParams();
////                    layoutParams.bottomMargin = 120;
////                    binding.main.contentMain.container.setLayoutParams(layoutParams);
//                } else {
//                    binding.main.contentMain.btnTest.setVisibility(View.VISIBLE);
//                    ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) binding.main.contentMain.container.getLayoutParams();
//                    layoutParams.bottomMargin = 0;
//                    binding.main.contentMain.container.setLayoutParams(layoutParams);
//                }
            }
        });

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

        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);

//        try {
//            Intent intent = new Intent(this, TestActivity.class);
//            startActivity(intent);
//        } catch (ActivityNotFoundException ex) {
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "TestActivity not found", Toast.LENGTH_SHORT).show();
//        }
//        Intent intent = new Intent(this, FirstActivity.class);
//        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
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

        searchView.setOnSearchClickListener(v -> Toast.makeText(MainActivity.this, "Searching....", Toast.LENGTH_SHORT).show());

        searchView.setOnCloseListener(() -> {
            Toast.makeText(MainActivity.this, "Closing....", Toast.LENGTH_SHORT).show();
            return false;
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


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setCheckable(true);
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_camera:
                switchFragment(new AndroidBaseFragment());
                break;

            case R.id.nav_BaseView:
                switchFragment(new AndroidWidgetFragment());
                break;

            case R.id.nav_database:
                Log.e(TAG, "onNavigationItemSelected: database");
                switchFragment(new DatabaseFragment());
                break;

            case R.id.nav_other:
                switchFragment(new OtherFragment());
                break;

            case R.id.nav_Manager:
                switchFragment(new ManagerFragment());
                break;

            case R.id.nav_CustomView:
                switchFragment(new CustomViewFragment());
                break;
            case R.id.nav_RecyclerView:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;

            case R.id.nav_materialDesign:
                switchFragment(new MaterialDesignFragment());
                break;

            case R.id.nav_share:
                switchFragment(new ShareFragment());
                break;

            case R.id.nav_send:
                switchFragment(new SendFragment());
                break;

            default:
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        binding.main.toolbar.setTitle(item.getTitle());
        return true;
    }

    public TabLayout getTabLayout() {
        return binding.main.slidingTabs;
    }


    public Toolbar getToolbar() {
        return binding.main.toolbar;
    }

    public FloatingActionButton getFloatingActionBar() {
        return binding.main.fab;
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


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
//        testAnr();
        binding.drawerLayout.performClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");


    }

    private void switchFragment(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
    }

//    private void testRxjava() {
//        android.database.Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onComplete();
//            }
//        });
//        android.database.Observable<String> map = observable.map(new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) throws Exception {
//                return integer.toString();
//            }
//        });
//
//        android.database.Observable<String> stringObservable = map.subscribeOn(Schedulers.io());
//        android.database.Observable<String> stringObservable1 = stringObservable.observeOn(AndroidSchedulers.mainThread());
//        stringObservable1.subscribe(new Observer<String>() {
//
//
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, s);
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
//    }

    private void  testAnr(){
        File file = new File("/data/anr");
        FileObserver fileObserver = new FileObserver("/data/anr") {
            @Override
            public void onEvent(int event, @Nullable String path) {
                Log.e(TAG, "onEvent: +" + path);
            }
        };
        fileObserver.startWatching();
    }
}
