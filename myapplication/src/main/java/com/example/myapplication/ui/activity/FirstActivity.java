package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
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
        initGestureListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
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

    private void initGestureListener() {
        GestureDetector gestureDetector = new GestureDetector(this, new OnGestureListener() {


            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        binding.root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: finger count == " + event.getPointerCount());

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouchEvent:ACTION_DOWN index == "+ event.getActionIndex());
                return true;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent:ACTION_UP  index == "+ event.getActionIndex());
                return true;

            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(TAG, "onTouchEvent:ACTION_POINTER_DOWN index ==   " + event.getActionIndex());
                return true;

            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "onTouchEvent:ACTION_POINTER_UP index ==   " + event.getActionIndex());
                return true;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouchEvent:ACTION_MOVE index == "+ event.getActionIndex());
                return true;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "onTouchEvent:ACTION_CANCEL ");
                return true;
        }
        return super.onTouchEvent(event);
    }
}