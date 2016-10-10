package com.example.atm.entities;

import java.io.Serializable;

public class SiteMap implements Serializable {

	private static final long serialVersionUID = -6031901364706391488L;

	private String SiteID;
	private String SiteName;
	private String Longitude;
	private String Lattitude;
	private String IsAlarm;

	public String getSiteID() {
		return SiteID;
	}

	public void setSiteID(String siteID) {
		SiteID = siteID;
	}

	public String getSiteName() {
		return SiteName;
	}

	public void setSiteName(String siteName) {
		SiteName = siteName;
	}

	public String getIsAlarm() {
		return IsAlarm;
	}

	public void setIsAlarm(String isAlarm) {
		IsAlarm = isAlarm;
	}

	public String getLongitude() {
		return Longitude;
	}

	public void setLongitude(String longitude) {
		Longitude = longitude;
	}

	public String getLattitude() {
		return Lattitude;
	}

	public void setLattitude(String lattitude) {
		Lattitude = lattitude;
	}

}
