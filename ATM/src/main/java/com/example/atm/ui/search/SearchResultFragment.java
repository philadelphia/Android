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
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.adapter.RecyclerViewAdapter;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.SiteData;
import com.example.atm.bean.SiteItem;
import com.example.atm.ui.sitePager.Fragment_SiteItem_ViewPager;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.CustomItemClickListener;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.utils.SiteListSortUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SearchResultFragment extends Fragment implements
        CustomItemClickListener,View.OnClickListener {
	private static final String TAG = "SearchResultFragment";
	private static final String REQUEST_TAG = "SiteList";
	private static final String KEY_SITE_NAME = "key_site_name";
	private static final String KEY_SITE_ID = "key_site_id";
	private static final String KEY_SITE_LIVE_FLAG = "key_site_live_flag";
	private ImageView iv_star;
	private Context context;
	private View view;
	private LinearLayout layout;
    private View divider;
	private TextView tvTilte;
	private ImageButton btn_delete;

	private FragmentManager fragmentManager;
	private List<SiteItem> mSiteList ;
	private static AlertDialog.Builder dialog;
	private int index = 0;
	private RecyclerView mRecyclerView;
	private ProgressDialog loadDialog ;
	private String siteName;
	private RecyclerViewAdapter myAdapter;
    private Bundle arg;
    private Call<SiteData> siteResults;

    @Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		Log.i(TAG, "onCreate(): ");
		super.onCreate(savedInstanceState);
		context = getContext();
        arg = getArguments();
        siteName = arg.getString(Constatnts.SITENAME);
        Log.i(TAG, "onCreate: SITENAME== " + siteName );

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView ");
        view = inflater.inflate(R.layout.fragment_searchresult_list,
                container, false);
        initView(view);
        initData();

		return view;

	}

    public void initView(View view){
        MainActivity.setActionBarTitle("Filter & search",null);
        layout = (LinearLayout) view.findViewById(R.id.header);
        tvTilte = (TextView) view.findViewById(R.id.keywords);
        btn_delete = (ImageButton) view.findViewById(R.id.btn_delete);
        divider = view.findViewById(R.id.devider);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_site);
        layout.setVisibility(View.VISIBLE);
        tvTilte.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
        tvTilte.setHeight((int) getResources().getDimension(
                R.dimen.list_item_height));
        btn_delete.setVisibility(View.VISIBLE);
        tvTilte.setText("Keyword: " + siteName);
        btn_delete.setOnClickListener(this);

        loadDialog = new ProgressDialog(context);
        fragmentManager = ((FragmentActivity) getActivity())
                .getSupportFragmentManager();
        dialog = new AlertDialog.Builder(context);
        dialog.setMessage("No match resultes").setPositiveButton("OK",
                new OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        loadDialog.setMessage("Loading...");
        loadDialog.show();

    }


    private void initData() {
        mSiteList = new ArrayList<SiteItem>();
        myAdapter = new RecyclerViewAdapter(getContext(), mSiteList);
        myAdapter.setOnCustomeItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        mRecyclerView.setAdapter(myAdapter);

    }

    @Override
	public void onResume() {
		super.onResume();
        showSearchResult("drc",siteName);
	}

	private void showSearchResult(String loginID, String siteName)  {
		Retrofit retrofit = MyRetrofit.initRetrofit();
		ApiClient apiClient = retrofit.create(ApiClient.class);
		siteResults = apiClient.getSiteResults(loginID, siteName);
        loadDialog.dismiss();
		siteResults.enqueue(new Callback<SiteData>() {
			@Override
			public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                Log.i(TAG, "onResponse: ");
                Log.i(TAG, "onResponse: " + response.body().toString());
                if (response.body().getSiteData() != null){
                    mSiteList.clear();
                    mSiteList.addAll(SiteListSortUtil.sortList(response.body().getSiteData()));
                    myAdapter.notifyDataSetChanged();
                }else{
                   dialog.show();
                }

			}

			@Override
			public void onFailure(Call<SiteData> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
            }
		});

	}



	public void onStop() {
		super.onStop();
        siteResults.cancel();

	}

	@Override
	public void onAttach(Context context) {
		// TODO Auto-generated method stub
		super.onAttach(context);
		Log.i(TAG, "onAttach");
	}

	@Override
	public void onDetach() {
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
	

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.btn_delete:
			Log.i(TAG, "delete was clicked!");
			this.onDestroyView();
			this.onDestroy();
			this.onDetach();
			getActivity().onBackPressed();
			break;

		default:
			break;
		}
		
	}

    @Override
    public void onItemClick(View v, int position) {
        SiteItem siteItem = mSiteList.get(position);
        iv_star = (ImageView) view.findViewById(R.id.icon);
        iv_star.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                iv_star.setImageDrawable(ContextCompat.getDrawable(context,
                        R.mipmap.ic_star_brown));

            }
        });
        Log.i(TAG, "siteItem :" + siteItem.toString());

        Bundle args = new Bundle();
        args.putString(Constatnts.SITEID, siteItem.getSiteID().trim());
        args.putString(Constatnts.SITENAME, siteItem.getSiteName().trim());
        args.putBoolean(Constatnts.SITEFLAG, siteItem.isFlag());

        Fragment_SiteItem_ViewPager fragment = new Fragment_SiteItem_ViewPager();
        fragment.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onItemLongClick(View v, int position) {

    }
}
