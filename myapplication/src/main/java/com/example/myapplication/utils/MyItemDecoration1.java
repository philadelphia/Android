package com.example.myapplication.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Tao.ZT.Zhang on 2017/8/16.
 */

public class MyItemDecoration1 extends RecyclerView.ItemDecoration {


    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;



    private int mOrientation;


    public MyItemDecoration1(int mOrientation) {
        this.mOrientation = mOrientation;
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
            if (parent.getChildAdapterPosition(view) != 0){
                outRect.top = 50;
            }
        } else {
//            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }

}
