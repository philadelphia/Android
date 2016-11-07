package com.example.uidemo;

import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnSystemUiVisibilityChangeListener {
    private static final String TAG = "MainActivity";
    private ImageView imageView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.img_beauty);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
        imageView.setOnClickListener(this);
        //隐藏statusBar、View.SYSTEM_UI_FLAG_FULLSCREEN 效果同View.INVISIBLE;
//        点击多任务键或者home键会导致所有SYSTEM_UI_FLAG的设置失效
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        //隐藏statusBar、NavigationBar
//        Log.i(TAG, "onCreate: 隐藏statusBar,NavigationBar");
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        decorView.setSystemUiVisibility(View.INVISIBLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        //显示SystemBar,此时statusBar是透明的。与布局的背景一样。
//        Log.i(TAG, "onCreate: 显示SystemBar");
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );

        //隐藏statusBar
//        Log.i(TAG, "onCreate: 隐藏statusBar");
//        decorView.setSystemUiVisibility(View.INVISIBLE);

        //显示statusBar
//        decorView.setSystemUiVisibility(View.VISIBLE);

        //隐藏StatusBar,但是StatusBar不会被ContentView占用，会显示一片空白区域。
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        //全屏显示，显示StatusBar,但是StatusBar会浮动在ContentView之上
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //隐藏navigationBar，但是ContentView会占用Navigation
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );


//      隐藏navigationBar,
//        Log.i(TAG, "onCreate: 隐藏navigationBar");
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);


//      沉浸式
//      immersive类的标签只有在与SYSTEM_UI_FLAG_HIDE_NAVIGATION,SYSTEM_UI_FLAG_FULLSCREEN中一个或两个一起使用的时候才会生效。
//      你可以只使用其中的一个，但是一般情况下你需要同时隐藏状态栏和导航栏以达到沉浸的效果
        Log.i(TAG, "onCreate: 沉浸式");
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

//        沉浸式Sticky
//         如果需要System Bar会在一段时间后自动隐藏的话，你应该使用SYSTEM_UI_FLAG_IMMERSIVE_STICKY标签。
//         请注意，带有'sticky'的标签不会触发任何的监听器SystemUiVisibilityChangeListener
//         因为在这个模式下展示的系统栏是处于暂时(transient)的状态。
//        Log.i(TAG, "onCreate: 沉浸式Sticky");

//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onSystemUiVisibilityChange(int visibility) {
        switch (visibility) {
            case 0:
                break;

        }
    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "onClick: ");
        Toast.makeText(this, "Content View was clicked!", Toast.LENGTH_SHORT).show();
    }


}
