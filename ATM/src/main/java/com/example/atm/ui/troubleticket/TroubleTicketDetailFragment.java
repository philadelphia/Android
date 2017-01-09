package com.example.atm.ui.troubleticket;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.SiteInfoData;
import com.example.atm.bean.TroubleTicket;
import com.example.atm.entities.LoginBean;
import com.example.atm.entities.SiteInfo;
import com.example.atm.ui.sitePager.Fragment_SiteItem_ViewPager;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.utils.Url;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TroubleTicketDetailFragment extends Fragment implements OnClickListener {
    private static final String TAG = "TroubleTicketDetailFrag";
    private View view;
    private FragmentManager mFragmentManager;
    private LinearLayout layoutSiteInfo;
    private TextView tv_troubleTicketID;
    private TextView tv_siteID;
    private TextView tv_siteName;
    private TextView tv_siteAddress;
    private EditText tv_troubleTicketDetail;
    private ImageButton ibt_map;

    private Bundle bundle;
    private Bundle bundleSend;
    private FloatingActionButton fl_pencil;

    private Intent intent;
    private ArrayList<TroubleTicket.TTDataBean> array;

    private String LoginID = null;
    private AlertDialog.Builder builder;
    private SiteInfoData.SiteInfoDataBean siteInfo;
    private Call<SiteInfoData> siteInformation;
    private TroubleTicket.TTDataBean mTroubleticket;

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        view = inflater.inflate(R.layout.fragment_troubleticket_detail,
                container, false);
        mFragmentManager = getActivity().getSupportFragmentManager();
        builder = new AlertDialog.Builder(getActivity());
        bundle = getArguments();
        initView(view);
        mTroubleticket = (TroubleTicket.TTDataBean) bundle.getSerializable("TroubleTicket");
        initData(mTroubleticket);

        return view;
    }

    public void initView(View view) {
        tv_troubleTicketID = (TextView) view
                .findViewById(R.id.txt_trobuleTicketID);
        tv_siteID = (TextView) view.findViewById(R.id.txt_siteId);
        tv_siteName = (TextView) view.findViewById(R.id.txt_siteName);
        tv_siteAddress = (TextView) view.findViewById(R.id.txt_siteAddress);
        tv_troubleTicketDetail = (EditText) view
                .findViewById(R.id.txt_troubleTicketDetail);

        layoutSiteInfo = (LinearLayout) view.findViewById(R.id.siteInfo);
        ibt_map = (ImageButton) view.findViewById(R.id.ibt_map);
        fl_pencil = MainActivity.getFloatingActionButton();
        fl_pencil.setImageResource(R.mipmap.ic_pencil);
        fl_pencil.setVisibility(View.VISIBLE);

        // 点击事件
        layoutSiteInfo.setOnClickListener(this);
        ibt_map.setOnClickListener(this);
        fl_pencil.setOnClickListener(this);
    }

    public void initData(TroubleTicket.TTDataBean troubleTicket) {
        tv_troubleTicketID.setText(troubleTicket.getTTNo());
        tv_siteID.setText(troubleTicket.getSiteID());
        tv_siteName.setText(troubleTicket.getSiteName());
        tv_siteAddress.setText(troubleTicket.getAddress());
        String ttDesc = troubleTicket.getTTDesc();
        tv_troubleTicketDetail.setText(TextUtils.isEmpty(ttDesc) ? ttDesc : ttDesc);

    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
        MainActivity.setActionBarTitle(getString(R.string.title_trouble_ticket_Detail), null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.siteInfo:
                Fragment_SiteItem_ViewPager fragment = new Fragment_SiteItem_ViewPager();
                Bundle bundle = new Bundle();
                bundle.putString("key_site_id", mTroubleticket.getSiteID());
                bundle.putString("key_site_name", mTroubleticket.getSiteName());
                fragment.setArguments(bundle);
                Log.i(TAG, bundle.toString());
                mFragmentManager.beginTransaction()
                        .replace(R.id.container, fragment).addToBackStack(null)
                        .commit();
                break;
            case R.id.ibt_map:
                String address = "http://ditu.google.cn/maps?hl=zh&mrt=loc&q="
                        + mTroubleticket.getLongitude() + "," + mTroubleticket.getLattitude() + "(" + mTroubleticket.getSiteName() + ")";
                intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(address));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                intent.setClassName("com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity");
                startActivity(intent);
                break;
            case R.id.fab:
                Fragment fragmentEdit = new TroubleTicketEditFragment();
                bundleSend = new Bundle();
                bundleSend.putString("ttid", tv_troubleTicketID.getText()
                        .toString().trim());
                bundleSend.putString("ttSummary", tv_siteID.getText().toString()
                        .trim());
                bundleSend.putString("ttDetailedInfo", tv_siteName.getText()
                        .toString().trim());
                bundleSend.putString("sID", tv_troubleTicketDetail.getText()
                        .toString().trim());
                fragmentEdit.setArguments(bundleSend);
                mFragmentManager.beginTransaction()
                        .replace(R.id.container, fragmentEdit).addToBackStack(null)
                        .commit();

                break;
            default:
                break;
        }

    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView: ");
        super.onDestroyView();
    }
}
