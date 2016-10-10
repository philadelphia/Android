package com.example.atm.ui.troubleticket;

import java.util.ArrayList;
import java.util.Collections;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.adapter.TroubleTicketListRecyclerViewAdapter;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.TroubleTicket;
import com.example.atm.utils.CustomItemClickListener;
import com.example.atm.utils.MyRetrofit;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class TroubleTicketListFragment extends Fragment implements CustomItemClickListener{

	private SharedPreferences prefer;
	private Editor editor;
	private FragmentManager mFragmentManager;
	private RecyclerView recyclerView;

	private ArrayList<TroubleTicket.TTDataBean> mTroubletList = new ArrayList<TroubleTicket.TTDataBean>();
	private ProgressDialog dialog;
	private String LoginID = null;
	private SharedPreferences preferences;
	private TextView textView;
	private Bundle bundle;
	private View root;
	private static final String TAG = "TroubleTicketListFragme";
	private Call<TroubleTicket> troubleTicketList;
	private TroubleTicketListRecyclerViewAdapter mAdapter;

	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView: ");
	
		root = inflater.inflate(R.layout.troubleticket, container, false);

		preferences = getActivity().getSharedPreferences("config",
				Context.MODE_PRIVATE);
		LoginID = preferences.getString("LoginID", "drc");
		Log.i(TAG, "onCreateView:LoginID ==  " + LoginID);
		mFragmentManager = getActivity().getSupportFragmentManager();
		initView(root);
		getAllTroubleTicketes("drc");
		return root;
	}

	public void initView(View view){
		Log.i(TAG, "initView: ");
		textView = (TextView) view.findViewById(R.id.textview);
		recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
		mAdapter = new TroubleTicketListRecyclerViewAdapter(getContext(),mTroubletList);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
		recyclerView.setAdapter(mAdapter);
		mAdapter.setOnCustomeItemClickListener(this);
	}
	public void getAllTroubleTicketes(String loginID){
		Log.i(TAG, "getAllTroubleTicketes: ");
		Retrofit retrofit = MyRetrofit.initRetrofit();
		ApiClient apiClient = retrofit.create(ApiClient.class);
		troubleTicketList = apiClient.getTroubleTicketList(loginID);
		troubleTicketList.enqueue(new Callback<TroubleTicket>() {
			@Override
			public void onResponse(Call<TroubleTicket> call, Response<TroubleTicket> response) {
				if (response.body() != null){
					mTroubletList.clear();
					Log.i(TAG, "onResponse: " + response.body().getTTData().size());
					mTroubletList.addAll(response.body().getTTData());
					mAdapter.notifyDataSetChanged();

				}
			}

			@Override
			public void onFailure(Call<TroubleTicket> call, Throwable t) {
				Log.i(TAG, "onFailure: ");
			}
		});
	}


	@Override
	public void onResume() {
		super.onResume();
		MainActivity.setActionBarTitle(getString(R.string.title_trouble_ticket),null);
	}

	public ArrayList<TroubleTicket.TTDataBean> sortList(ArrayList<TroubleTicket.TTDataBean> sitelist) {

		ArrayList<TroubleTicket.TTDataBean> sortedList = new ArrayList<TroubleTicket.TTDataBean>();
		ArrayList<TroubleTicket.TTDataBean> criticalList = new ArrayList<TroubleTicket.TTDataBean>();

		for (TroubleTicket.TTDataBean siteItem : sitelist) {
			criticalList.add(siteItem);
		}

//		sortListByAlarmNum(criticalList);
		sortedList.addAll(criticalList);

		return sortedList;
	}

	public void sortListByAlarmNum(ArrayList<TroubleTicket.TTDataBean> sitelist) {

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		root = null;
	}

	@Override
	public void onItemClick(View v, int position) {
		bundle = new Bundle();
//		TroubleTicket.TTDataBean = sortList(mTroubletList).get(position);
		TroubleTicket.TTDataBean ttdataBean = mTroubletList.get(position);
		bundle.putSerializable("TroubleTicket", ttdataBean);
		bundle.putString("flag", "NORMAL");
		Fragment fragment = new TroubleTicketDetailFragment();
		fragment.setArguments(bundle);
		mFragmentManager.beginTransaction()
				.replace(R.id.container, fragment).addToBackStack(null)
				.commit();
	}

	@Override
	public void onItemLongClick(View v, int position) {

	}
}
