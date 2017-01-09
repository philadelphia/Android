package com.example.atm.entities;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class SiteImageItem implements Serializable, Comparable<SiteImageItem> {

	private static final long serialVersionUID = 5158488433551536483L;

	private String EventName;
	private String DateTime;
	private String ImageURL;
	private Date d1;
	private Date d2;

	public SiteImageItem() {
		super();
	}

	public SiteImageItem(String eventName, String dateTime, String imageURL) {
		super();
		EventName = eventName;
		DateTime = dateTime;
		ImageURL = imageURL;
	}

	public String getEventName() {
		return EventName;
	}

	public void setEventName(String eventName) {
		EventName = eventName;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	@SuppressWarnings("unused")
	@Override
	public int compareTo(SiteImageItem another) {
		String time = this.getDateTime();
		String time2 = time.replaceAll("T", " ");

		String anotherTime = another.getDateTime();
		String anotherTime2 = time.replaceAll("T", " ");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			d1 = sdf.parse(time2);
			d2 = sdf.parse(anotherTime2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (d1.after(d2)) {
			return 1;
		} else if (d1 == d2) {
			return 0;
		} else {
			return -1;
		}
	}
}
