package com.example.myapplication

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.os.FileObserver
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.taskmanager.TaskUtils
import com.example.myapplication.ui.*
import com.example.myapplication.ui.fragment.widget.AndroidWidgetFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import java.io.File

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var toggle: ActionBarDrawerToggle? = null
    private var toast: Toast? = null
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: ")
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        DialogFragment().show(this.supportFragmentManager, "")
        //default show android Base Fragment
        val androidBaseFragment = AndroidBaseFragment()
        val managerFragment = ManagerFragment()
        val otherFragment = OtherFragment()
        val androidBaseViewFragment = AndroidWidgetFragment()
        //        DatabaseFragment databaseFragment = new DatabaseFragment();
        val customViewFragment = CustomViewFragment()
        this.supportFragmentManager.beginTransaction().replace(R.id.container, customViewFragment).commit()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.i(TAG, "onSaveInstanceState: ")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onDestroy() {
        Log.e(TAG, "onDestroy: ")
        super.onDestroy()
    }

    fun initView() {
        setSupportActionBar(mBinding.main.toolbar)
        mBinding.main.fab.setOnClickListener { onFloatingActionButtonClick() }
        toggle = ActionBarDrawerToggle(
                this, mBinding.drawerLayout, mBinding.main.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mBinding.drawerLayout.setDrawerListener(toggle)
        toggle!!.syncState()
        mBinding.main.slidingTabs.visibility = View.GONE
        mBinding.navView.setNavigationItemSelectedListener(this)
        toast = Toast.makeText(this, "toast", Toast.LENGTH_SHORT)
        mBinding.main.container.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            mBinding.root.getWindowVisibleDisplayFrame(rect)
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
    }

    private fun onFloatingActionButtonClick() {
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
//        SYSTEM_UI_FLAG_HIDE_NAVIGATIONI_FLAG_HIDE_NAVIGATIONDE_NAVIGATIONval intent = Intent(this, ThirdActivity::class.java)
//        startActivity(intentntent)

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

    override fun onBackPressed() {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        val itemSearch = menu.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(itemSearch) as SearchView

        //设置Search View一开始就是展开的
//        searchView.setIconified(false);
        //设置Search View一开始就是展开的,并且不能被隐藏
//        actionView.setIconifiedByDefault(false);
        val goButton = searchView.findViewById<View>(R.id.search_go_btn) as ImageView
        val voiceButton = searchView.findViewById<View>(R.id.search_voice_btn) as ImageView
        searchView.isSubmitButtonEnabled = true
        voiceButton.visibility = View.VISIBLE
        voiceButton.setImageResource(R.mipmap.ic_settings_voice_white_24dp)
        voiceButton.isEnabled = true
        //修改提交按钮的背景图片
//        goButton.setImageResource(R.mipmap.ic_settings_voice_white_24dp);
        goButton.setOnClickListener {
            Toast.makeText(this@MainActivity, "Test", Toast.LENGTH_SHORT).show()
            Log.i(TAG, "onClick: " + searchView.query)
        }
        searchView.setOnSearchClickListener { v: View? -> Toast.makeText(this@MainActivity, "Searching....", Toast.LENGTH_SHORT).show() }
        searchView.setOnCloseListener {
            Toast.makeText(this@MainActivity, "Closing....", Toast.LENGTH_SHORT).show()
            false
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Log.i(TAG, "onQueryTextSubmit: $query")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.i(TAG, "onQueryTextChange: $newText")
                return false
            }
        })
        searchView.queryHint = "请输入商品名或者首字母，祝您购物愉快"
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Log.i(TAG, "onOptionsItemSelected: setting");
        return when (item.itemId) {
            R.id.action_settings -> {
                Log.i(TAG, "add a new item: ")
                //              手动添加一项MenuItem.
                Log.i(TAG, "onOptionsItemSelected: action_settings")
                toast!!.show()

                TaskUtils.executeDownloadTask("http://baidu.com")

                true
            }
            R.id.action_share -> {
                share()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun share() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Share Test")
        startActivity(Intent.createChooser(sharingIntent, "share"))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        item.isCheckable = true
        val id = item.itemId
        when (id) {
            R.id.nav_camera -> switchFragment(AndroidBaseFragment())
            R.id.nav_BaseView -> switchFragment(AndroidWidgetFragment())
            R.id.nav_database -> {
                Log.e(TAG, "onNavigationItemSelected: database")
                switchFragment(DatabaseFragment())
            }
            R.id.nav_other -> switchFragment(OtherFragment())
            R.id.nav_Manager -> switchFragment(ManagerFragment())
            R.id.nav_CustomView -> switchFragment(CustomViewFragment())
            R.id.nav_RecyclerView -> startActivity(Intent(this, RecyclerViewActivity::class.java))
            R.id.nav_materialDesign -> switchFragment(MaterialDesignFragment())
            R.id.nav_share -> switchFragment(ShareFragment())
            R.id.nav_send -> switchFragment(SendFragment())
            else -> {
            }
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        mBinding.main.toolbar.title = item.title
        return true
    }

    val tabLayout: TabLayout
        get() = mBinding.main.slidingTabs

    val toolbar: Toolbar
        get() = mBinding.main.toolbar

    val floatingActionBar: FloatingActionButton
        get() = mBinding.main.fab

    fun onClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i(TAG, "onConfigurationChanged: ")
        Log.i(TAG, "onConfigurationChanged: " + if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) "Horizontal" else "Vertical")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: ")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: ")
        //        testAnr();
        mBinding.drawerLayout.performClick()
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
    }

    private fun switchFragment(fragment: Fragment?) {
        if (fragment == null) {
            return
        }
        this.supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
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
    private fun testAnr() {
        val file = File("/data/anr")
        val fileObserver: FileObserver = object : FileObserver("/data/anr") {
            override fun onEvent(event: Int, path: String?) {
                Log.e(TAG, "onEvent: +$path")
            }
        }
        fileObserver.startWatching()
    }

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}