package com.example.atm.entities;

public class Cluster {
    private String ClusterID;
    private String ClusterName;
    public String getClusterID() {
        return ClusterID;
    }
    public void setClusterID(String clusterID) {
        this.ClusterID = clusterID;
    }
    public String getClusterName() {
        return ClusterName;
    }
    public void setClusterName(String clusterName) {
        this.ClusterName = clusterName;
    }
    
    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return "[clusterID:"+ClusterID + "clusterName"+ClusterName +"]";
    }
}
