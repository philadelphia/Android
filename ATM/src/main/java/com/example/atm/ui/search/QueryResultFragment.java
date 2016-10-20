package com.example.atm.ui.search;

import java.util.ArrayList;
import java.util.List;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.adapter.RecyclerViewAdapter;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.apiInterface.ApiClientRxJava;
import com.example.atm.bean.SiteData;
import com.example.atm.bean.SiteItem;
import com.example.atm.ui.sitePager.Fragment_SiteItem_ViewPager;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.CustomItemClickListener;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.utils.RxsRxSchedulers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

public class QueryResultFragment extends Fragment implements CustomItemClickListener {
	private static final boolean DEBUG = true;
	private static final String TAG = QueryResultFragment.class.getSimpleName();
	private ImageView iv_star;
	private Context context;
	private View inflate;
	private LinearLayout layout;
	private TextView tvTilte;
	private String productName;
	private String circleName;
	private String clusterName;
	private View view;
	private FragmentManager fragmentManager;
	private static AlertDialog.Builder dialog;
	private int index = 0;
	private RecyclerView mRecyclerView;
	private ProgressDialog loadDialog;
	private List<SiteItem> mSiteList = new ArrayList<>();
	private RecyclerViewAdapter myAdapter;
	private Call<SiteData> queryProductCall;
	private Call<SiteData> queryCircleCall;
	private Call<SiteData> queryClusterCall;
	private Observable<SiteData> siteDataObservable;
	private Observable<SiteData> siteDataCircleObservable;
	private Observable<SiteData> siteDataClusterObservable;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		Log.i(TAG, "onCreate(): ");
		super.onCreate(savedInstanceState);
		context = getContext();
		loadDialog = new ProgressDialog(context);
		fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
		dialog = new AlertDialog.Builder(context);
		dialog.setMessage("No matched resultes").setPositiveButton("OK", new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				getActivity().onBackPressed();
			}
		});

		loadDialog.setMessage("Loading...");
		loadDialog.show();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView ");

		inflate = inflater.inflate(R.layout.fragment_searchresult_list, container, false);
		layout = (LinearLayout) inflate.findViewById(R.id.header);
		tvTilte = (TextView) inflate.findViewById(R.id.keywords);
		view = inflate.findViewById(R.id.devider);
		tvTilte.setVisibility(View.GONE);
		mRecyclerView = (RecyclerView) inflate.findViewById(R.id.list_site);
		myAdapter = new RecyclerViewAdapter(getContext(), mSiteList);
//        myAdapter.setOnCustomeItemClickListener(this);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
		mRecyclerView.setAdapter(myAdapter);

		return inflate;

	}

	@Override
	public void onResume() {
		super.onResume();
		Bundle arg = getArguments();
		if (arg != null) {
			Log.i("argu", arg.toString());
		}
		Log.i(TAG, "onResume: bundle ==" + getArguments().toString());
		int productID = 0;
		int circleID = 0;
		int clusterID = 0;
		if (arg.containsKey(Constatnts.SelectedProductID)) {
			productID = arg.getInt(Constatnts.SelectedProductID);
			productName = arg.getString(Constatnts.SelectedProductName);
		}
		if (TextUtils.isEmpty(productName)) {
			productName = "N/A";
		}
		if (arg.containsKey(Constatnts.SelectedCircleID)) {
			circleID = arg.getInt(Constatnts.SelectedCircleID);
			circleName = arg.getString(Constatnts.SelectedCircleName);
		} else {
			circleName = "N/A";
		}

		if (arg.containsKey(Constatnts.SelectedClusterID)) {
			clusterID = arg.getInt(Constatnts.SelectedClusterID);
			clusterName = arg.getString(Constatnts.SelectedClusterName);
		} else {
			clusterName = "N/A";
		}
		Log.i(TAG, "onResume: begin to fetch");
//        showQueryResult ("drc",productID, circleID, clusterID);
		showQueryResultByRxJava("drc", productID, circleID, clusterID);

		// ensure the appbar layout is visible after visiting site pages
		AppBarLayout appBarLayout = MainActivity.getAppBarLayout();
		appBarLayout.setExpanded(true, true);
	}

	private void showQueryResultByRxJava(String loginID, int productid, int circleid, int clusterid) {
		Log.i(TAG, "showQueryResult: ");
		Log.i(TAG, "productid: " + productid);
		Log.i(TAG, "circleid: " + circleid);
		Log.i(TAG, "clusterid: " + clusterid);
		layout.setVisibility(View.VISIBLE);
		tvTilte.setVisibility(View.VISIBLE);
		tvTilte.setText("Applied filters:" + "\r\n" + " - Product: " + productName + "\n" + "\r" + "- Cirlce: " + circleName + "\n\r"
				+ "- Cluster: " + clusterName);
		view.setVisibility(View.VISIBLE);
		Log.i(TAG, "showQueryResult was Called" + productid + circleid + clusterid);
		MainActivity.setActionBarTitle(getString(R.string.Query_title), null);
		ApiClientRxJava apiClient = MyRetrofit.getInstance().create(ApiClientRxJava.class);

		if (productid != 0 && clusterid == 0 && circleid == 0) {
			Log.i(TAG, "1 parameters");
			Log.i(TAG, "productid==:  " + productid);
			siteDataObservable = apiClient.queryProduct(loginID, String.valueOf(productid));
			siteDataObservable.compose(RxsRxSchedulers.io_main()).subscribe(new Subscriber<SiteData>() {
				@Override
				public void onCompleted() {
					Log.i(TAG, "onCompleted: ");
				}

				@Override
				public void onError(Throwable e) {
					Log.i(TAG, "onError: ");
					loadDialog.dismiss();
				}

				@Override
				public void onNext(SiteData siteData) {
					Log.i(TAG, "onNext: ");

					loadDialog.dismiss();
					mSiteList.clear();
					mSiteList.addAll(siteData.getSiteData());
					Log.i(TAG, "mSiteList: size == " + mSiteList.size());
					myAdapter.notifyDataSetChanged();
					if (mSiteList.size() == 0) {
						dialog.show();
					}
				}
			});

		} else if (productid != 0 && circleid != 0 && clusterid == 0) {
			Log.i(TAG, "2 parameters");
			siteDataCircleObservable = apiClient.queryCircle(loginID, String.valueOf(productid), String.valueOf(circleid));
			siteDataCircleObservable.compose(RxsRxSchedulers.io_main())
					.subscribe(new Subscriber<SiteData>() {
						@Override
						public void onCompleted() {
							Log.i(TAG, "onCompleted: ");
						}

						@Override
						public void onError(Throwable e) {
							Log.i(TAG, "onError: ");
							loadDialog.dismiss();
						}

						@Override
						public void onNext(SiteData siteData) {
							Log.i(TAG, "onNext: ");
							mSiteList.clear();
							loadDialog.dismiss();
							mSiteList.addAll(siteData.getSiteData());
							Log.i(TAG, "mSiteList: size == " + mSiteList.size());
							myAdapter.notifyDataSetChanged();
							if (mSiteList.size() == 0) {
								dialog.show();
							}
						}
					});

		} else if (productid != 0 && circleid != 0 && clusterid != 0) {
			Log.i(TAG, "3 parameters");
			siteDataClusterObservable = apiClient.queryCluster(loginID, String.valueOf(productid), String.valueOf(circleid), String.valueOf(clusterid));
			siteDataClusterObservable.compose(RxsRxSchedulers.io_main()).subscribe(new Subscriber<SiteData>() {
				@Override
				public void onCompleted() {
					Log.i(TAG, "onCompleted: ");
				}

				@Override
				public void onError(Throwable e) {
					Log.i(TAG, "onError: ");
					loadDialog.dismiss();
				}

				@Override
				public void onNext(SiteData siteData) {
					Log.i(TAG, "onNext: ");
					mSiteList.clear();
					loadDialog.dismiss();
					mSiteList.addAll(siteData.getSiteData());
					Log.i(TAG, "mSiteList: size == " + mSiteList.size());
					myAdapter.notifyDataSetChanged();
					if (mSiteList.size() == 0) {
						dialog.show();
					}
				}
			});
		}

	}
	private void showQueryResult (String loginID,int productid, int circleid, int clusterid){
        Log.i(TAG, "showQueryResult: ");
        Log.i(TAG, "productid: " + productid);
        Log.i(TAG, "circleid: " + circleid);
        Log.i(TAG, "clusterid: " + clusterid);
        layout.setVisibility (View.VISIBLE);
		tvTilte.setVisibility (View.VISIBLE);
		tvTilte.setText ("Applied filters:" + "\r\n" + " - Product: " + productName + "\n" + "\r" + "- Cirlce: " + circleName + "\n\r"
				+ "- Cluster: " + clusterName);
		view.setVisibility (View.VISIBLE);
		Log.i (TAG, "showQueryResult was Called" + productid + circleid + clusterid);
		MainActivity.setActionBarTitle (getString (R.string.Query_title),null);
		ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);

		if (productid != 0 && clusterid == 0 && circleid == 0)
		{
			Log.i (TAG, "1 parameters");
            Log.i(TAG, "productid==:  " +productid);
            queryProductCall = apiClient.queryProduct(loginID, String.valueOf(productid));
            queryProductCall.enqueue(new Callback<SiteData>() {
				@Override
				public void onResponse(Call<SiteData> call, Response<SiteData> response) {
					if (HttpCallUtil.isResponseValid(response)){
                            loadDialog.dismiss();
                            mSiteList.clear();
                            mSiteList.addAll(response.body().getSiteData());
                            Log.i(TAG, "mSiteList: size == " + mSiteList.size());
							myAdapter.notifyDataSetChanged();
                        if (mSiteList.size () == 0){
                            dialog.show ();
                            loadDialog.dismiss();
                        }
					}
				}

				@Override
				public void onFailure(Call<SiteData> call, Throwable t) {
                    loadDialog.dismiss();
				}
			});

		}
		else if (productid != 0 && circleid != 0 && clusterid == 0)
		{
			Log.i (TAG, "2 parameters");
            queryCircleCall = apiClient.queryCircle(loginID, String.valueOf(productid), String.valueOf(circleid));
            queryCircleCall.enqueue(new Callback<SiteData>() {
                @Override
                public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                    Log.i(TAG, "onResponse: " + response);
                    if (HttpCallUtil.isResponseValid(response)) {

                            mSiteList.clear();
                            loadDialog.dismiss();
                            mSiteList.addAll(response.body().getSiteData());
                            Log.i(TAG, "mSiteList: size == " + mSiteList.size());
                            myAdapter.notifyDataSetChanged();
                        if (mSiteList.size() == 0) {
                            dialog.show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SiteData> call, Throwable t) {
                    loadDialog.dismiss();
                    Log.i(TAG, "onFailure: ");
                }
            });

		}
		else if (productid != 0 && circleid != 0 && clusterid != 0)
		{
			Log.i (TAG, "3 parameters");
            queryClusterCall = apiClient.queryCluster(loginID, String.valueOf(productid), String.valueOf(circleid), String.valueOf(clusterid));
            queryClusterCall.enqueue(new Callback<SiteData>() {
                @Override
                public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                    if (HttpCallUtil.isResponseValid(response)) {
                            mSiteList.clear();
                            loadDialog.dismiss();
                            mSiteList.addAll(response.body().getSiteData());
                            Log.i(TAG, "mSiteList: size == " + mSiteList.size());
                            myAdapter.notifyDataSetChanged();
                        if (mSiteList.size() == 0) {
                            dialog.show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<SiteData> call, Throwable t) {
                    loadDialog.dismiss();
                }
            });
        }


	}

	@Override
	public void onPause () {
		super.onPause ();

	}

	public void onStop ()
	{
		super.onStop ();
	}

	@Override
	public void onAttach (Context context)
	{
		super.onAttach (context);
		Log.i (TAG, "onAttach");
	}

	@Override
	public void onDetach (){
		super.onDetach ();
		Log.i (TAG, "onDetach");
	}

	@Override
	public void onDestroy () {
		super.onDestroy ();
		Log.i (TAG, "onDestroy");
        HttpCallUtil.cancelCall(queryProductCall);
        HttpCallUtil.cancelCall(queryCircleCall);
        HttpCallUtil.cancelCall(queryClusterCall);

	}

    @Override
    public void onItemClick(View v, int position) {
        Log.i(TAG, "onItemClick: " + position);
        Fragment_SiteItem_ViewPager fragment = new Fragment_SiteItem_ViewPager();
        Bundle args = new Bundle();
        SiteItem siteItem = mSiteList.get(position);
        args.putString(Constatnts.SITEID, siteItem.getSiteID().trim());
        args.putString(Constatnts.SITENAME, siteItem.getSiteName().trim());
        args.putBoolean(Constatnts.SITEFLAG, siteItem.isFlag());
        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onItemLongClick(View v, int position) {

    }
}
