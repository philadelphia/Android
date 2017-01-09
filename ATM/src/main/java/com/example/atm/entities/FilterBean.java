package com.example.atm.entities;

import java.io.Serializable;

public class FilterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer productID;
	private String cirlceID;
	private String clusterID;
	private String tag;
	private String loginID;
	private String ProductName;
	private String CircleName;
	private String ClusterName;

	public FilterBean(String tag, String loginID) {
		super();
		this.tag = tag;
		this.loginID = loginID;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getProductID() {
		return productID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public String getCirlceID() {
		return cirlceID;
	}

	public void setCirlceID(String cirlceID) {
		this.cirlceID = cirlceID;
	}

	public String getClusterID() {
		return clusterID;
	}

	public void setClusterID(String clusterID) {
		this.clusterID = clusterID;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public FilterBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FilterBean(String tag, Integer productName, String cirlceName,
			String clustereName) {
		super();
		this.tag = tag;
		cirlceID = cirlceName;
		productID = productName;
		clusterID = clustereName;
	}

	public FilterBean(Integer productID, String cirlceID, String clusterID,
			String tag, String loginID) {
		super();
		this.productID = productID;
		this.cirlceID = cirlceID;
		this.clusterID = clusterID;
		this.tag = tag;
		this.loginID = loginID;
	}

	public FilterBean(Integer productID, String cirlceID, String clusterID,
			String tag, String loginID, String ProductName, String CircleName,
			String ClusterName) {
		super();
		this.productID = productID;
		this.cirlceID = cirlceID;
		this.clusterID = clusterID;
		this.tag = tag;
		this.loginID = loginID;
		this.ProductName = ProductName;
		this.CircleName = CircleName;
		this.ClusterName = ClusterName;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getCircleName() {
		return CircleName;
	}

	public void setCircleName(String circleName) {
		CircleName = circleName;
	}

	public String getClusterName() {
		return ClusterName;
	}

	public void setClusterName(String clusterName) {
		ClusterName = clusterName;
	}

}
