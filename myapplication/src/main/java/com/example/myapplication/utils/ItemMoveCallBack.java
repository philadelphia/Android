package com.example.myapplication.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;

/**
 * Created by Tao.ZT.Zhang on 2017/8/21.
 */

@SuppressWarnings("ALL")
public class ItemMoveCallBack<T> extends ItemTouchHelper.Callback {
    private final ItemTouchHelperAdapterCallBack adapterCallBack;
    private static final String TAG = "ItemMoveCallBack";
    private final Context context;

    public ItemMoveCallBack(Context context, ItemTouchHelperAdapterCallBack adapter) {
        this.context = context;
        this.adapterCallBack = adapter;

    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "getMovementFlags: ");
        return makeMovementFlags(ItemTouchHelper.DOWN | ItemTouchHelper.UP, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.i(TAG, "onMove: ");
        adapterCallBack.onMove(recyclerView, viewHolder.getAdapterPosition(), target.getAdapterPosition());
      return  true;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped: ");
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        Log.i(TAG, "onSelectedChanged: actionState==" + actionState);
        super.onSelectedChanged(viewHolder, actionState);
        if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG)
            viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "clearView: ");
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_light_background));
    }


}
