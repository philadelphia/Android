package com.example.atm.entities;


@SuppressWarnings("serial")
public class Measurement extends BaseEntity {

	public String MName;
	public String MValue;

	public String getMName() {
		return MName;
	}

	public void setMName(String mName) {
		MName = mName;
	}

	public String getMValue() {
		return MValue;
	}

	public void setMValue(String mValue) {
		MValue = mValue;
	}

}
