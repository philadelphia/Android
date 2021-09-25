package com.example.myapplication.observer;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @Author zhang tao
 * @Date 9/21/21 10:20 PM
 * @Desc
 */
public class CustomChronometer extends Chronometer implements LifecycleObserver {
    protected long elapsedTime;
    private static final String TAG = "CustomChronometer";

    public CustomChronometer(Context context) {
        super(context);
    }

    public CustomChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomChronometer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void onCreate() {
        Log.e(TAG, "onCreate: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart() {
        Log.e(TAG, "onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void startMeter() {
        Log.e(TAG, "startMeter: ");
        setBase(SystemClock.elapsedRealtime() - elapsedTime);
        start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void stopMeter() {
        Log.e(TAG, "stopMeter: ");
        elapsedTime = SystemClock.elapsedRealtime() - getBase();
        stop();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onStop() {
        Log.e(TAG, "onStop: ");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        Log.e(TAG, "onDestroy: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void onAny() {
        Log.e(TAG, "onAny: ");

    }
}
