package com.example.atm.apiInterface;

import com.example.atm.bean.LoginResult;
import com.example.atm.bean.SiteData;
import com.example.atm.bean.SiteInfoData;
import com.example.atm.bean.TroubleTicket;
import com.example.atm.bean.UpdatePasswordResult;
import com.example.atm.bean.UserInfo;
import com.example.atm.entities.SiteAlarmData;
import com.example.atm.entities.SiteMap;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by Tao.ZT.Zhang on 2016/9/22.
 */

public interface ApiClientRxJava {
    @POST("/WebAPI/API/Login")
    Observable<LoginResult> login(@Header("LoginID") String id, @Header("Password") String password);

    @POST("/WebAPI/API/UserProfile")
    Observable<UserInfo> getUserInfo(@Header("LoginID") String id);

    @POST("/WebAPI/API/ChangePass")
    Observable<UpdatePasswordResult> updateUserPassword(@Header("LoginID") String id, @Header("CurPass") String currentPasswd, @Header("NewPass") String newPasswd);

    @POST("/WebAPI/API/ConsolidatedSite")
    Observable<SiteData> getAllSites(@Header("LoginID") String id);

    @POST("/WebAPI/API/SiteSearch")
    Observable<SiteData> getSiteResults(@Header("LoginID") String id, @Header("SiteName") String siteName);

    @POST("/WebAPI/API/SiteInfo")
    Observable<SiteInfoData> getSiteInformation(@Header("LoginID") String id, @Header("SiteID") String siteID);

    @POST("/WebAPI/API/{AlarmPage}")
    Observable<SiteAlarmData> getAlarmInfo(@Header("LoginID") String id, @Header("SiteID") String siteID, @Header("SiteInfoID") String siteInfoID, @Path("AlarmPage") String path);

    @POST("/WebAPI/API/TroubleTicket")
    Observable<TroubleTicket> getTroubleTicketList(@Header("LoginID") String id);

    @POST("/WebAPI/API/Product")
    Observable<SiteData> queryProduct(@Header("LoginID") String id, @Header("ProductID") String productID);

    @POST("/WebAPI/API/Circle")
    Observable<SiteData> queryCircle(@Header("LoginID") String id, @Header("ProductID") String productID, @Header("CircleID") String circleID);

    @POST("/WebAPI/API/Cluster")
    Observable<SiteData> queryCluster(@Header("LoginID") String id, @Header("ProductID") String productID, @Header("CircleID") String circleID, @Header("ClusterID") String clusterID);

    /*get siteMap information*/
    @POST("/WebAPI/API/SiteMap")
    Observable<SiteMap>getAllSiteMapInfo(@Header("LoginID") String id);
}
