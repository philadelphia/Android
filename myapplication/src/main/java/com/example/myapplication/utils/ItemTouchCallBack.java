package com.example.myapplication.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;

/**
 * Created by Tao.ZT.Zhang on 2017/8/21.
 */

@SuppressWarnings("ALL")
public class ItemTouchCallBack<T> extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapterCallBack adapterCallBack;
    private static final String TAG = "MyCallBack";
    private final Context context;

    public ItemTouchCallBack(Context context, ItemTouchHelperAdapterCallBack adapter) {
        this.context = context;
        this.adapterCallBack = adapter;

    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "getMovementFlags: ");
        return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.i(TAG, "onMove: ");
        adapterCallBack.onMove(recyclerView, viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        Log.i(TAG, "onMoved: ");
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped: ");
        adapterCallBack.onSwipe(viewHolder.getAdapterPosition());
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        Log.d(TAG, "onChildDraw: dY" + dY);
        View itemView = viewHolder.itemView;
        int itemWidth = itemView.getWidth();
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            Log.i(TAG, "onChildDraw: 向右滑动");
            float alpha = 1f - Math.abs(dX) / itemWidth;
            itemView.setAlpha(alpha);
            itemView.setScaleX(alpha);
            itemView.setScaleY(alpha);
            itemView.setTranslationX(itemWidth);
            if (alpha == 0) {
                itemView.setAlpha(1);
                itemView.setScaleX(1);
                itemView.setScaleY(1);
                itemView.setTranslationX(0);
            }

            if (dX <= 0.5f * itemWidth) {
                itemView.setAlpha(0.5f);
                itemView.setTranslationX(0.5f * itemWidth);

            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        } else {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.i(TAG, "onSelectedChanged: actionState==" + actionState);
        super.onSelectedChanged(viewHolder, actionState);
        if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG)
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "clearView: ");
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_light_background));
    }
}
