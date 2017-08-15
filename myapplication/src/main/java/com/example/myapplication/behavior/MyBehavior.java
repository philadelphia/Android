package com.example.myapplication.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by Tao.ZT.Zhang on 2017/8/14.
 */

public class MyBehavior extends CoordinatorLayout.Behavior<View> {
//    private static final Interpolator INTERPOLATOR = new BounceInterpolator();
    private static final Interpolator INTERPOLATOR = new AnticipateOvershootInterpolator();
    private float viewY;//控件距离coordinatorLayout底部距离
    private boolean isAnimate;//动画是否在进行
    private static final String TAG = "MyBehavior";

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    //在嵌套滑动开始前回调
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        //coordinatorLayout的高度
        Log.i(TAG, "onStartNestedScroll:---coordinatorLayout.getHeight() " + coordinatorLayout.getHeight());
//        child--FloatingActionButton的高度
        Log.i(TAG, "onStartNestedScroll----child.getHeight(): " + child.getHeight());
        //  child--FloatingActionButton顶部距离父控件顶部的高度,其值不会随着child的位移而改变
        Log.i(TAG, "onStartNestedScroll----child.getTop() == : " + child.getTop());
        //  child--FloatingActionButton底部距离父控件顶部的高度，其值等于child.getTop() + child.getHeight();
        Log.i(TAG, "onStartNestedScroll----child.getBottom() == : " + child.getBottom());

        //  child--FloatingActionButton顶部距离父控件顶部的高度,其值会随着child的滑动而变化，等于 child.getTop() + child.getTranslationY();
        Log.i(TAG, "onStartNestedScroll----child.getY()===: " + child.getY());

        if (child.getVisibility() == View.VISIBLE && viewY == 0) {
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewY = coordinatorLayout.getHeight() - child.getY();
        }

        Log.i(TAG, "viewY===: " + viewY);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//判断是否竖直滚动
    }

    //在嵌套滑动进行时，对象消费滚动距离前回调
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
//        Log.e(TAG, "onNestedPreScroll ---- child.getTranslationY()===: " + child.getTranslationY() );
//        Log.e(TAG, "onNestedPreScroll ---- child.getTop()===: " + child.getTop());
//        Log.e(TAG, "onNestedPreScroll ---- child.getY()===: " + child.getY());
        //dy大于0是向上滚动 小于0是向下滚动
//        if (dy >=0&&!isAnimate&&child.getVisibility()==View.VISIBLE) {
//            hide(child);
//        } else if (dy <0&&!isAnimate&&child.getVisibility()==View.GONE) {
//            show(child);
//        }
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(TAG, "onNestedScroll:dyConsumed ==" + dyConsumed);
        Log.i(TAG, "onNestedScroll:dyUnconsumed == " + dyUnconsumed);
        //dy大于0是向上滚动 小于0是向下滚动
        //如果滑动距离小于3，则不做处理
        if (Math.abs(dyConsumed) < 3) return;
        if (dyConsumed > 0 && !isAnimate && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (dyConsumed < 0 && !isAnimate && child.getVisibility() == View.GONE) {
            show(child);
        }

        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    //隐藏时的动画
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(300);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }

    //显示时的动画
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(300);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                isAnimate = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimate = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }
}
