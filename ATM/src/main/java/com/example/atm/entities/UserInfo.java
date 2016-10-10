package com.example.atm.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserInfo implements Serializable {

	private String Name;
	private String Email;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

}
