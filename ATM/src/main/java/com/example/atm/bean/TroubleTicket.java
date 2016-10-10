package com.example.atm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/10/10.
 */

public class TroubleTicket {
    /**
     * TTNo : MTT2153
     * SiteID : 00INA41101
     * SiteName : Cantonment Trichy
     * Address : GF, 4/S7,Sanjay Regate, Reynolds Road, Cantonment, Trichy - 620 001
     * Longitude : 78.682017
     * Lattitude : 10.807213
     * GenDateTime : 29/Mar/2016 12:20:25
     * TTDesc : The trouble ticket was clarified by changing a new beacon lamp
     */

    private List<TTDataBean> TTData;

    public List<TTDataBean> getTTData() {
        return TTData;
    }

    public void setTTData(List<TTDataBean> TTData) {
        this.TTData = TTData;
    }

    public static class TTDataBean implements Serializable {
        private String TTNo;
        private String SiteID;
        private String SiteName;
        private String Address;
        private String Longitude;
        private String Lattitude;
        private String GenDateTime;
        private String TTDesc;

        public String getTTNo() {
            return TTNo;
        }

        public void setTTNo(String TTNo) {
            this.TTNo = TTNo;
        }

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

        public String getGenDateTime() {
            return GenDateTime;
        }

        public void setGenDateTime(String GenDateTime) {
            this.GenDateTime = GenDateTime;
        }

        public String getTTDesc() {
            return TTDesc;
        }

        public void setTTDesc(String TTDesc) {
            this.TTDesc = TTDesc;
        }
    }
}
