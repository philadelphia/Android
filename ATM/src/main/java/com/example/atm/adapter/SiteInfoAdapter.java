package com.example.atm.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.atm.R;


public class SiteInfoAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<String> title;
	private ArrayList<String> words;
	private ViewHolder viewHolder=null;
	
	
	public SiteInfoAdapter(Context context, ArrayList<String> title,
			ArrayList<String> words) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.title=title;
		this.words=words;
		
		}

	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return words.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return words.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.site_info_item
					, null);
			viewHolder=new ViewHolder();
			viewHolder.titleTextView=(TextView) convertView.findViewById(R.id.titleTextview);
			viewHolder.wordsTextView=(TextView) convertView.findViewById(R.id.wordsTextView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.titleTextView.setText(title.get(position));
		viewHolder.wordsTextView.setText(words.get(position));
		return convertView;
	}
	class ViewHolder{
		TextView titleTextView;
		TextView wordsTextView;
	}
class SiteItem{
	private String title;
	private String words;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWords() {
		return words;
	}
	public void setWords(String words) {
		this.words = words;
	}
	
	
}
}
