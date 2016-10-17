package com.example.atm.ui.siteMap;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atm.MainActivity;
import com.example.atm.R;

import com.example.atm.apiInterface.ApiClient;

import com.example.atm.entities.SiteMap;
import com.example.atm.ui.sitePager.Fragment_SiteItem_ViewPager;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SiteMapFragment extends Fragment {
    private MapView mMap;
    private GoogleMap googleMap;
    private Bundle args = null;
    private ProgressDialog mDialog;
    private String LoginID = null;
    private SharedPreferences preferences;
    private List<SiteMap.SiteMapDataBean> mSiteMaps;
    private AlertDialog.Builder builder;
    private static final String TAG = SiteMapFragment.class.getSimpleName();
    private Call<SiteMap> allSiteMapInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_site_map, container, false);
        preferences = getActivity().getSharedPreferences("config",
                Context.MODE_PRIVATE);
        LoginID = preferences.getString("LoginID", null);
		builder = new AlertDialog.Builder(getActivity());
        mMap = (MapView) view.findViewById(R.id.mapview);
        mMap.onCreate(savedInstanceState);
        mMap.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int errorCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this.getActivity());

        if (ConnectionResult.SUCCESS != errorCode) {
            GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(), 0).show();
        } else {
            googleMap = mMap.getMap();
            if (googleMap != null) {
                fetchSiteMapInfo("drc");
                LatLng india = new LatLng(23, 77);

                googleMap.setMyLocationEnabled(true);
                googleMap.moveCamera(CameraUpdateFactory
                        .newLatLngZoom(india, 5));
            }
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated: ");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setActionBarTitle(getString(R.string.title_site_map), null);
        mMap.onResume();
        Log.i(TAG, "onResume: ");

        // ensure the appbar layout is visible after visiting site pages
        MainActivity.getAppBarLayout().setExpanded(true, true);
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach: ");
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMap.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
        mMap.onDestroy();
        HttpCallUtil.cancelCall(allSiteMapInfo);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMap.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }


    public void fetchSiteMapInfo(String loginID) {
        Log.i(TAG, "fetchSiteMapInfo: ");
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Loading...");
        mDialog.show();
        allSiteMapInfo = MyRetrofit.initRetrofit().create(ApiClient.class).getAllSiteMapInfo(loginID);
        allSiteMapInfo.enqueue(new Callback<SiteMap>() {
            @Override
            public void onResponse(Call<SiteMap> call, Response<SiteMap> response) {
                mDialog.dismiss();
                if (HttpCallUtil.isResponseValid(response)) {
                    mSiteMaps = response.body().getSiteMapData();
                    setData(mSiteMaps);
                }
            }


            @Override
            public void onFailure(Call<SiteMap> call, Throwable t) {
                mDialog.dismiss();
            }

        });

    }

    public void setData(List<SiteMap.SiteMapDataBean> siteMaps) {
        Log.i(TAG, "setData: ");
        for (int i = 0; i < siteMaps.size(); i++) {
            Log.i("info", "for" + siteMaps.size());
            if ("Yes".equals(siteMaps.get(i).getIsAlarm())) {
                if (siteMaps.get(i).getLattitude().contains("°")
                        || siteMaps.get(i).getLongitude().contains("°")) {
                    Log.i(TAG, "Lattitude" + siteMaps.get(i).getLattitude());
                    Log.i(TAG, "Longitude" + siteMaps.get(i).getLongitude());
                } else if (siteMaps.get(i).getLongitude().contains("0")
                        || siteMaps.get(i).getLongitude().contains("0")) {
                    Log.i(TAG, siteMaps.get(i).getSiteName());
                } else {
                    double Lattitude = Double.valueOf(siteMaps.get(i).getLattitude());
                    Log.i(TAG, "forLattitude" + Lattitude);
                    double Longitude = Double.valueOf(siteMaps.get(i).getLongitude());
                    Log.i(TAG, "forLonggitude"
                            + Longitude);
                    googleMap.addMarker(new MarkerOptions().position(
                            new LatLng(Lattitude, Longitude))
                            .title(siteMaps.get(i).getSiteName())
                            .snippet(siteMaps.get(i).getSiteID()));
                }
            } else {
                if (siteMaps.get(i).getLattitude().contains("°")
                        || siteMaps.get(i).getLongitude().contains("°")) {
                    Log.i(TAG, "Lattitude" + siteMaps.get(i).getLattitude());
                    Log.i(TAG, "Longitude" + siteMaps.get(i).getLongitude());
                } else if (siteMaps.get(i).getLongitude().contains("0")
                        || siteMaps.get(i).getLongitude().contains("0")) {
                    Log.i("info", siteMaps.get(i)
                            .getSiteName());
                } else {
                    double Lattitude = Double.valueOf(siteMaps.get(i).getLattitude());
                    Log.i(TAG, "forLattitude" + Lattitude);
                    double Longitude = Double.valueOf(siteMaps.get(i).getLongitude());
                    Log.i(TAG, "forLonggitude" + Longitude);
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(Lattitude, Longitude))
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                            .title(siteMaps.get(i).getSiteName())
                            .snippet(siteMaps.get(i).getSiteID()));
                    googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                                @Override
                                public void onInfoWindowClick(
                                        Marker marker) {
                                    args = new Bundle();
                                    args.putString(Constatnts.SITEID, marker.getSnippet());
                                    Log.i(TAG, "key_site_name" + marker.getSnippet());
                                    args.putString(Constatnts.SITENAME, marker.getTitle());
                                    Log.i(TAG, "key_site_name" + marker.getTitle());
                                    FragmentManager fragmentManager = ((FragmentActivity) getActivity())
                                            .getSupportFragmentManager();
                                    Fragment_SiteItem_ViewPager fragment = new Fragment_SiteItem_ViewPager();
                                    fragmentManager.beginTransaction().
                                            replace(R.id.container, fragment)
                                            .addToBackStack(null)
                                            .commit();
                                    fragment.setArguments(args);
                                }
                            });
                }
            }
        }
    }
}