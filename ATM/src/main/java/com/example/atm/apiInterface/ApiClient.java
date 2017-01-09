package com.example.atm.apiInterface;

import com.example.atm.bean.SiteData;
import com.example.atm.bean.SiteInfoData;
import com.example.atm.bean.TroubleTicket;
import com.example.atm.entities.PCFilter;
import com.example.atm.entities.SiteAlarmData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Tao.ZT.Zhang on 2016/9/22.
 */

public interface ApiClient {
    @POST("/WebAPI/API/ConsolidatedSite")
    Call<SiteData> getAllSites(@Header("LoginID") String id);

    @POST("/WebAPI/API/PCCFilter")
    Call<PCFilter> getAllProducts(@Header("LoginID") String id);

    @POST("/WebAPI/API/SiteSearch")
    Call<SiteData> getSiteResults(@Header("LoginID") String id, @Header("SiteName") String siteName);

    @POST("/WebAPI/API/SiteInfo")
    Call<SiteInfoData> getSiteInformation(@Header("LoginID") String id, @Header("SiteID") String siteID);

    @POST("/WebAPI/API/{AlarmPage}")
   Call<SiteAlarmData> getAlarmInfo(@Header("LoginID") String id, @Header("SiteID") String siteID, @Header("SiteInfoID") String siteInfoID, @Path("AlarmPage")String path);

    @POST("/WebAPI/API/TroubleTicket")
    Call<TroubleTicket> getTroubleTicketList(@Header("LoginID") String id);

}
