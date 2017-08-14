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
public class CustomArcView extends View {
    public CustomArcView(Context context) {
        super(context);
    }

    public CustomArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
