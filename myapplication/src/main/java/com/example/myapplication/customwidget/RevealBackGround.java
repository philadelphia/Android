package com.example.myapplication.customwidget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.example.myapplication.R;


/**
 * Created by Tao.ZT.Zhang on 2017/8/24.
 */

public class RevealBackGround extends View {
    private Paint paint;
    private int radius;
    private int startLocationX;
    private int startLocationY;
    private Interpolator INTERPOLATOR = new LinearInterpolator();
    private OnStateChangedListener onStateChangedListener;
    public final static int STATE_NOT_STARTED = 1;
    public final static int STATE_FILL_STARTED = 2;
    public final static int STATE_FINISHED = 3;
    private Context context;

    public RevealBackGround(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
//        paint.setColor(Color.GREEN);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.onStateChangedListener = onStateChangedListener;
    }

    //开启动画
    public void startBackgroundAnimation(int[] location) {
        onStateChangedListener.onStateChange(STATE_NOT_STARTED);
        startLocationX = location[0];
        startLocationY = location[1];
        //这里使用属性动画来达到属性变化的效果，只要让半径越变越大就行了，
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "radius", 0, getWidth() + getHeight()).setDuration(400);
        animator.setInterpolator(INTERPOLATOR);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //动画执行完毕
                onStateChangedListener.onStateChange(STATE_FINISHED);

            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(startLocationX, startLocationY, radius, paint);
    }

    public  interface OnStateChangedListener{
        void onStateChange(int state);

    }
}
