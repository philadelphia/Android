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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.utils.CustomItemClickListener;
import com.example.myapplication.utils.ItemTouchHelperAdapterCallBack;

import java.util.Collections;
import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/27.
 */
public class RecyclerViewItemTouchAdapter extends RecyclerView.Adapter<RecyclerViewItemTouchAdapter.SwipeViewHolder> implements ItemTouchHelperAdapterCallBack{
    private  List<PackageInfo> dataList;
    private CustomItemClickListener customItemClickListener;
    private static final String TAG = "RecyclerViewAdapter";

    public RecyclerViewItemTouchAdapter(List<PackageInfo> dataList) {

        this.dataList = dataList;
    }

    @Override
    public  SwipeViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layotu_swipe_item,parent,false);
        SwipeViewHolder viewHolder = new SwipeViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(final SwipeViewHolder holder, final int position) {
        Log.i(TAG, "position: " + position);
        Log.i(TAG, "holder.hashCode(): " + holder.hashCode());
        PackageInfo packageInfo = dataList.get(position);
        holder.bind(packageInfo);
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

    @Override
    public boolean onMove(RecyclerView recyclerView, int source, int dest) {
        Collections.swap(dataList, source, dest);
        notifyItemMoved(source, dest);
        return false;
    }

    @Override
    public void onSwipe(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder getViewHolder() {
        return null;
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener{
        public TextView tv_pkgName;
        public TextView tv_pkgVersionCode;
        public TextView tv_pkgVersionName;
        public RelativeLayout viewContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            viewContent = ((RelativeLayout) itemView.findViewById(R.id.itemContent));
            tv_pkgName = (TextView) itemView.findViewById(R.id.tv_pkgName);
            tv_pkgVersionCode = (TextView) itemView.findViewById(R.id.tv_VersionCode);
            tv_pkgVersionName = (TextView) itemView.findViewById(R.id.tv_VersionName);
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            itemView.setBackgroundColor(Color.BLUE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }

        public void bind(PackageInfo packageInfo) {
            tv_pkgName.setText(packageInfo.packageName);
            tv_pkgVersionCode.setText(String.valueOf(packageInfo.versionCode));
            tv_pkgVersionName.setText(packageInfo.versionName);
        }
    }

    public  class SwipeViewHolder extends  MyViewHolder{
        private Button btnDelete;
        private Button btnRefresh;
        public View itemBackGround;


        private SwipeViewHolder(View itemView){
            super(itemView);
            itemBackGround = itemView.findViewById(R.id.item_background);
            this.btnDelete = (Button) itemView.findViewById(R.id.btn_delete);
            this.btnRefresh = (Button) itemView.findViewById(R.id.btn_refresh);
        }

        @Override
        public void bind(PackageInfo packageInfo) {
            super.bind(packageInfo);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doDelete(getAdapterPosition());
                }


            });

            btnRefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "第" + (getAdapterPosition() + 1) + "个元素正在被刷新!",Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void doDelete(int position) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }

    }
}
