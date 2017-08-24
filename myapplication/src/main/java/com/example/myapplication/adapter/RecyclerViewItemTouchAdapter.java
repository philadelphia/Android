package com.example.myapplication.adapter;

import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.utils.CustomItemClickListener;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/27.
 */
public class RecyclerViewItemTouchAdapter extends RecyclerView.Adapter<RecyclerViewItemTouchAdapter.SwipeViewHolder>{
    private  List<PackageInfo> dataList;
    private CustomItemClickListener customItemClickListener;
    private static final String TAG = "RecyclerViewAdapter";

    private int position;
    public RecyclerViewItemTouchAdapter(List<PackageInfo> dataList) {

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
    public  SwipeViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_packages1,parent,false);
//        final MyViewHolder viewHolder = new MyViewHolder(view);

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layotu_swipe_item,parent,false);
        SwipeViewHolder viewHolder = new SwipeViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final SwipeViewHolder holder, final int position) {
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

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.remove(holder.getAdapterPosition());
            }
        });

        holder.btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "第" + (holder.getAdapterPosition() + 1) + "个元素正在被刷新!",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onViewRecycled(SwipeViewHolder holder) {
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

    }

    public static class SwipeViewHolder extends  MyViewHolder{
        private Button btnDelete;
        private Button btnRefresh;
        private View itemContent;
        private SwipeViewHolder(View itemView){
            super(itemView);
            this.btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
            this.btnRefresh = (Button) itemView.findViewById(R.id.btn_refresh);
        }

    }
}
