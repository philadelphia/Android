package com.example.myapplication.customwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.myapplication.R;

/**
 * TODO: document your custom view class.
 */
public class CustomOvalView extends View {

    public CustomOvalView(Context context) {
        super(context);
    }

    public CustomOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
