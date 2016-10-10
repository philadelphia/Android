package com.example.atm.bean;

import java.io.Serializable;

public class LoginResult implements Serializable
{
	private static final long serialVersionUID = 4465123407016229249L;
	private String	Result;

	
	public LoginResult() {
		super();
	}

	public LoginResult(String result) {
		Result = result;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

	@Override
	public String toString() {
		return "LoginResult{" +
				"Result='" + Result + '\'' +
				'}';
	}
}
