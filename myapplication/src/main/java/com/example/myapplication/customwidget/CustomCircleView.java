package com.example.myapplication.customwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.myapplication.R;

/**
 * TODO: document your custom view class.
 */
public class CustomCircleView extends View {
    public CustomCircleView(Context context) {
        super(context);
    }

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


}
