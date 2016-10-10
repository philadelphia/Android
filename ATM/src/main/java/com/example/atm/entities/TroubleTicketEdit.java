package com.example.atm.entities;

import java.io.Serializable;

public class TroubleTicketEdit implements Serializable {

	private static final long serialVersionUID = 1L;
	private String siteID;
	private String str;
	private String ImageName;
	private String loginID;
	private String trobuleTicketID;

	public TroubleTicketEdit() {
		super();
	}

	public String getTrobuleTicketID() {
		return trobuleTicketID;
	}

	public void setTrobuleTicketID(String trobuleTicketID) {
		this.trobuleTicketID = trobuleTicketID;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getImageName() {
		return ImageName;
	}

	public void setImageName(String imageName) {
		ImageName = imageName;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getSiteID() {
		return siteID;
	}

	public void setSiteID(String siteID) {
		this.siteID = siteID;
	}

	public TroubleTicketEdit(String siteID, String str, String imageName,
			String loginID) {
		super();
		this.siteID = siteID;
		this.str = str;
		ImageName = imageName;
		this.loginID = loginID;
	}

	public TroubleTicketEdit(String siteID, String str, String imageName,
			String loginID, String trobuleTicketID) {
		super();
		this.siteID = siteID;
		this.str = str;
		ImageName = imageName;
		this.loginID = loginID;
		this.trobuleTicketID = trobuleTicketID;
	}

}
