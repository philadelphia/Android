package com.example.atm.ui.sitelist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.atm.apiInterface.ApiClientRxJava;
import com.example.atm.ui.search.SearchFragment;
import com.example.atm.ui.sitePager.Fragment_SiteItem_ViewPager;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.adapter.RecyclerViewAdapter;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.SiteData;
import com.example.atm.bean.SiteItem;
import com.example.atm.utils.CustomItemClickListener;
import com.example.atm.utils.RxsRxSchedulers;
import com.example.atm.utils.SiteListSortUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


@SuppressLint("DefaultLocale")
public class SiteListFragment extends Fragment implements CustomItemClickListener, OnRefreshListener {
    private static final boolean DEBUG = true;
    private static final String TAG = "SiteListFragment";
    private static final String PREF_KEY_SITE_LIST = "pref_site_list";

    private FragmentManager fragmentManager;
    @BindView(R.id.list_site)
    RecyclerView mRecyclerView;
    @BindView(R.id.SwipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private SharedPreferences mSharedPreferences;
    private SharedPreferences mSP_siteListRefreshTime;
    private Context context;
    private RecyclerViewAdapter myAdapter;
    private List<SiteItem> mSiteList = new ArrayList<>();
    private Call<SiteData> allSites;
    private Observable<SiteData> allSitesRxjava;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        super.onCreateView(inflater, container, savedInstanceState);
        context = getContext();
        View view = inflater.inflate(R.layout.fragment_site_list, container,false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        myAdapter = new RecyclerViewAdapter(getContext(), mSiteList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
//       mRecyclerView.addItemDecoration();
        mRecyclerView.setAdapter(myAdapter);

        myAdapter.setOnCustomeItemClickListener(this);
//        fetchSiteList("drc");
        fetchSiteListByRxJava("drc");

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Log.i(TAG, "onOptionsItemSelected: ");
                jumpToSearchFragment();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchSiteList(String loginID) {
        Log.i(TAG, "fetchSiteList: ");
        ApiClient siteList = MyRetrofit.initRetrofit().create(ApiClient.class);

        allSites = siteList.getAllSites(loginID);
        allSites.enqueue(new Callback<SiteData>() {
            @Override
            public void onResponse(Call<SiteData> call, Response<SiteData> response) {
                if(response.body() != null){
                    Log.i(TAG, "onResponse: ");
                    Log.i(TAG, "onResponse: "+response.body().toString());
                    mSwipeRefreshLayout.setRefreshing(false);
                    mSiteList.clear();
                    mSiteList.addAll(SiteListSortUtil.sortList(response.body().getSiteData()));
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SiteData> call, Throwable t) {
                Log.i(TAG, "onFailure: ");
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    
    public  void fetchSiteListByRxJava(String loginID){
        Log.i(TAG, "fetchSiteListByRxJava: ");
        ApiClientRxJava apiClientRxJava = MyRetrofit.initRetrofit().create(ApiClientRxJava.class);
        allSitesRxjava = apiClientRxJava.getAllSites(loginID);
        allSitesRxjava.compose(RxsRxSchedulers.io_main())
        .subscribe(new Observer<SiteData>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onNext(SiteData siteData) {
                Log.i(TAG, "onNext: ");
                mSwipeRefreshLayout.setRefreshing(false);
                mSiteList.clear();
                mSiteList.addAll(SiteListSortUtil.sortList(siteData.getSiteData()));
                myAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }

       });

    }
    
    @Override
    public void onRefresh() {
        Log.i(TAG, "onRefresh: ");
        mSwipeRefreshLayout.setRefreshing(true);
//        fetchSiteList("drc");
        fetchSiteListByRxJava("drc");
    }
    
    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setActionBarTitle("Site List",null);
        MainActivity.getFloatingActionButton().setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HttpCallUtil.cancelCall(allSites);
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

    public void jumpToSearchFragment(){
        Log.i(TAG, "jumpToSearchFragment: ");
        SearchFragment searchFragment = new SearchFragment();
        fragmentManager.beginTransaction().replace(R.id.container, searchFragment).addToBackStack(null).commit();
    }
}
