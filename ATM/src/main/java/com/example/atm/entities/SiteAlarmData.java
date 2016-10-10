package com.example.atm.entities;

import java.util.List;

/**
 * Created by Tao.ZT.Zhang on 2016/9/29.
 */

public class SiteAlarmData {

    /**
     * SiteSingleData : [{"Date":"2015-09-23T15:54:08","Time":"2015-09-23T15:54:08","ATMCount":0,"SiteInfoID":44879784}]
     * SiteCurrentData : [{"AlarmName":"System Tampered","Status":"Active","Zone":"Removal"},{"AlarmName":"Service Rm Door Open","Status":"Active","Zone":"Removal"},{"AlarmName":"Emergency at site","Status":"Active","Zone":"Removal"},{"AlarmName":"ATM1 Vibration","Status":"Active","Zone":"Security"},{"AlarmName":"ATM1 Displaced","Status":"Active","Zone":"Security"},{"AlarmName":"Beacon Lamp ON","Status":"Active","Zone":"Removal"},{"AlarmName":"ATM2 Vibration","Status":"Active","Zone":"Security"},{"AlarmName":"AC 2 Removal","Status":"Active","Zone":"Removal"},{"AlarmName":"AC1 Removal","Status":"Active","Zone":"Removal"},{"AlarmName":"Drop Box Removal","Status":"Active","Zone":"Removal"},{"AlarmName":"Security System Removed","Status":"Active","Zone":"Removal"},{"AlarmName":"DVR Alarm","Status":"Active","Zone":"Removal"},{"AlarmName":"ATM1 Chest Open","Status":"Active","Zone":"Security"},{"AlarmName":"ATM2 Door Open","Status":"Active","Zone":"Security"},{"AlarmName":"Cheque Box Open","Status":"Active","Zone":"Removal"},{"AlarmName":"Shutter Open","Status":"Active","Zone":"Removal"},{"AlarmName":"ATM2 Displaced","Status":"Active","Zone":"Security"},{"AlarmName":"Glass Door Tampered","Status":"Active","Zone":"Removal"},{"AlarmName":"Movement On Site","Status":"Active","Zone":"Removal"},{"AlarmName":"ATM1 Cut Attempt","Status":"Active","Zone":"Security"},{"AlarmName":"ATM2 Cut Attempt","Status":"Active","Zone":"Security"}]
     * Message : The request is invalid.
     * ModelState : {"Error":["Site Info ID does not match"]}
     */

    private String Message;
    private ModelStateBean ModelState;
    /**
     * Date : 2015-09-23T15:54:08
     * Time : 2015-09-23T15:54:08
     * ATMCount : 0
     * SiteInfoID : 44879784
     */

    private List<SiteSingleDataBean> SiteSingleData;
    /**
     * AlarmName : System Tampered
     * Status : Active
     * Zone : Removal
     */

    private List<SiteCurrentDataBean> SiteCurrentData;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public ModelStateBean getModelState() {
        return ModelState;
    }

    public void setModelState(ModelStateBean ModelState) {
        this.ModelState = ModelState;
    }

    public List<SiteSingleDataBean> getSiteSingleData() {
        return SiteSingleData;
    }

    public void setSiteSingleData(List<SiteSingleDataBean> SiteSingleData) {
        this.SiteSingleData = SiteSingleData;
    }

    public List<SiteCurrentDataBean> getSiteCurrentData() {
        return SiteCurrentData;
    }

    public void setSiteCurrentData(List<SiteCurrentDataBean> SiteCurrentData) {
        this.SiteCurrentData = SiteCurrentData;
    }

    public static class ModelStateBean {
        private List<String> Error;

        public List<String> getError() {
            return Error;
        }

        public void setError(List<String> Error) {
            this.Error = Error;
        }
    }

    public static class SiteSingleDataBean {
        private String Date;
        private String Time;
        private int ATMCount;
        private int SiteInfoID;

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
        }

        public int getATMCount() {
            return ATMCount;
        }

        public void setATMCount(int ATMCount) {
            this.ATMCount = ATMCount;
        }

        public int getSiteInfoID() {
            return SiteInfoID;
        }

        public void setSiteInfoID(int SiteInfoID) {
            this.SiteInfoID = SiteInfoID;
        }
    }

    public static class SiteCurrentDataBean {
        private String AlarmName;
        private String Status;
        private String Zone;

        public String getAlarmName() {
            return AlarmName;
        }

        public void setAlarmName(String AlarmName) {
            this.AlarmName = AlarmName;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }

        public String getZone() {
            return Zone;
        }

        public void setZone(String Zone) {
            this.Zone = Zone;
        }
    }
}
