package com.example.atm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.atm.bean.SiteItem;
import com.example.atm.R;
import com.example.atm.utils.CustomItemClickListener;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/7/27.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<SiteItem> dataList;
    private CustomItemClickListener customItemClickListener;
    private Context context;

    private int position;

    public RecyclerViewAdapter(Context context, List<SiteItem> dataList) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_site, parent, false);
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
        final SiteItem siteItem = dataList.get(position);
        String siteId = siteItem.getSiteID();
        int alarmNum = siteItem.getAlarmNum();
        boolean flag = siteItem.isFlag();
        String severity = siteItem.getSeverity();

        holder.siteIDTextView.setText(siteItem.getSiteID());
        holder.siteNameTextView.setText(siteItem.getSiteName().trim());

        // alarmNum equlas 0 ,hidden serverity icon and alarmnum icon
        if (flag) {
            holder.siteIDTextView.setTextColor(context.getResources()
                    .getColor(R.color.green));
        } else {

            holder.siteIDTextView.setTextColor(context.getResources()
                    .getColor(R.color.red));
        }
        if (alarmNum == 0) {
            holder.siteAlarmNum.setVisibility(View.INVISIBLE);
            holder.siteServerity.setVisibility(View.GONE);
        } else {
            holder.siteAlarmNum.setVisibility(View.VISIBLE);
            holder.siteServerity.setVisibility(View.VISIBLE);
            holder.siteAlarmNum.setText(alarmNum + "");
            if (severity.equalsIgnoreCase("Critical")) {
                holder.siteServerity
                        .setImageResource(R.mipmap.ic_exclamation_red);
            } else if (severity.equalsIgnoreCase("Major")) {
                holder.siteServerity
                        .setImageResource(R.mipmap.ic_exclamation_brown);
            } else if (severity.equalsIgnoreCase("Minor")) {
                holder.siteServerity
                        .setImageResource(R.mipmap.ic_exclamation_yellow);
            }

            holder.starImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView imageView = (ImageView) view;
                    if (siteItem.ismIsStarred()) {
                        imageView.setImageResource((R.mipmap.ic_star_gray));
                        siteItem.setmIsStarred(false);
                        // 取消状态，从数据库删掉
                    } else {
                        imageView.setImageResource((R.mipmap.ic_star_brown));
                        siteItem.setmIsStarred(true);
                    }
                }
            });

        }
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
        TextView siteNameTextView;
        TextView siteIDTextView;
        TextView siteAlarmNum;
        ImageView starImageView;
        ImageView siteServerity;

        public MyViewHolder(View itemView) {
            super(itemView);
            siteNameTextView = (TextView) itemView
                    .findViewById(R.id.primary_text);
            siteIDTextView = (TextView) itemView
                    .findViewById(R.id.secondary_text);
            starImageView = (ImageView) itemView
                    .findViewById(R.id.icon);
            siteAlarmNum = (TextView) itemView
                    .findViewById(R.id.alarmnum);
            siteServerity = (ImageView) itemView
                    .findViewById(R.id.serverity);
        }


    }
}
