package com.example.atm.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LoginResult implements Serializable {
	private String Message;

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

}
