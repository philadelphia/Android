package com.example.atm.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atm.R;
import com.example.atm.common.BaseApplication;
import com.example.atm.entities.TroubleTicketEdit;
import com.example.atm.utils.ReportToNOCHelper;


@SuppressLint("InflateParams")
@SuppressWarnings("unused")
public class ReportToNOCAdapter extends BaseAdapter {

	private Context context;

	private String trobuleTicketID;
	private String imagePath;
	private String LoginID = null;
	private String image_name;
	private String siteID;

	private ViewHolder viewHolder = null;
	private ImageView ivHead;
	private ImageView bt_delete;
	private EditText ed_name;
	private File mImageFile;

	private TroubleTicketEdit troubleTicketEdit;
	private ReportToNOCHelper reportToNOCHelper;

	private List<TroubleTicketEdit> arrayList;

	public ReportToNOCAdapter(Context context, List<TroubleTicketEdit> list,
			String LoginID, String siteID, String trobuleTicketID) {
		this.context = context;
		this.arrayList = list;
		this.LoginID = LoginID;
		this.siteID = siteID;
		this.trobuleTicketID = trobuleTicketID;
		troubleTicketEdit = new TroubleTicketEdit();
		reportToNOCHelper = new ReportToNOCHelper(context);
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		viewHolder = new ViewHolder();
		troubleTicketEdit = new TroubleTicketEdit();
		troubleTicketEdit = arrayList.get(position);
		int i = troubleTicketEdit.getStr().lastIndexOf("/");
		image_name = troubleTicketEdit.getStr().substring(i + 1,
				troubleTicketEdit.getStr().length());

		TroubleTicketEdit edit = reportToNOCHelper.getTrobuleTicketByID(siteID,
				LoginID, troubleTicketEdit.getStr(), trobuleTicketID);
		if (null == edit) {
			reportToNOCHelper.insert(siteID, troubleTicketEdit.getStr(),
					image_name, LoginID, "xxx");
		}
		mImageFile = new File(troubleTicketEdit.getStr());

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.troubleticket_edit_item, null);
			viewHolder.ivHead = (ImageView) convertView
					.findViewById(R.id.ivHead);
			viewHolder.ed_name = (EditText) convertView
					.findViewById(R.id.ed_name);
			viewHolder.bt_delete = (ImageView) convertView
					.findViewById(R.id.bt_delete);
			viewHolder.bt_delete.setTag(troubleTicketEdit.getStr());
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		try {
			String path = troubleTicketEdit.getStr();
			FileInputStream in = new FileInputStream(path);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 10;
			imagePath = mImageFile.getAbsolutePath();
			int i1 = imagePath.lastIndexOf("/");
			String image_name1 = imagePath
					.substring(i1 + 1, imagePath.length());
			Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
			viewHolder.ivHead.setImageBitmap(bmp);
			viewHolder.ed_name.setText(image_name1);

			viewHolder.ivHead.setVisibility(View.VISIBLE);
			viewHolder.ed_name.setVisibility(View.VISIBLE);
			viewHolder.bt_delete.setVisibility(View.VISIBLE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		viewHolder.bt_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int i2 = v.getTag().toString().lastIndexOf("/");
				String image_name2 = v.getTag().toString()
						.substring(i2 + 1, v.getTag().toString().length());

				reportToNOCHelper.cancel(siteID, LoginID, image_name2, "xxx");

				notifyDataSetChanged();
				arrayList.remove(mImageFile);
				arrayList.remove(position);

				Message message = new Message();
				message.what = 5;
				BaseApplication.getHandler().sendMessage(message);
			}
		});

		return convertView;
	}

	class ViewHolder {
		ImageView ivHead;
		TextView ed_name;
		ImageView bt_delete;
	}
}
