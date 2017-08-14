package com.example.myapplication.customwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomRoundRectView extends View {
    private Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CustomRoundRectView(Context context) {
        super(context);

    }

    public CustomRoundRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
   }

    public CustomRoundRectView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint1.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(100, 100, 500, 500, 50, 50, paint1);

        paint2.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(700, 100, 1100, 500, 50, 50, paint2);
    }
}
