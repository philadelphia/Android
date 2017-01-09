package com.example.uidemo;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;


import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener, View.OnSystemUiVisibilityChangeListener {
    private static final String TAG = "MainActivity";
    private ImageView imageView;
    private boolean flag = false;

    private View decorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        Log.i(TAG, "linearLayout's parent: " + linearLayout.getParent());
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View  buttonLayout = layoutInflater.inflate(R.layout.layout_button,linearLayout);
//        View  buttonLayout = layoutInflater.inflate(R.layout.layout_button,null,true);

//        inflate(int id, ViewGroup root, boolean attachToRoot)
//        第二个参数和第三个参数有四种组合情况
//        if(root == null && attachToRoot){
//            返回id里的view本身
//        }else if( root == null && !attachToRoot){
//            throw new InflateException("<merge /> can be used only with a valid "
//                    + "ViewGroup root and attachToRoot=true");
//        }
//        if(root != null && attachToRoot){
//            返回root本身，此时root已经将id里的控件添加了。
//        此时如果在使用root.addview(view) 就会报错。view此时已经有parent、因为view本身就是root自己。root自己addview自身会报错
//        }else if (){
//            返回返回id里的view本身，此时id里的控件已经设置了布局参数
//        }




        Log.i(TAG, "onCreate: " + buttonLayout.getClass().getSimpleName());
        Log.i(TAG, "buttonLayout's parent: " + buttonLayout.getParent().getClass().getSimpleName());
        Log.i(TAG, "linearLayout's child: " + linearLayout.getChildAt(0).getClass().getSimpleName());

    }


    public void testUI() {
        //        imageView = (ImageView) findViewById(R.id.img_beauty);
//        decorView = getWindow().getDecorView();
////        decorView.setSystemUiVisibility(0);
//        imageView.setOnClickListener(this);
//        //隐藏statusBar、View.SYSTEM_UI_FLAG_FULLSCREEN 效果同View.INVISIBLE;
////        点击多任务键或者home键会导致所有SYSTEM_UI_FLAG的设置失效
////        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
//
//        //隐藏statusBar、NavigationBar
////        Log.i(TAG, "onCreate: 隐藏statusBar,NavigationBar");
////        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
////        decorView.setSystemUiVisibility(View.INVISIBLE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//
//
//        //显示SystemBar,此时statusBar是透明的。与布局的背景一样。
////        Log.i(TAG, "onCreate: 显示SystemBar");
////        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );
//
//        //隐藏statusBar
////        Log.i(TAG, "onCreate: 隐藏statusBar");
////        decorView.setSystemUiVisibility(View.INVISIBLE);
//
//        //显示statusBar
////        decorView.setSystemUiVisibility(View.VISIBLE);
//
//        //隐藏StatusBar,但是StatusBar不会被ContentView占用，会显示一片空白区域。
////        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//
//        //全屏显示，显示StatusBar,但是StatusBar会浮动在ContentView之上
////        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//
//        //隐藏navigationBar，但是ContentView会占用Navigation
////        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
//
//
////      隐藏navigationBar,
////        Log.i(TAG, "onCreate: 隐藏navigationBar");
////        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//
//
////      沉浸式
////      immersive类的标签只有在与SYSTEM_UI_FLAG_HIDE_NAVIGATION,SYSTEM_UI_FLAG_FULLSCREEN中一个或两个一起使用的时候才会生效。
////      你可以只使用其中的一个，但是一般情况下你需要同时隐藏状态栏和导航栏以达到沉浸的效果
////        Log.i(TAG, "onCreate: 沉浸式");
//        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

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
        flag = !flag;
        Log.i(TAG, "onClick: flag = " + flag);
        showUI(flag);
    }

    public void showUI(boolean flag) {
        if (flag) {
            decorView.setSystemUiVisibility(0);

        } else {
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//            decorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_FULLSCREEN |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        }


    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
