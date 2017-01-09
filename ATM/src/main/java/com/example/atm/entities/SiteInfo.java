package com.example.atm.entities;

public class SiteInfo {

	private String SiteID;
	private String SiteName;
	private String Address;
	private String Circle;
	private String Cluster;
	private String Country;
	private String Critical;
	private String Major;
	private String Minor;
	private String SimNo;
	private String Longitude;
	private String Lattitude;
	private boolean Flag;

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

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCircle() {
		return Circle;
	}

	public void setCircle(String circle) {
		Circle = circle;
	}

	public String getCluster() {
		return Cluster;
	}

	public void setCluster(String cluster) {
		Cluster = cluster;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getCritical() {
		return Critical;
	}

	public void setCritical(String critical) {
		Critical = critical;
	}

	public String getMajor() {
		return Major;
	}

	public void setMajor(String major) {
		Major = major;
	}

	public String getMinor() {
		return Minor;
	}

	public void setMinor(String minor) {
		Minor = minor;
	}

	public String getSimNo() {
		return SimNo;
	}

	public void setSimNo(String simNo) {
		SimNo = simNo;
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

	public boolean isFlag() {
		return Flag;
	}

	public void setFlag(boolean flag) {
		Flag = flag;
	}

}