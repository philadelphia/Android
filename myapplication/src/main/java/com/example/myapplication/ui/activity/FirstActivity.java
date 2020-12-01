package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.TestActivity;
import com.example.myapplication.databinding.ActivityFirstBinding;


public class FirstActivity extends AppCompatActivity {
    private static final String TAG = "FirstActivity";
    private ActivityFirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnSecondActivity.setOnClickListener((View view) -> {
            onViewClicked();
        });
    }

    public void onViewClicked() {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
    }
}
