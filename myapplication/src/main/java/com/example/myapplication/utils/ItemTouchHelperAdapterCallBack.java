package com.example.myapplication.utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Tao.ZT.Zhang on 2016/7/28.
 */
public interface ItemTouchHelperAdapterCallBack {
    boolean  onMove(RecyclerView recyclerView, int source, int dest);

    void onMoved(RecyclerView recyclerView, int source, int dest);

    void onSwipe(int position);

    RecyclerView.ViewHolder getViewHolder();

}
