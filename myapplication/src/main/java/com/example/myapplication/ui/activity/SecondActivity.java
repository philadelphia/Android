package com.example.myapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Window;

import com.example.myapplication.R;

public class SecondActivity extends AppCompatActivity {
    private  final  String TAG = SecondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        String flag = getIntent().getStringExtra("Flag");
        if (flag.equalsIgnoreCase("explode")){
            Log.e(TAG, "flag: " + flag);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }else if (flag.equalsIgnoreCase("slide")){
            Log.e(TAG, "flag: " + flag);
            getWindow().setEnterTransition(new Slide());
            getWindow().setExitTransition(new Slide());
        }else if (flag.equalsIgnoreCase("fade")){
            Log.e(TAG, "flag: " + flag);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
        }


        setContentView(R.layout.activity_second);
        Log.e(TAG, "onCreate");
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
