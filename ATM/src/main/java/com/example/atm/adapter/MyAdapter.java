package com.example.atm.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atm.R;


public class MyAdapter extends BaseAdapter {
	private List<String> data = new ArrayList<String>();
	private Context context;
	private final static String  NO_ALARM_TAG = "NO ACTIVE ALARMS";

	public MyAdapter(List<String> mDatas, Context context) {
		this.data = mDatas;
		this.context = context;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.alarm_item_listview, parent, false);
			holder = new ViewHolder();
			holder.ivStatus = (ImageView) convertView
					.findViewById(R.id.iv_chest_open);
			holder.tvAtmName = (TextView) convertView
					.findViewById(R.id.tv_chest_open);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if(data.get(position).equalsIgnoreCase(NO_ALARM_TAG)){
		    	holder.tvAtmName.setText(NO_ALARM_TAG);
			holder.ivStatus.setVisibility(View.GONE);
		}else {
		    holder.tvAtmName.setText(data.get(position));
		}
		
		return convertView;
	}

	class ViewHolder {
		ImageView ivStatus;
		TextView tvAtmName;
	}

}
