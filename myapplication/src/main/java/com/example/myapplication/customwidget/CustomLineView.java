package com.example.myapplication.customwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.Nullable;

import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class CustomLineView extends View {
    private Paint paint1 = new Paint();
    private Paint paint2 = new Paint();
    private Paint paint3 = new Paint();
    public CustomLineView(Context context) {
        super(context);
    }

    public CustomLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint1.setStrokeWidth(20);
        paint1.setColor(Color.BLUE);
        canvas.drawLine(20,20,1020,140, paint1);
        float[] points = {50f,30f, 1000f, 1050f, 1080f, 500f};
        float[] points2 = {100f,1050f, 1080f, 500f};
        paint2.setStrokeWidth(10);
        paint2.setStyle(Paint.Style.STROKE);
        canvas.drawLines(points, paint2);

        canvas.save();

        paint3.setColor(Color.BLACK);
        canvas.drawLines(points2, paint3);
    }
}
