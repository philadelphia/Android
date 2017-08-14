package com.example.myapplication.customwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class CustomCircleView extends View {
    private Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint5 = new Paint(Paint.ANTI_ALIAS_FLAG);
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
        //实心圆
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 350, 200, paint1);

        //空心圆
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.BLUE);
        canvas.drawCircle(850, 350, 300, paint2);


        //蓝色实心圆
        paint3.setColor(Color.BLUE);
        paint3.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 850, 200, paint3);

        //粗边圆
        paint4.setColor(Color.BLACK);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(40);
        canvas.drawCircle(850, 1050, 400, paint4);

    }


}
