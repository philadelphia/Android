package com.example.retrofitdemo.bean;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
public class Message implements  Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9157752829868728313L;
	@SerializedName("userId")
	private int		id;
	private int		roleId;
	@SerializedName("userName")
	private String	name;
	private String	token;
	
	public Message() {
		super();
	}
	
	
	public Message(int id, int roleId, String name, String token) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.name = name;
		this.token = token;
	}

	

	@Override
	public String toString() {
		return "Message [id=" + id + ", roleId=" + roleId + ", name=" + name
				+ ", token=" + token + "]";
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
