package com.example.myapplication.adapter;

import android.content.pm.PackageInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public RecyclerViewAdapter(List<PackageInfo> dataList) {

        this.dataList = dataList;
    }


    public void setOnCustomeItemClickListener(CustomItemClickListener customItemClickListener){
        this.customItemClickListener = customItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_packages,parent,false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customItemClickListener.onItemClick(view,viewHolder.getAdapterPosition() );
            }
        });
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_pkgName.setText(dataList.get(position).packageName);
        holder.tv_pkgVersionCode.setText(String.valueOf(dataList.get(position).versionCode));
        holder.tv_pkgVersionName.setText(dataList.get(position).versionName);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    public class MyViewHolder extends  RecyclerView.ViewHolder{
        public TextView tv_pkgName;
        public TextView tv_pkgVersionCode;
        public TextView tv_pkgVersionName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_pkgName = (TextView) itemView.findViewById(R.id.tv_pkgName);
            tv_pkgVersionCode = (TextView) itemView.findViewById(R.id.tv_VersionCode);
            tv_pkgVersionName = (TextView) itemView.findViewById(R.id.tv_VersionName);
        }
    }
}
