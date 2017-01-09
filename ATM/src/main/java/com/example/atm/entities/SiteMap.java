package com.example.atm.entities;

import java.io.Serializable;
import java.util.List;

public class SiteMap implements Serializable {


	/**
	 * SiteID : 00S1ACCM02
	 * SiteName :  Robinson Road
	 * Longitude : 00
	 * Lattitude : 00
	 * IsAlarm : No
	 */

	private List<SiteMapDataBean> SiteMapData;

	public List<SiteMapDataBean> getSiteMapData() {
		return SiteMapData;
	}

	public void setSiteMapData(List<SiteMapDataBean> SiteMapData) {
		this.SiteMapData = SiteMapData;
	}

	public static class SiteMapDataBean {
		private String SiteID;
		private String SiteName;
		private String Longitude;
		private String Lattitude;
		private String IsAlarm;

		public String getSiteID() {
			return SiteID;
		}

		public void setSiteID(String SiteID) {
			this.SiteID = SiteID;
		}

		public String getSiteName() {
			return SiteName;
		}

		public void setSiteName(String SiteName) {
			this.SiteName = SiteName;
		}

		public String getLongitude() {
			return Longitude;
		}

		public void setLongitude(String Longitude) {
			this.Longitude = Longitude;
		}

		public String getLattitude() {
			return Lattitude;
		}

		public void setLattitude(String Lattitude) {
			this.Lattitude = Lattitude;
		}

		public String getIsAlarm() {
			return IsAlarm;
		}

		public void setIsAlarm(String IsAlarm) {
			this.IsAlarm = IsAlarm;
		}
	}
}
