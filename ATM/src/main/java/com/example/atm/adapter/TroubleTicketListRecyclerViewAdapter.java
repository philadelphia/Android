package com.example.atm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.atm.R;

import com.example.atm.bean.TroubleTicket;
import com.example.atm.utils.CustomItemClickListener;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/27.
 */
public class TroubleTicketListRecyclerViewAdapter extends RecyclerView.Adapter<TroubleTicketListRecyclerViewAdapter.MyViewHolder> {
    private List<TroubleTicket.TTDataBean> dataList;
    private CustomItemClickListener customItemClickListener;
    private Context context;

    private int position;

    public TroubleTicketListRecyclerViewAdapter(Context context, List<TroubleTicket.TTDataBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    public void setOnCustomeItemClickListener(CustomItemClickListener customItemClickListener) {
        this.customItemClickListener = customItemClickListener;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.troubleticket_item, parent, false);
        final MyViewHolder viewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customItemClickListener.onItemClick(view, viewHolder.getAdapterPosition());
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                customItemClickListener.onItemLongClick(view, viewHolder.getAdapterPosition());
                return true;
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final TroubleTicket.TTDataBean ttDataBean = dataList.get(position);
        String ttid = ttDataBean.getTTNo().trim();
        String ttDesc = ttDataBean.getTTDesc().trim();
        String siteId = ttDataBean.getSiteID().trim();
        String siteName = ttDataBean.getSiteName().trim();
        holder.tv_ttid.setText(ttid);
        holder.tv_ttDesc.setText(ttDesc);
        holder.tv_siteID.setText(siteId);
        holder.tv_siteName.setText(siteName);

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


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ttid;
        TextView tv_ttDesc;
        TextView tv_siteID;
        TextView tv_siteName;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_ttid = (TextView) itemView.findViewById(R.id.tv_ttID);
            tv_ttDesc = (TextView) itemView.findViewById(R.id.tv_ttDesc);
            tv_siteID = (TextView) itemView.findViewById(R.id.tv_siteID);
            tv_siteName = (TextView) itemView.findViewById(R.id.tv_siteName);

        }


    }
}
