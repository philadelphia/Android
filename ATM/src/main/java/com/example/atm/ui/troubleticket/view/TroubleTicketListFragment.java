package com.example.atm.ui.troubleticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.example.atm.apiInterface.ApiClientRxJava;
import com.example.atm.bean.TroubleTicket;
import com.example.atm.ui.troubleticket.view.ITroubleTicketList;
import com.example.atm.utils.CustomItemClickListener;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.utils.RxsRxSchedulers;


import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;


public class TroubleTicketListFragment extends Fragment implements CustomItemClickListener, ITroubleTicketList{

	private SharedPreferences prefer;
	private Editor editor;
	private FragmentManager mFragmentManager;
	private RecyclerView recyclerView;

	private ArrayList<TroubleTicket.TTDataBean> mTroubletList = new ArrayList<TroubleTicket.TTDataBean>();
	private ProgressDialog progressDialog;
	private String LoginID = null;
	private SharedPreferences preferences;
	private TextView textView;
	private Bundle bundle;
	private View root;
	private static final String TAG = "TroubleTicketListFragme";
	private Call<TroubleTicket> troubleTicketList;
	private TroubleTicketListRecyclerViewAdapter mAdapter;
	private Observable<TroubleTicket> troubleTicketListRxjava;

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
		initProgressDialog();
		
//		getAllTroubleTicketes("drc");
		getAllTroubleTicketesByRxjava("drc");
		return root;
	}

	private void initProgressDialog() {
		progressDialog = new ProgressDialog(getContext());
		progressDialog.setMessage("Loading...");
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
		ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
		troubleTicketList = apiClient.getTroubleTicketList(loginID);
		troubleTicketList.enqueue(new Callback<TroubleTicket>() {
			@Override
			public void onResponse(Call<TroubleTicket> call, Response<TroubleTicket> response) {
				Log.i(TAG, "onResponse:code ===  " + response.code());
				Log.i(TAG, "onResponse: message == " + response.message());
				Headers headers =  response.headers();

				Map<String, List<String>> stringListMap = headers.toMultimap();
				for (Map.Entry<String,List<String>> entry : stringListMap.entrySet()
					 ) {
					Log.i(TAG, "key== " + entry.getKey());
					Log.i(TAG, "value'size ==: " + entry.getValue().size());
					Log.i(TAG, "value== " + entry.getValue().toString());
				}
				

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

	public void getAllTroubleTicketesByRxjava(String loginID){
		Log.i(TAG, "getAllTroubleTicketes: ");
		ApiClientRxJava apiClient = MyRetrofit.getInstance().create(ApiClientRxJava.class);
		troubleTicketListRxjava = apiClient.getTroubleTicketList(loginID);
		troubleTicketListRxjava.compose(RxsRxSchedulers.io_main()).subscribe(new Subscriber<TroubleTicket>() {
			@Override
			public void onCompleted() {
				Log.i(TAG, "onCompleted: ");
			}

			@Override
			public void onError(Throwable e) {
				Log.i(TAG, "onError: ");
			}

			@Override
			public void onNext(TroubleTicket troubleTicket) {
				Log.i(TAG, "onNext: ");
				mTroubletList.clear();
				Log.i(TAG, "onResponse: " + troubleTicket.getTTData().size());
				mTroubletList.addAll(troubleTicket.getTTData());
				mAdapter.notifyDataSetChanged();
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
		getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.i(TAG, "run: 厉害了我的哥");
			}
		});
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

	@Override
	public void onDestroy() {
		super.onDestroy();
		HttpCallUtil.cancelCall(troubleTicketList);
	}

	@Override
	public void onSuccess() {

	}

	@Override
	public void onFailed() {

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


}
