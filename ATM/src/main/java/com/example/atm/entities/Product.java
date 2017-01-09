package com.example.atm.entities;



public class Product  {
    /**
     * 
     */
//    private static final long serialVersionUID = -7440821676709437205L;
    private int ProductID;
    private String ProductName;
    private String CircleID;

    public int getProductID() {
        return ProductID;
    }
    public void setProductID(int productID) {
        this.ProductID = productID;
    }
    public String getProductName() {
        return ProductName;
    }
    public void setProductName(String productName) {
        this.ProductName = productName;
    }
    public String getCircleID() {
        return CircleID;
    }
    public void setCircleID(String circleID) {
        this.CircleID = circleID;
    }
  
    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return "[productID:"+ProductID + "productName"+ProductName +"circleID" +CircleID + "]";
    }
  
   
 
}
