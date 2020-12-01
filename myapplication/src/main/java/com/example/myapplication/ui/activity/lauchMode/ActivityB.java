package com.example.myapplication.ui.activity.lauchMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityLayoutBBinding;


/**
 * @author zhangtao
 * @date 2019-11-18
 **/
public class ActivityB extends Activity {
    private static final String TAG = "ActivityB";
    private ActivityLayoutBBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLayoutBBinding.inflate(getLayoutInflater());
        Log.i(TAG, "onCreate: current TaskID" + getTaskId());
        Log.i(TAG, "onCreate: this hashCode == " + hashCode());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
        Log.i(TAG, "onCreate: this hashCode == " + hashCode());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityB.this, ActivityA.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}
