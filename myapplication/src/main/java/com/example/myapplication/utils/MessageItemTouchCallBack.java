package com.example.myapplication.utils;

import android.graphics.Canvas;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.myapplication.adapter.RecyclerViewItemTouchAdapter;

/**
 * Created by Tao.ZT.Zhang on 2017/8/21.
 */

@SuppressWarnings("ALL")
public class MessageItemTouchCallBack<T> extends ItemTouchHelper.Callback {
    private static final String TAG = "MessageItemTouchCallBack";
    private final ItemTouchHelperAdapterCallBack adapterCallBack;

    public MessageItemTouchCallBack(ItemTouchHelperAdapterCallBack adapter) {
        this.adapterCallBack = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "getMovementFlags: ");
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.i(TAG, "onMove: ");
        return false;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped: ");
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.i(TAG, "onChildDraw: dX ==" + dX);
        RecyclerViewItemTouchAdapter.SwipeViewHolder viewHolder1 = (RecyclerViewItemTouchAdapter.SwipeViewHolder) viewHolder;
        int width = ((RecyclerViewItemTouchAdapter.SwipeViewHolder) viewHolder).itemBackGround.getWidth();
        View viewContent = ((RecyclerViewItemTouchAdapter.SwipeViewHolder) viewHolder).viewContent;
            if (dX  < -width) {
                dX = -width;
                Log.i(TAG, "onChildDraw: ***");
                viewContent.setTranslationX(dX);
            } else {
                Log.i(TAG, "onChildDraw: -------------");
                viewContent.setTranslationX(dX);
            }

            if (viewContent.getTranslationX() == width){
                viewContent.setTranslationX(0);
            }
            if (dY != 0 && dX == 0) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

    }


    public  void onItemClickListener(){

    }

}
