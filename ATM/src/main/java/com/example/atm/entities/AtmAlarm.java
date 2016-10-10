/*
 * Delta CONFIDENTIAL
 *
 * (C) Copyright Delta Electronics, Inc. 2014 All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the
 * property of Delta Electronics. The intellectual and technical
 * concepts contained herein are proprietary to Delta Electronics
 * and are protected by trade secret, patent law or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Delta Electronics.
 */

/**
 * @author Tao.ZT.Zhang
 */
package com.example.atm.entities;

import java.util.List;

public class AtmAlarm {
    private List<String> mSecurityList;
    private String atmNameString;
    public String getAtmNameString() {
        return atmNameString;
    }

    public void setAtmNameString(String atmNameString) {
        this.atmNameString = atmNameString;
    }

    public AtmAlarm(){
	
    }

    public AtmAlarm(String atmNameString, List<String> mSecurityList) {
   	super();
   	this.atmNameString = atmNameString;
   	this.mSecurityList = mSecurityList;
       }
    
    public List<String> getmSecurityList() {
        return mSecurityList;
    }

    public void setmSecurityList(List<String> mSecurityList) {
        this.mSecurityList = mSecurityList;
    }

   

  
}
  
