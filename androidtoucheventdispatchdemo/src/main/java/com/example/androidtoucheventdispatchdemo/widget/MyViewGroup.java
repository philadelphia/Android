package com.example.androidtoucheventdispatchdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.androidtoucheventdispatchdemo.LogUtils;

/**
 * Author:  ZhangTao
 * Date: 2018/1/12.
 */

public class MyViewGroup  extends LinearLayout{
    private static final String TAG = "MyViewGroup";
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed,l,t,t,b);
        
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.i(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
//        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.i(TAG, "onInterceptTouchEvent: ");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i(TAG, "onTouchEvent: " + MotionEvent.actionToString(event.getAction()));
        return super.onTouchEvent(event);
    }
}
