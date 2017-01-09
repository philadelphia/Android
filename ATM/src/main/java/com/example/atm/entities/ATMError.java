package com.example.atm.entities;

public class ATMError extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	int a ;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
