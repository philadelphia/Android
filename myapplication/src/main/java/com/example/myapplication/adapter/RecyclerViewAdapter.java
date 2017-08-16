package com.example.myapplication.adapter;

import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.support.annotation.ColorRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.utils.CustomItemClickListener;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/27.
 */
public class RecyclerViewAdapter  extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private  List<PackageInfo> dataList;
    private CustomItemClickListener customItemClickListener;
    private static final String TAG = "RecyclerViewAdapter";

    private int position;
    public RecyclerViewAdapter(List<PackageInfo> dataList) {

        this.dataList = dataList;
    }


    public void setOnCustomeItemClickListener(CustomItemClickListener customItemClickListener){
        this.customItemClickListener = customItemClickListener;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public  MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_packages,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Log.i(TAG, "position: " + position);
        Log.i(TAG, "holder.hashCode(): " + holder.hashCode());
        holder.tv_pkgName.setText(dataList.get(position).packageName);
        holder.tv_pkgVersionCode.setText(String.valueOf(dataList.get(position).versionCode));
        holder.tv_pkgVersionName.setText(dataList.get(position).versionName);
//
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                setPosition(holder.getPosition());
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


    public static class MyViewHolder extends  RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener{
        public TextView tv_pkgName;
        public TextView tv_pkgVersionCode;
        public TextView tv_pkgVersionName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_pkgName = (TextView) itemView.findViewById(R.id.tv_pkgName);
            tv_pkgVersionCode = (TextView) itemView.findViewById(R.id.tv_VersionCode);
            tv_pkgVersionName = (TextView) itemView.findViewById(R.id.tv_VersionName);
//            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            itemView.setBackgroundColor(Color.BLUE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


//        @Override
//        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
//            contextMenu.setHeaderTitle("操作");
//            contextMenu.add(0, 0, 0, "添加");
//            contextMenu.add(0, 1, 1, "标记为重要");
//            contextMenu.add(0, 2, 2, "删除");
//        }
    }
}
