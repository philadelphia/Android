package com.example.atm.entities;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/9/26.
 */
public class PCFilter {
    /**
     * ProductID : 1
     * ProductName : IB_ProTecht
     * CircleID : 1
     */

    private List<PrDetailBean> PrDetail;
    /**
     * CircleID : 1
     * CircleName : Tamil Nadu
     * ClusterID : 1,35,36,37
     */

    private List<PrCircleDetailBean> PrCircleDetail;
    /**
     * ClusterID : 1
     * ClusterName : Chennai
     */

    private List<PrClusterDetailBean> PrClusterDetail;

    public List<PrDetailBean> getPrDetail() {
        return PrDetail;
    }

    public void setPrDetail(List<PrDetailBean> PrDetail) {
        this.PrDetail = PrDetail;
    }

    public List<PrCircleDetailBean> getPrCircleDetail() {
        return PrCircleDetail;
    }

    public void setPrCircleDetail(List<PrCircleDetailBean> PrCircleDetail) {
        this.PrCircleDetail = PrCircleDetail;
    }

    public List<PrClusterDetailBean> getPrClusterDetail() {
        return PrClusterDetail;
    }

    public void setPrClusterDetail(List<PrClusterDetailBean> PrClusterDetail) {
        this.PrClusterDetail = PrClusterDetail;
    }

    public static class PrDetailBean {
        private int ProductID;
        private String ProductName;
        private String CircleID;

        public int getProductID() {
            return ProductID;
        }

        public void setProductID(int ProductID) {
            this.ProductID = ProductID;
        }

        public String getProductName() {
            return ProductName;
        }

        public void setProductName(String ProductName) {
            this.ProductName = ProductName;
        }

        public String getCircleID() {
            return CircleID;
        }

        public void setCircleID(String CircleID) {
            this.CircleID = CircleID;
        }

        @Override
        public String toString() {
            return "PrDetailBean{" +
                    "ProductID=" + ProductID +
                    ", ProductName='" + ProductName + '\'' +
                    ", CircleID='" + CircleID + '\'' +
                    '}';
        }
    }

    public static class PrCircleDetailBean {
        private int CircleID;
        private String CircleName;
        private String ClusterID;

        public int getCircleID() {
            return CircleID;
        }

        public void setCircleID(int CircleID) {
            this.CircleID = CircleID;
        }

        public String getCircleName() {
            return CircleName;
        }

        public void setCircleName(String CircleName) {
            this.CircleName = CircleName;
        }

        public String getClusterID() {
            return ClusterID;
        }

        public void setClusterID(String ClusterID) {
            this.ClusterID = ClusterID;
        }


        @Override
        public String toString() {
            return "PrCircleDetailBean{" +
                    "CircleID=" + CircleID +
                    ", CircleName='" + CircleName + '\'' +
                    ", ClusterID='" + ClusterID + '\'' +
                    '}';
        }
    }

    public static class PrClusterDetailBean {
        private int ClusterID;
        private String ClusterName;

        public int getClusterID() {
            return ClusterID;
        }

        public void setClusterID(int ClusterID) {
            this.ClusterID = ClusterID;
        }

        public String getClusterName() {
            return ClusterName;
        }

        public void setClusterName(String ClusterName) {
            this.ClusterName = ClusterName;
        }

        @Override
        public String toString() {
            return "PrClusterDetailBean{" +
                    "ClusterID=" + ClusterID +
                    ", ClusterName='" + ClusterName + '\'' +
                    '}';
        }
    }


}
