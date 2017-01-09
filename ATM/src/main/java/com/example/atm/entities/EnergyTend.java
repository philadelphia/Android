package com.example.atm.entities;


public class EnergyTend extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double EnergyVal;
	private String DateStamp;

	public String getDateStamp() {
		return DateStamp;
	}

	public void setDateStamp(String dateStamp) {
		DateStamp = dateStamp;
	}

	public EnergyTend() {
		super();

	}

	public EnergyTend( double EnergyVal, String dateStamp) {
		super();
	
		this.EnergyVal = EnergyVal;
		this.DateStamp = dateStamp;
	}




	public double getEnergyVal() {
		return EnergyVal;
	}

	public void setEnergyVal(double energyVal) {
		EnergyVal = energyVal;
	}

	@Override
	public String toString() {
		return  "[EnergyVal=" + EnergyVal + ", DateStamp="
				+ DateStamp + "]";
	}

}
