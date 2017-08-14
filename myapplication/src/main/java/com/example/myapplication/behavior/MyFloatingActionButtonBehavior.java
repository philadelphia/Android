package com.example.myapplication.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.R.attr.scrollY;

/**
 * Created by Tao.ZT.Zhang on 2017/8/10.
 */

public class MyFloatingActionButtonBehavior extends CoordinatorLayout.Behavior {
    private static final String TAG = "MyFloatingActionButtonB";
    private float height;
    private boolean isFirst = false;

    public MyFloatingActionButtonBehavior() {

    }

    public MyFloatingActionButtonBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        Log.i(TAG, "layoutDependsOn: ");
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        Log.i(TAG, "onDependentViewChanged: ");
        int scrollY = dependency.getTop();


        return super.onDependentViewChanged(parent, child, dependency);

    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.i(TAG, "onNestedPreScroll: ");
//        int scrollY = ((RecyclerView) target).getx
//        Log.i(TAG, "scrollY: " + scrollY);

//        ObjectAnimator.ofFloat(child, "translationY", dy).setDuration(100).start();//默认时间内让mView在Y轴上平移100个像素
//       ObjectAnimator.ofFloat().addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//           @Override
//           public void onAnimationUpdate(ValueAnimator animation) {
//
//           }
//       });
//        ViewCompat.offsetTopAndBottom(child,-scrollY);
//        if(height1>=0){
//            ViewCompat.offsetTopAndBottom(child,50);
//        }else {
//            ViewCompat.offsetTopAndBottom(child,-50);
//        }
//        if (Math.abs(height1) <=height){
//
//        }
//        ObjectAnimator.ofFloat().addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//
//        ObjectAnimator.ofFloat().addPauseListener(new Animator.AnimatorPauseListener() {
//            @Override
//            public void onAnimationPause(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationResume(Animator animation) {
//
//            }
//        });



        ValueAnimator.ofInt().addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ValueAnimator.ofInt().addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
        ValueAnimator.ofInt().addPauseListener(new Animator.AnimatorPauseListener() {
            @Override
            public void onAnimationPause(Animator animation) {

            }

            @Override
            public void onAnimationResume(Animator animation) {

            }
        });
    }




    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.e(TAG, "onStartNestedScroll: ");
        if (child.getVisibility() == View.VISIBLE && height == 0) {
            height = child.getBottom();
        }
        Log.i(TAG, "height== : " + height);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        Log.e(TAG, "onNestedPreFling: ");
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.e(TAG, "onNestedScroll: ");
        Log.e(TAG, "dyConsumed: === " + dyConsumed);
        Log.e(TAG, "dyUnconsumed: ===  " + dyUnconsumed);

        if (dyConsumed > 0) {
            float min = Math.min(dyConsumed, height);
            ViewCompat.offsetTopAndBottom(child, (int) min);
            Log.i(TAG, "onNestedScroll: 上滑动");
            Log.i(TAG, "child.getBottom(): " + child.getBottom());
            Log.i(TAG, "child.getVisibility: " + child.getVisibility());
            Log.i(TAG, "child.isAccessibilityFocused: " + child.isAccessibilityFocused());
            Log.i(TAG, "child.isActivated: " + child.isActivated());
            Log.i(TAG, "child.isShown: " + child.isShown());

        } else {
            float min = Math.min(Math.abs(dyConsumed), height);
            ViewCompat.offsetTopAndBottom(child, -(int) min);
            Log.i(TAG, "onNestedScroll: 下滑动");
            Log.i(TAG, "child.getVisibility: " + child.getVisibility());
            Log.i(TAG, "child.isAccessibilityFocused: " + child.isAccessibilityFocused());
            Log.i(TAG, "child.isActivated: " + child.isActivated());
            Log.i(TAG, "child.isShown: " + child.isShown());

            Log.i(TAG, "child.getBottom(): " + child.getBottom());
        }


        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        Log.e(TAG, "onStopNestedScroll: ");
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }
}
