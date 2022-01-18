package com.example.myapplication.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.RecyclerViewActivity;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/23.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<RecyclerViewActivity.Item> datas;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_key.setText(datas.get(position).key);
    }

    public ItemAdapter(List<RecyclerViewActivity.Item> itemList) {
        this.datas = itemList;
    }


    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_key;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_key = itemView.findViewById(R.id.tv_key);
        }
    }

}
