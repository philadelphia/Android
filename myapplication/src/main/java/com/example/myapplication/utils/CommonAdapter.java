package com.delta.ams.common.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder>
{
	protected final List<T>			mData;

	protected final Context			mContext;

	protected LayoutInflater		mInflater;

	private OnItemClickListener		mClickListener;

	private OnItemLongClickListener	mLongClickListener;

	public CommonAdapter(Context context, List<T> list)
	{
		mData = (list != null) ? list : new ArrayList<T> ();
		mContext = context;
		mInflater = LayoutInflater.from (context);
	}
	@Override
	public CommonViewHolder onCreateViewHolder (ViewGroup parent, int viewType)
	{
		View v = mInflater.inflate (getItemLayoutId (viewType), parent, false);
		setLayoutParameter (v);
		final CommonViewHolder holder = new CommonViewHolder (mContext, v);
		if (mClickListener != null)
		{
			holder.itemView.setOnClickListener (new View.OnClickListener ()
			{
				@Override
				public void onClick (View v)
				{
					mClickListener.onItemClick (holder.itemView, holder.getLayoutPosition ());
				}
			});
		}
		if (mLongClickListener != null)
		{
			holder.itemView.setOnLongClickListener (new View.OnLongClickListener ()
			{
				@Override
				public boolean onLongClick (View v)
				{
					mLongClickListener.onItemLongClick (holder.itemView, holder.getLayoutPosition ());
					return true;
				}
			});
		}
		return holder;
	}

	@Override
	public void onBindViewHolder (CommonViewHolder holder, int position)
	{
		bindData (holder, position, mData.get (position));
	}

	@Override
	public int getItemCount ()
	{
		return mData.size ();
	}

	public void add (int pos, T item)
	{
		mData.add (pos, item);
		notifyItemInserted (pos);
	}

	public void delete (int pos)
	{
		mData.remove (pos);
		notifyItemRemoved (pos);
	}

	public void setOnItemClickListener (OnItemClickListener listener)
	{
		mClickListener = listener;
	}

	public void setOnItemLongClickListener (OnItemLongClickListener listener)
	{
		mLongClickListener = listener;

	}

	abstract public int getItemLayoutId (int viewType);

	abstract public void bindData (CommonViewHolder holder, int position, T item);

	abstract public void setLayoutParameter (View v);

	public interface OnItemClickListener
	{
		public void onItemClick (View itemView, int pos);
	}

	public interface OnItemLongClickListener
	{
		public void onItemLongClick (View itemView, int pos);
	}

}