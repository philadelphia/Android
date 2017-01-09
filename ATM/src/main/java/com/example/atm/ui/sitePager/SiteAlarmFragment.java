package com.example.atm.ui.sitePager;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ScrollView;

import com.example.atm.R;
import com.example.atm.adapter.MyAdapter;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.entities.SiteAlarmData;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.ListviewUtlis;
import com.example.atm.utils.MyRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SiteAlarmFragment extends Fragment implements OnClickListener {
    private ListView lvatm1, lvRemoval, lvSilentZone;
    private ImageButton ibtnPre;
    private ImageButton ibtnNext;
    private TextView removalText;
    private TextView silentText;
    private TextView emptyView1;
    //	private Boolean isHasNextData = true;
//	private Boolean isHasPreviousData = true;
    private Boolean isNoData = false;
    private String loginID;
    private String siteID;
    // 得到当前的SiteInfoID
    private int siteInfoID;


    private List<SiteAlarmData.SiteSingleDataBean> singleDataList = new ArrayList<>();
    private List<SiteAlarmData.SiteCurrentDataBean> currentDataList;

    private List<String> mSecurityatm1List = new ArrayList<String>();
    private List<String> mSecurityatm2List = new ArrayList<String>();
    private List<String> mRemovalList = new ArrayList<String>();
    private List<String> mSilentList = new ArrayList<String>();
    private static final String TAG = "SiteAlarmFragment";
    private TextView text_current_time;
    private Context context;

    private BaseAdapter atm1Adapter;
    private BaseAdapter atm2Adapter;
    private BaseAdapter removalAdapter;
    private BaseAdapter silentAdapter;


    private Boolean hasNoPreData = false;
    private Boolean hasNoNextData = false;
    private static ProgressDialog dialog;
    private ScrollView scrollView;
    private Call<SiteAlarmData> alarmInfo;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View contentView = inflater.inflate(R.layout.fragment_site_alarm,
                container, false);
        context = getContext();
//		loginID =
        siteID = getArguments().getString(Constatnts.SITEID);
        Log.i(TAG, "siteID: " + siteID);
        initView(contentView);
        loadData("drc", siteID, null, "AlarmPage");
        return contentView;
    }

    private void initView(View contentView) {
        Log.i(TAG, "initView");
        scrollView = (ScrollView) contentView.findViewById(R.id.ll01);
        lvatm1 = (ListView) contentView.findViewById(R.id.listatm1);
        emptyView1 = (TextView) contentView.findViewById(R.id.atmlistempty1);
        removalText = (TextView) contentView
                .findViewById(R.id.removal_zone_noAlarms);
        lvRemoval = (ListView) contentView.findViewById(R.id.listRemovalZone);
        silentText = (TextView) contentView
                .findViewById(R.id.silent_zone_noAlarms);
        lvSilentZone = (ListView) contentView.findViewById(R.id.listSilentZone);
        ibtnPre = (ImageButton) contentView.findViewById(R.id.ibt_previous);
        ibtnNext = (ImageButton) contentView.findViewById(R.id.ibt_next);
        text_current_time = (TextView) contentView
                .findViewById(R.id.text_current_time);

        ibtnNext.setOnClickListener(this);
        ibtnPre.setOnClickListener(this);

        lvatm1.setAlpha(1.0f);
        lvatm1.setEmptyView(emptyView1);
        atm1Adapter = new MyAdapter(mSecurityatm1List, context);
        lvatm1.setAdapter(atm1Adapter);
        ListviewUtlis.setListViewHeightBasedOnChildren(lvatm1);

        //Removal Listview
        lvRemoval.setAlpha(1.0f);
        lvRemoval.setEmptyView(removalText);
        removalAdapter = new MyAdapter(mRemovalList, context);
        lvRemoval.setAdapter(removalAdapter);
        ListviewUtlis.setListViewHeightBasedOnChildren(lvRemoval);
        //silent Zone
        lvSilentZone.setAlpha(1.0f);
        lvSilentZone.setEmptyView(silentText);
        silentAdapter = new MyAdapter(mSilentList, context);
        lvSilentZone.setAdapter(silentAdapter);
        ListviewUtlis.setListViewHeightBasedOnChildren(lvSilentZone);


    }

    private void loadData(String loginID, String siteID, String siteInfoID, final String path) {
        dialog = new ProgressDialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Retrofit retrofit = MyRetrofit.initRetrofit();
        ApiClient apiClient = retrofit.create(ApiClient.class);
        alarmInfo = apiClient.getAlarmInfo(loginID, siteID, siteInfoID, path);
        alarmInfo.enqueue(new Callback<SiteAlarmData>() {
            @Override
            public void onResponse(Call<SiteAlarmData> call, Response<SiteAlarmData> response) {
                if (response.body() != null) {
                    showData(response.body(), path);
                }
            }

            @Override
            public void onFailure(Call<SiteAlarmData> call, Throwable t) {

            }
        });
    }

    private void showData(SiteAlarmData body, String path) {
        dialog.dismiss();
        singleDataList.clear();
        if (body.getSiteCurrentData()!=null) {
            singleDataList.addAll(body.getSiteSingleData());
            mSecurityatm1List.clear();
            mRemovalList.clear();
            mSilentList.clear();

            SiteAlarmData.SiteSingleDataBean singleData = singleDataList.get(0);
            String time = singleData.getTime();
            text_current_time.setText(time);
            siteInfoID = singleData.getSiteInfoID();
            currentDataList = body.getSiteCurrentData();
            Log.i(TAG, "currentDataList: " + currentDataList.size());
            for (SiteAlarmData.SiteCurrentDataBean siteCurrentData : currentDataList) {
                if (siteCurrentData.getZone().equalsIgnoreCase("Removal")) {
                    mRemovalList.add(siteCurrentData.getAlarmName());
                } else if (siteCurrentData.getZone().equalsIgnoreCase(
                        "Silent")) {
                    mSilentList.add(siteCurrentData.getAlarmName());
                } else if (siteCurrentData.getZone().equalsIgnoreCase(
                        "Security")) {
                    mSecurityatm1List.add(siteCurrentData
                            .getAlarmName().replace("ATM1", ""));
                }
            }

            Log.i(TAG, "ATM1'List length :" + mSecurityatm1List.size() + "");
            Log.i(TAG, "mRemovalList length :" + mRemovalList.size() + "");
            Log.i(TAG, "mSilentList length :" + mSilentList.size() + "");
            updataData();
        } else if (body.getMessage().equalsIgnoreCase("The request is invalid.".trim())){
            if ("AlarmPreviousPage".equalsIgnoreCase(path)) {
              ibtnPre.setImageResource(R.mipmap.btn_left_no_alarm);
			  ibtnPre.setClickable(false);


            } else if ("AlarmNextPage".equalsIgnoreCase(path)) {
                ibtnNext.setImageResource(R.mipmap.btn_right_no_alarm);
                ibtnNext.setClickable(false);

            } else {
                ibtnNext.setImageResource(R.mipmap.ic_previous);
                ibtnNext.setClickable(true);
            }
        }
    }

    public void updataData() {
        Log.i(TAG, "updataData");
        scrollView.scrollTo(0, 0);
        atm1Adapter.notifyDataSetChanged();
        ListviewUtlis.setListViewHeightBasedOnChildren(lvatm1);

        removalAdapter.notifyDataSetChanged();
        ListviewUtlis.setListViewHeightBasedOnChildren(lvRemoval);

        silentAdapter.notifyDataSetChanged();
        ListviewUtlis.setListViewHeightBasedOnChildren(lvSilentZone);

    }

    @Override
    public void onClick(View v) {
        String siteinfoid = String.valueOf(siteInfoID);
        switch (v.getId()) {
            case R.id.ibt_previous:
                loadData("drc", siteID, siteinfoid, "AlarmPreviousPage");
                Log.i(TAG, "Previous----siteinfoid=== " + siteinfoid);
                ibtnNext.setImageResource(R.mipmap.ic_next);
                ibtnNext.setClickable(true);
                break;
            case R.id.ibt_next:
                loadData("drc", siteID, siteinfoid, "AlarmNextPage");
                Log.i(TAG, "NEXT----siteinfoid" + siteinfoid);
                ibtnPre.setImageResource(R.mipmap.ic_previous);
                ibtnPre.setClickable(true);
                break;
        }
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        // TODO Auto-generated method stub
        super.onAttach(context);
        Log.i(TAG, "onAttach");
    }

    @Override
    public void onDetach() {
        // TODO Auto-generated method stub
        Log.i(TAG, "onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}