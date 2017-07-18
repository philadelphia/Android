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
public class CustomColorView extends View {
    private Paint paint;
    public CustomColorView(Context context) {
        super(context);
        paint = new Paint();
    }

    public CustomColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
   }

    public CustomColorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(100, 100, 500, 500, paint);
    }
}
