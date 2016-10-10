package com.example.atm.bean;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/9/28.
 */
public class SiteInfoData {

    /**
     * SiteID : 00E1ACCL01
     * SiteName :  Batten Complex-Calicut
     * Address : Batten Complex,3 / 339 – A,West Nadakkavu,
     Kozhikode,Calicut
     ,Kerala


     * Circle : Kerala
     * Cluster : Calicut
     * Country : India
     * Critical : 1
     * Major : 1
     * Minor : 0
     * SimNo :
     * Longitude : 11.992541
     * Lattitude : 75.449039
     * Flag : true
     */

    private List<SiteInfoDataBean> SiteInfoData;

    public List<SiteInfoDataBean> getSiteInfoData() {
        return SiteInfoData;
    }

    public void setSiteInfoData(List<SiteInfoDataBean> SiteInfoData) {
        this.SiteInfoData = SiteInfoData;
    }

    @Override
    public String toString() {
        return "SiteInfoData{" +
                "SiteInfoData=" + SiteInfoData +
                '}';
    }

    public static class SiteInfoDataBean {
        private String SiteID;
        private String SiteName;
        private String Address;
        private String Circle;
        private String Cluster;
        private String Country;
        private int Critical;
        private int Major;
        private int Minor;
        private String SimNo;
        private String Longitude;
        private String Lattitude;
        private String Flag;

        public String getSiteID() {
            return SiteID;
        }

        public void setSiteID(String SiteID) {
            this.SiteID = SiteID;
        }

        public String getSiteName() {
            return SiteName;
        }

        public void setSiteName(String SiteName) {
            this.SiteName = SiteName;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getCircle() {
            return Circle;
        }

        public void setCircle(String Circle) {
            this.Circle = Circle;
        }

        public String getCluster() {
            return Cluster;
        }

        public void setCluster(String Cluster) {
            this.Cluster = Cluster;
        }

        public String getCountry() {
            return Country;
        }

        public void setCountry(String Country) {
            this.Country = Country;
        }

        public int getCritical() {
            return Critical;
        }

        public void setCritical(int Critical) {
            this.Critical = Critical;
        }

        public int getMajor() {
            return Major;
        }

        public void setMajor(int Major) {
            this.Major = Major;
        }

        public int getMinor() {
            return Minor;
        }

        public void setMinor(int Minor) {
            this.Minor = Minor;
        }

        public String getSimNo() {
            return SimNo;
        }

        public void setSimNo(String SimNo) {
            this.SimNo = SimNo;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getLattitude() {
            return Lattitude;
        }

        public void setLattitude(String Lattitude) {
            this.Lattitude = Lattitude;
        }

        public String getFlag() {
            return Flag;
        }

        public void setFlag(String Flag) {
            this.Flag = Flag;
        }

        @Override
        public String toString() {
            return "SiteInfoDataBean{" +
                    "SiteID='" + SiteID + '\'' +
                    ", SiteName='" + SiteName + '\'' +
                    ", Address='" + Address + '\'' +
                    ", Circle='" + Circle + '\'' +
                    ", Cluster='" + Cluster + '\'' +
                    ", Country='" + Country + '\'' +
                    ", Critical=" + Critical +
                    ", Major=" + Major +
                    ", Minor=" + Minor +
                    ", SimNo='" + SimNo + '\'' +
                    ", Longitude='" + Longitude + '\'' +
                    ", Lattitude='" + Lattitude + '\'' +
                    ", Flag='" + Flag + '\'' +
                    '}';
        }
    }
}
