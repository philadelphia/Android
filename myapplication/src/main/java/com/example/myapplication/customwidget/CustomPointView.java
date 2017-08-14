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
public class CustomPointView extends View {
    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();
    private Paint paint3 = new Paint();

    public CustomPointView(Context context) {
        super(context);
    }

    public CustomPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint1.setColor(Color.GREEN);
        paint1.setStrokeWidth(50);
        paint1.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(200, 200, paint1);
        paint2.setStrokeWidth(30);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(100, 100, paint2);
        paint3.setStrokeWidth(30);
        paint3.setColor(Color.BLUE);
        paint3.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(150, 150, paint3);


    }
}
