package com.example.myapplication.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

/**
 * Created by Tao.ZT.Zhang on 2017/8/16.
 */

public class MyItemDecoration2 extends RecyclerView.ItemDecoration {


    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;


    private int mOrientation;

    private Paint paint;
    private int height;


    public MyItemDecoration2(int mOrientation) {
        this.mOrientation = mOrientation;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }


    //设置条目周边的偏移量
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {

            if (parent.getChildAdapterPosition(view) != 0) {
//                outRect.top = 50;
                outRect.set(50,50,0,0);
                height = 2;
            }

        } else {
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(child);

            if (index == 0)
                continue;

            float top = child.getTop() - 30;
            float left = parent.getPaddingLeft() ;
            float right = parent.getWidth() - parent.getPaddingRight();
            float bottom = child.getTop();
            c.drawRect(left, top, right, bottom, paint);
        }

    }
}
