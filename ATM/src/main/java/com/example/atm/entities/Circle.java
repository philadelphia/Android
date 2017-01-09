package com.example.atm.entities;


public class Circle {
   
    private String CircleID;
    private String CircleName;
    private String ClusterID;
    public String getCircleID() {
        return CircleID;
    }
    public void setCircleID(String circleID) {
        this.CircleID = circleID;
    }
    public String getCircleName() {
        return CircleName;
    }
    public void setCircleName(String circleName) {
        this.CircleName = circleName;
    }
    public String getClusterID() {
        return ClusterID;
    }
    public void setClusterID(String clusterID) {
        this.ClusterID = clusterID;
    }
    
    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return "[circleID:"+CircleID + "circleName"+CircleName +"clusterID" +ClusterID + "]";
    }
    
    
    
    
}
