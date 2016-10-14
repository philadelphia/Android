package com.example.atm.ui.sitePager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.adapter.RecyclerViewAdapter;
import com.example.atm.adapter.SiteInfoAdapter;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.SiteInfoData;
import com.example.atm.entities.SiteInfo;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SiteInfoFragment extends Fragment {
	private static final String TAG = "SiteInfoFragment";
	private String siteName;
	private String Flag;
	private ListView mSiteListview;

	private SiteInfoAdapter mSiteInfoAdapter;
	private AlertDialog.Builder builder;
	private String LoginID = null;
	private String siteId;
	private SharedPreferences preferences;
	private Call<SiteInfoData> siteInformation;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_site_info, container,
				false);
		mSiteListview = (ListView) view.findViewById(R.id.sitelist);
		Bundle bundle = getArguments();
		builder = new AlertDialog.Builder(getActivity());
		siteId = bundle.getString(Constatnts.SITEID);
		siteName = bundle.getString(Constatnts.SITENAME);
		Log.i(TAG,  siteId);
		Log.i(TAG,  siteName);
		preferences = getActivity().getSharedPreferences("config",
				Context.MODE_PRIVATE);
		LoginID = preferences.getString("LoginID", "drc");
		MainActivity.setActionBarTitle(siteName, siteId);

		//MyNetworkStatus.getdialog(getContext());
		//MyNetworkStatus.showLoading(true);
////		MyStringRequest strRequest = new MyStringRequest(Method.POST,
////				Url.SITEINFO_URL, new Listener<String>() {
//					@Override
//					public void onResponse(String response) {
//
//						Log.i("info", "site  info:" + response);
//						if ("Message".equals(response.substring(2, 9))) {
//							builder.setMessage(
//									"Site ID does not match! or Login ID does not match!")
//									.setPositiveButton("OK", null).show();
//						} else {
//							try {
//
//								JSONArray jsonArray = new JSONObject(response)
//										.getJSONArray("SiteInfoData");
//								String s = jsonArray.toString();
//								String str = s.substring(1, s.length() - 1);
//								siteInfo = GsonTools.changeGsonToBean(str,
//										SiteInfo.class);
//								ArrayList<String> title = new ArrayList<String>();
//								title.add("Active alarms");
//								title.add("Site ID");
//								title.add("Site name");
//								title.add("SIM NO.");
//								title.add("Longitude");
//								title.add("Latitude");
//								title.add("Address");
//								ArrayList<String> wordsArrayList = new ArrayList<>();
//								wordsArrayList.add("Critical:"
//										+ siteInfo.getCritical() + "   Major:"
//										+ siteInfo.getMajor() + "   Minor:"
//										+ siteInfo.getMinor());
//								wordsArrayList.add(siteInfo.getSiteID());
//								Log.i("info", "name"
//										+ siteInfo.getSiteName()
//												.substring(0, 1));
//								if (" ".equals(siteInfo.getSiteName()
//										.substring(0, 1))) {
//									wordsArrayList.add(siteInfo.getSiteName()
//											.substring(
//													1,
//													siteInfo.getSiteName()
//															.length()));
//								} else {
//									wordsArrayList.add(siteInfo.getSiteName());
//								}
//								String simno = null;
//								if ("".equals(siteInfo.getSimNo())) {
//									simno = "0";
//								} else {
//									simno = siteInfo.getSimNo();
//								}
//								wordsArrayList.add(simno);
//								wordsArrayList.add(siteInfo.getLongitude());
//								wordsArrayList.add(siteInfo.getLattitude());
//								wordsArrayList.add(siteInfo.getAddress());
//								mSiteInfoAdapter = new SiteInfoAdapter(
//										getActivity(), title, wordsArrayList);
//								mSiteListview.setAdapter(mSiteInfoAdapter);
//				//				MyNetworkStatus.showLoading(false);
//							}
//						}
//					}
//				}, new ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//
//						}
//					}
//				});
//
//
		return view;
	}

	public void fetSiteInformation(String loginID, String siteID){
		Retrofit retrofit = MyRetrofit.initRetrofit();
		ApiClient apiClient = retrofit.create(ApiClient.class);
		siteInformation = apiClient.getSiteInformation(loginID, siteID);
		siteInformation.enqueue(new Callback<SiteInfoData>() {
			@Override
			public void onResponse(Call<SiteInfoData> call, Response<SiteInfoData> response) {
				Log.i(TAG, "onResponse: ");
				if (response.body() != null){
					Log.i(TAG, "onResponse:body=== "+response.body().toString());
					Log.i(TAG, "onResponse: " + response.body().getSiteInfoData().get(0).toString());
					SiteInfoData siteInfoData = response.body();
					Log.i(TAG, "onResponse:siteInfoData ===  " +siteInfoData.toString());

					SiteInfoData.SiteInfoDataBean siteInfo = response.body().getSiteInfoData().get(0);
					showSiteInformation(siteInfo);
				}
			}

			@Override
			public void onFailure(Call<SiteInfoData> call, Throwable t) {
				Log.i(TAG, "onFailure: ");
			}
		});
	}

	public void  showSiteInformation(SiteInfoData.SiteInfoDataBean siteInfomation){
		ArrayList<String> title = new ArrayList<String>();
								title.add("Active alarms");
								title.add("Site ID");
								title.add("Site name");
								title.add("SIM NO.");
								title.add("Longitude");
								title.add("Latitude");
								title.add("Address");
								ArrayList<String> wordsArrayList = new ArrayList<>();
								wordsArrayList.add("Critical:"
										+ siteInfomation.getCritical() + "   Major:"
										+ siteInfomation.getMajor() + "   Minor:"
										+ siteInfomation.getMinor());
								wordsArrayList.add(siteInfomation.getSiteID());
								Log.i("info", "name"
										+ siteInfomation.getSiteName()
												.substring(0, 1));
								if (" ".equals(siteInfomation.getSiteName()
										.substring(0, 1))) {
									wordsArrayList.add(siteInfomation.getSiteName()
											.substring(
													1,
													siteInfomation.getSiteName()
															.length()));
								} else {
									wordsArrayList.add(siteInfomation.getSiteName());
								}
								String simno = null;
								if ("".equals(siteInfomation.getSimNo())) {
									simno = "0";
								} else {
									simno = siteInfomation.getSimNo();
								}
								wordsArrayList.add(simno);
								wordsArrayList.add(siteInfomation.getLongitude());
								wordsArrayList.add(siteInfomation.getLattitude());
								wordsArrayList.add(siteInfomation.getAddress());
								mSiteInfoAdapter = new SiteInfoAdapter(
										getActivity(), title, wordsArrayList);
								mSiteListview.setAdapter(mSiteInfoAdapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		fetSiteInformation(LoginID,siteId);
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		HttpCallUtil.cancelCall(siteInformation);
	}
}
