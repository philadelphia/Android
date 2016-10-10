package com.example.atm.entities;


public class CurrentData {
	private int Signage;
	private int Aircon;
	private int Mute;
	private int Service;
	private int Light1;
	private int Light2;
	private int AcHVal;
	private int ACLVal;
	public int  getAcHVal() {
		return AcHVal;
	}
	public void setAcHVal(int acHVal) {
		AcHVal = acHVal;
	}

	

	public int getAcLVal() {
		return ACLVal;
	}
	public void setAcLVal(int ACLVal) {
		this.ACLVal = ACLVal;
	}
	public int getLight1() {
		return Light1;
	}
	public void setLight1(int light1) {
		Light1 = light1;
	}
	public int getLight2() {
		return Light2;
	}
	public void setLight2(int light2) {
		Light2 = light2;
	}
	public int getSignage() {
		return Signage;
	}
	public void setSignage(int signage) {
		Signage = signage;
	}
	public int getAircon() {
		return Aircon;
	}
	public void setAircon(int aircon) {
		Aircon = aircon;
	}
	public int getMute() {
		return Mute;
	}
	public void setMute(int mute) {
		Mute = mute;
	}
	public int getService() {
		return Service;
	}
	public void setService(int service) {
		Service = service;
	}
	
	public String toString(){
		return "HighTemp=" + this.AcHVal + "LowTemp=" + this.ACLVal;
		
	}
}
