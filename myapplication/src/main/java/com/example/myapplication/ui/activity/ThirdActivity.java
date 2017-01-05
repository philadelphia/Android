package com.example.myapplication.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;

public class ThirdActivity extends Activity {
    private static final String TAG = "ThirdActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }
}
