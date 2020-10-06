package com.example.myapplication.customwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.myapplication.R;

/**
 * TODO: document your custom view class.
 */
public class SlideView extends View {
    private String[] mLetters;
    private Paint paint;
    //选择哪一个控件
    private int choose;
    //设备相关属性
    private float mDensity;
    private RectF rectf = new RectF();
    private boolean isBeingDraggered = false;
    private boolean mStartEndAnim = false;
    private int mTouchSlop;
    private float mHalfWidth, mHalfHeight;
    private float mLetterHeight;
    private float mAnimStep;
    private float mY;
    private float mInitDownY;

    public SlideView(Context context) {
        this(context,null);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
        mLetters = getResources().getStringArray(R.array.letters);
        paint.setTextAlign(Paint.Align.CENTER);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mDensity = context.getResources().getDisplayMetrics().density;
        setPadding(0, dip2Px(20), 0, dip2Px(20));
    }

    private int dip2Px(int dipPx) {
        return ((int) (dipPx * mDensity + 0.5f));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHalfHeight = h - getPaddingBottom() - getPaddingTop();
        mHalfWidth = w - dip2Px(16);
        mLetterHeight = mHalfHeight / mLetters.length;
        int textSize = ((int) (mLetterHeight * 0.7));
        paint.setTextSize(textSize);
        rectf.set(w - dip2Px(32), 0, w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isBeingDraggered = false;
                float initDownY = event.getY();
                if (!rectf.contains(event.getX(), event.getY())){
                    return  false;
                }
                mInitDownY = initDownY;

                break;
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                float diff = Math.abs(y - mInitDownY);
                if (diff > mTouchSlop && !isBeingDraggered){
                    isBeingDraggered = true;
                }
                if (isBeingDraggered){
                    mY = y;
                    float moveY = y - getPaddingTop();
                    int charcter = ((int) (moveY - mHalfHeight * mLetters.length));
                    if (charcter != choose){
                        if (charcter > 0 && charcter < mLetters.length){
                            choose = charcter;
                        }
                    }
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mStartEndAnim = false;
                isBeingDraggered = false;
                choose = -1;
                invalidate();
                return false;
            }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mLetters.length; i++) {
            float letterPostY = mLetterHeight * (i + 1) + getPaddingTop();
            float diff;
            float diffX;
            float diffY;
            if (choose == i && i != 0 && i != mLetters.length - 1) {
                diff = 2.2f;
                diffX = 0f;
                diffY = 0f;
            } else {
                float maxPost = Math.abs(mY - letterPostY) / mHalfHeight * 7;
                diff = Math.max(1f, 2.2f - maxPost);
                //如果到了点击字幕的范围
                if (mStartEndAnim && diff != 1f) {
                    diff -= mAnimStep;
                    if (diff < 1) {
                        diff = 1;
                    }
                } else if (!isBeingDraggered) {
                    //超过了范围
                    diff = 1f;
                }

                diffY = maxPost * 50f * (letterPostY > mY ? -1 : 1);
                diffX = maxPost * 100;
            }
            canvas.save();
            canvas.scale(diff, diff, mHalfWidth*1.20f + diffX, letterPostY + diffY );
            if (diff == 1f){
                paint.setAlpha(255);
                paint.setTypeface(Typeface.DEFAULT);
            }else {
                int alpha = (int) (255 * 1 - Math.min(0.9, diff - 1));
                if (choose == i){
                    alpha = 255;
                }
                paint.setAlpha(alpha);
                paint.setTypeface(Typeface.DEFAULT_BOLD);

            }
            canvas.drawText(mLetters[i],mHalfWidth, letterPostY, paint);
            canvas.restore();
        }
    }
}
