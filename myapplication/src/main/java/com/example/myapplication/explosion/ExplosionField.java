package com.example.myapplication.explosion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtao
 * @date 2019-06-14
 **/
public class ExplosionField extends View {
    private List<ExplosionAnimator> animatorList;
    private ParticleFactory particleFactory;
    private OnClickListener onClickListener;

    public ExplosionField(Context context, ParticleFactory particleFactory) {
        super(context);
        animatorList = new ArrayList<>();
        this.particleFactory = particleFactory;
        attach2Activity();

    }

    private void attach2Activity() {
        ViewGroup viewGroup = (ViewGroup) ((Activity) getContext()).findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewGroup.addView(this, layoutParams);

    }

    public ExplosionField(Context context) {
        super(context);
    }

    public ExplosionField(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExplosionField(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExplosionField(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void addListener(View view) {
        if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                addListener(((ViewGroup) view).getChildAt(i));
            }
        } else {
            view.setClickable(true);
            view.setOnClickListener(getOnClickListener());
            addListener(view);
        }
    }

    private OnClickListener getOnClickListener() {
        if (onClickListener == null) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExplosionField.this.explode(v);
                }
            };
        }
        return onClickListener;
    }


    //爆炸
    private void explode(View v) {
        //调整位置，现获取view的区域，
        Rect rect = new Rect();
        v.getGlobalVisibleRect(rect);//获取view相对整个屏幕的对着，即绝对坐标，相对于屏幕的坐上顶点
        //标题栏高度
        int contentTop = ((ViewGroup) getParent()).getTop();

        //状态栏高度
        Rect frame = new Rect();
        ((Activity) getContext()).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        rect.offset(0, -contentTop - statusBarHeight);
        if (rect.width() ==0 || rect.height() == 0){
            return;
        }
        //开始动画，1，震动，
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f).setDuration(150);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });

    }

    private void explode(final  View view, Rect bound){
        ExplosionAnimator animator = new ExplosionAnimator();
        animatorList.add(animator);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setClickable(false);
                view.animate().setDuration(150).scaleX(1f).scaleY(1f).alpha(1f).start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
    }
}
