package com.example.myapplication.explosion;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.animation.Animation;

/**
 * @author zhangtao
 * @date 2019-06-14
 **/
class ExplosionAnimator extends Animator {
    @Override
    public long getStartDelay() {
        return 0;
    }

    @Override
    public void setStartDelay(long startDelay) {

    }

    @Override
    public Animator setDuration(long duration) {
        return null;
    }

    @Override
    public long getDuration() {
        return 0;
    }

    @Override
    public void setInterpolator(TimeInterpolator value) {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
