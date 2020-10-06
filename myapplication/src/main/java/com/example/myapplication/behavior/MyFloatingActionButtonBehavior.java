package com.example.myapplication.behavior;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

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


        return super.onDependentViewChanged(parent, child, dependency);

    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {

        Log.i(TAG, "onNestedPreScroll: ");
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

    }



    @Override
    public void onNestedScrollAccepted(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.i(TAG, "onNestedScrollAccepted: ");
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }
    
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.e(TAG, "onStartNestedScroll: ");
        if (child.getVisibility() == View.VISIBLE && height == 0) {
            height = coordinatorLayout.getHeight() - child.getTop();
        }
        Log.i(TAG, "height== : " + height);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        Log.i(TAG, "onNestedPreFling: ");
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        Log.i(TAG, "onNestedFling: ");
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(TAG, "onNestedScroll: ");
//        Log.e(TAG, "dyConsumed: === " + dyConsumed);
//        Log.e(TAG, "dyUnconsumed: ===  " + dyUnconsumed);
        //上滑，隐藏fab
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            hideView(child);

        } else if (dyConsumed < 0 && child.getVisibility() == View.GONE) {
           showView(child);
        }


        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        Log.e(TAG, "onStopNestedScroll: ");
        super.onStopNestedScroll(coordinatorLayout, child, target);
    }

    public void showView(final View view){
        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", 0).setDuration(300);
        translationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        translationY.start();

    }
    public void hideView(final View view){
        ObjectAnimator translation = ObjectAnimator.ofFloat(view, "translationY", height).setDuration(200);
        translation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "onAnimationStart: " + System.currentTimeMillis());
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                Log.i(TAG, "onAnimationEnd: " + System.currentTimeMillis());
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        translation.start();

    }

}
