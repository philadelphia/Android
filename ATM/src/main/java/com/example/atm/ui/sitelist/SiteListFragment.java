package com.example.atm.ui.sitelist;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import com.example.atm.ui.search.view.SearchFragment;
import com.example.atm.ui.sitePager.Fragment_SiteItem_ViewPager;
import com.example.atm.ui.sitelist.SiteListContract;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.adapter.RecyclerViewAdapter;

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
import rx.Observable;
import rx.Observer;


@SuppressLint("DefaultLocale")
public class SiteListFragment extends Fragment implements CustomItemClickListener, OnRefreshListener, SiteListContract.View {
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
    private SiteListContract.Presenter siteListPresenter;
    private ProgressDialog progressDialog;
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
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("loading......");
        setPresenter(new SiteListPresenter(this));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initAdapter();
        siteListPresenter.fetchSiteList("drc");

        return view;
    }

    public void initAdapter(){
        myAdapter = new RecyclerViewAdapter(getContext(), mSiteList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        mRecyclerView.setAdapter(myAdapter);
        myAdapter.setOnCustomeItemClickListener(this);
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
    
    public  void fetchSiteListByRxJava(String loginID){
        Log.i(TAG, "fetchSiteListByRxJava: ");

        ApiClientRxJava apiClientRxJava = MyRetrofit.getInstance().create(ApiClientRxJava.class);
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
        siteListPresenter.fetchSiteList("drc");
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

    @Override
    public void setPresenter(SiteListContract.Presenter presenter) {
        this.siteListPresenter = presenter;
    }

    @Override
    public void onSuccess() {
        hideDialog();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailed() {
        hideDialog();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showDialog() {
        progressDialog.show();
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showSiteList(List<SiteItem> siteList) {
        mSiteList.clear();
        mSiteList.addAll(SiteListSortUtil.sortList(siteList));
        myAdapter.notifyDataSetChanged();
    }


}
