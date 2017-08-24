package com.example.myapplication.adapter;

import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.ItemTouchHelperAdapterCallBack;
import com.example.myapplication.utils.OnStartDragListener;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/27.
 */
@SuppressWarnings("ALL")
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements ItemTouchHelperAdapterCallBack {
    private final List<PackageInfo> dataList;
    private final OnStartDragListener onStartDragListener;
    private static final String TAG = "RecyclerViewAdapter";

    public RecyclerViewAdapter(List<PackageInfo> dataList, OnStartDragListener onStartDragListener) {
        this.dataList = dataList;
        this.onStartDragListener = onStartDragListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_packages1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.i(TAG, "position: " + position);
        Log.i(TAG, "holder.hashCode(): " + holder.hashCode());
        holder.tv_pkgName.setText(dataList.get(position).packageName);
        holder.tv_pkgVersionCode.setText(String.valueOf(dataList.get(position).versionCode));
        holder.tv_pkgVersionName.setText(dataList.get(position).versionName);
//        PackageInfo packageInfo = dataList.get(position);
//        if(packageInfo.applicationInfo.icon != 0){
//            holder.img_Icon.setImageResource(packageInfo.applicationInfo.icon);
//

        holder.img_Icon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    onStartDragListener.startDrag(holder);
                }

                return false;
            }
        });

    }


    @Override
    public void onViewRecycled(MyViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, int source, int dest) {
        Collections.swap(dataList, source, dest);
        return true;
    }

    @Override
    public void onMoved(RecyclerView recyclerView, int source, int dest) {
        notifyItemMoved(source, dest);
    }

    @Override
    public void onSwipe(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return getViewHolder();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener {
        public final TextView tv_pkgName;
        public final TextView tv_pkgVersionCode;
        public final TextView tv_pkgVersionName;
        private final ImageView img_Icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_pkgName = (TextView) itemView.findViewById(R.id.tv_pkgName);
            tv_pkgVersionCode = (TextView) itemView.findViewById(R.id.tv_VersionCode);
            tv_pkgVersionName = (TextView) itemView.findViewById(R.id.tv_VersionName);
            img_Icon = ((ImageView) itemView.findViewById(R.id.icon));
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            itemView.setBackgroundColor(Color.BLUE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

    }

}
