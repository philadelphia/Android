package com.example.atm.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.atm.R;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.MainActivity;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.entities.PCFilter;


import com.example.atm.entities.FilterBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SearchFragment extends Fragment implements OnClickListener, OnItemClickListener {

	private static final String LOG_TAG = SearchFragment.class.getSimpleName();
	private static final String TAG = "SearchFragment";
	private EditText ed_search;
	private ImageButton bt_search;
	private Button bt_save;
	private Button bt_query;
	private EditText ed_bookMark;
	private ListView lv_list;
	private AlertDialog.Builder builder;
	private List<PCFilter.PrDetailBean> productList = new ArrayList<>();
	private List<PCFilter.PrCircleDetailBean> circleList = new ArrayList<>();
	private List<PCFilter.PrClusterDetailBean> clusterList = new ArrayList<>();
	private TextView tv_product;
	private FilterBean filterBean = null;
	private List<FilterBean> filterLists;
	private TextView tv_circle;
	private TextView tv_cluster;
	private String selectedProduct;
	private String selectedCircle;
	private String selectedCluster;
	private List<FilterBean> filterList;
	private View inflate;
	private View view;
	private Context context;
//	private SiteFilterAdapter adapter;
//	private FilterMarHelper filterMarHelper;
	private ArrayList<String> productNameList = new ArrayList<String>();
	private List<String> circleNameList = new ArrayList<>();
	private List<String> clusterNameList = new ArrayList<>();
	private SharedPreferences mSharedPreferences;
	private String loginID = null;
	private Bundle bundle = null;
	private String[] array;
	private SharedPreferences sharePreference;
	private SharedPreferences.Editor editor;
	private final static String Product = "Product";
	private final static String Circle = "Circle";
	private final static String Cluster = "Cluster";
	private ProgressDialog loadDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.i(LOG_TAG, "onCreate");
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.i(TAG, "onCreateView");
//		MyNetworkStatus.getNetworkConnection(getActivity(), "search");
		Log.i(TAG, "Fragment's number is " + getFragmentManager().getFragments().size() + " ");
		mSharedPreferences = getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
		loginID = mSharedPreferences.getString("LoginID", null);

//		filterMarHelper = new FilterMarHelper(context);
		context = getContext();
		builder = new AlertDialog.Builder(context);
		loadDialog = new ProgressDialog(context);
		loadDialog.setMessage("Loading...");
		loadDialog.show();
		inflate = inflater.inflate(R.layout.fragment_search_filter, container, false);
		sharePreference = context.getSharedPreferences("SelectedItem", Context.MODE_PRIVATE);
		editor = sharePreference.edit();

		// initialize UI
		initView();
		
		return inflate;
	}

	public void initView() {
		Log.i(TAG, "initView");
		ed_search = (EditText) inflate.findViewById(R.id.ed_search);
		bt_search = (ImageButton) inflate.findViewById(R.id.bt_search);
		bt_search.setOnClickListener(this);
		lv_list = (ListView) inflate.findViewById(R.id.lv_list);
		tv_product = (TextView) inflate.findViewById(R.id.tv_product);
		tv_circle = (TextView) inflate.findViewById(R.id.tv_circle);
		tv_cluster = (TextView) inflate.findViewById(R.id.tv_cluster);
		bt_save = (Button) inflate.findViewById(R.id.bt_save);
		bt_query = (Button) inflate.findViewById(R.id.bt_query);
		tv_product.setOnClickListener(this);
		tv_circle.setOnClickListener(this);
		tv_cluster.setOnClickListener(this);
		bt_save.setOnClickListener(this);
		bt_query.setOnClickListener(this);

		filterList = new ArrayList<FilterBean>();
		filterLists = new ArrayList<FilterBean>();
		filterList.clear();
		filterLists.clear();

		// 不要放parseJson里，否则缓存走两次
		getDataFromDatabase();
		lv_list.setOnItemClickListener(this);
	}

	private void getDataFromDatabase() {
//		filterList = filterMarHelper.getFilterBeanList();
		if (filterList.size() > 0) {
			for (int i = 0; i < filterList.size(); i++) {
				String id = filterList.get(i).getLoginID();
				if (loginID.equals(id) || loginID == id) {
					filterBean = new FilterBean(filterList.get(i).getProductID(), filterList.get(i).getCirlceID(), filterList.get(i).getClusterID(), filterList.get(i).getTag(), filterList.get(i).getLoginID(), filterList.get(i).getProductName(), filterList.get(i).getCircleName(), filterList.get(i).getClusterName());
					filterLists.add(filterBean);

					lv_list.setEmptyView(null);
//					adapter = new SiteFilterAdapter(context, filterLists);
					lv_list.setBackgroundColor(getResources().getColor(R.color.white));
//					lv_list.setAdapter(adapter);
//					ListviewUtlis.setListViewHeightBasedOnChildren(lv_list);

				}
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
		MainActivity.setActionBarTitle("Filter & search", null);
		fetchFilterData("drc");

		tv_product.setText(sharePreference.getString(Product, "Please select a product"));
		tv_circle.setText(sharePreference.getString(Circle, "Please select a circle"));
		tv_cluster.setText(sharePreference.getString(Cluster, "Please select a cluster"));
		Log.i(TAG, sharePreference.getString(Product, "Please select a product"));
	}
	
	public void fetchFilterData(String loginID) {
		Log.i(TAG, "fetchFilterData: ");
		Retrofit retrofit = MyRetrofit.initRetrofit();
		ApiClient apiClient = retrofit.create(ApiClient.class);
		Call<PCFilter> allProducts = apiClient.getAllProducts(loginID);
		allProducts.enqueue(new Callback<PCFilter>() {
			@Override
			public void onResponse(Call<PCFilter> call, Response<PCFilter> response) {
				Log.i(TAG, "onResponse: " + response.body());
				productList.clear();
				productList.addAll(response.body().getPrDetail());
			}

			@Override
			public void onFailure(Call<PCFilter> call, Throwable t) {
				Log.i(TAG, "onFailure: ");
			}
		});
		
			

		
	}


	private ArrayList<String> getProductNameList() {
		Log.i(LOG_TAG, "getProductNameList");
		ArrayList<String> nameLists = new ArrayList<>();
		for (int i = 0; i < productList.size(); i++) {
			String productName = productList.get(i).getProductName();
			nameLists.add(productName);
		}
		Log.i("productNameList", productNameList.toString());

		return nameLists;
	}

	private ArrayList<String> getCircleNameList(String productName) {
		Log.i(LOG_TAG, "getCircleNameList");
		ArrayList<String> nameLists = new ArrayList<>();
		for (PCFilter.PrDetailBean product : productList) {
			String proName = product.getProductName();
			if (productName.equals(proName)) {
				String[] cirids = product.getCircleID().split(",");

				for (String circleid : cirids) {
					Log.i(TAG, "circleid: " + Integer.parseInt(circleid));
					for (PCFilter.PrCircleDetailBean circle : circleList)
						if (Integer.parseInt(circleid) == (circle.getCircleID())) {
							Log.i(TAG, "circle: " + circle.toString());
							nameLists.add(circle.getCircleName());
						}
				}

				Log.i(TAG,"nameLists.size=== " + nameLists.size());
				return nameLists;
			}
		}

		return nameLists;
	}

	public  String getCircleNameById(int circleID){
		for (PCFilter.PrCircleDetailBean circle: circleList){
			if (circle.getCircleID() == circleID){
				return circle.getCircleName();
			}
		}

		return  null;
		}

	public ArrayList<String> getClusterNameList(String circleName) {

		ArrayList<String> nameLists = new ArrayList<>();
		for (PCFilter.PrCircleDetailBean cir : circleList) {
			if (circleName.equals(cir.getCircleName())) {
				String[] clusters = cir.getClusterID().split(",");
				for (String string : clusters) {
					for (PCFilter.PrClusterDetailBean cluster : clusterList) {
						if (string.equals(cluster.getClusterID())) {
							nameLists.add(cluster.getClusterName());
						}
					}
				}
			}
		}

		return nameLists;
	}

	@Override
	public void onDetach() {
		Log.i(LOG_TAG, "onDetach");

		super.onDetach();
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.i(LOG_TAG, "onPause");
		hideSoftKeyboard();
	}

	@Override
	public void onStop() {
		Log.i(LOG_TAG, "onStop");

		super.onStop();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_search:
			onSearch();
			break;

		case R.id.tv_product:
			Log.i(LOG_TAG, "product was clicked");
			onProductSelect();
			break;

		case R.id.tv_circle:
			Log.i(LOG_TAG, "circle was clicked");
			if (TextUtils.isEmpty(selectedProduct)) {
				break;
			}
			onCircleSelect();
			break;

		case R.id.tv_cluster:
			Log.i(LOG_TAG, "cluster was clicked");
			if (TextUtils.isEmpty(selectedCircle)) {
				break;
			}
//			onClusterSelect();
			break;

		case R.id.bt_save:
			Log.i(LOG_TAG, "save was clicked");
//			onSave();
			break;

		case R.id.bt_query:
			Log.i(LOG_TAG, "query was clicked");
//			onQuery();
			break;

		}
	}

	private void onSearch() {
		Log.i(TAG, "onSearch: ");
		String sitename = ed_search.getText().toString();
		hideSoftKeyboard();
		if (TextUtils.isEmpty(sitename)) {
			builder.setMessage("You must input a keyword!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			}).show();

		} else {
			bundle = new Bundle();
			bundle.putCharSequence(Constatnts.SITENAME, sitename);
			Fragment searchResultFragment = new SearchResultFragment();
			searchResultFragment.setArguments(bundle);
			Log.i(TAG,"arguments == "+bundle.toString());
			getFragmentManager().beginTransaction().replace(R.id.container, searchResultFragment).addToBackStack(null).commit();
			ed_search.setText(null);
//
		}
	}

	private void onProductSelect() {
		AlertDialog.Builder build = new AlertDialog.Builder(context);
		productNameList = getProductNameList();
		array = new String[productNameList.size()];
		array = (String[]) productNameList.toArray(array);
		build.setItems(array, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				selectedProduct = array[which];
				Log.i(TAG, "onClick: " + selectedProduct);
				tv_product.setText(selectedProduct);
				Log.i(TAG, "circleNameList: " + getCircleNameList(selectedProduct));
				selectedCircle = getCircleNameList(selectedProduct).get(0);
				Log.i(TAG, "onClick: selectedCircle " + selectedCircle);
				tv_circle.setText(selectedCircle);

				selectedCluster = getClusterNameList(tv_circle.getText().toString()).get(0);
				tv_cluster.setText(selectedCluster);
				editor.putString(Product, selectedProduct);
				editor.putString(Circle, selectedCircle);
				editor.putString(Cluster, selectedCluster);

				editor.commit();
			}
		}).show();

	}

	private void onCircleSelect() {
		AlertDialog.Builder build = new AlertDialog.Builder(context);
		circleNameList = getCircleNameList(selectedProduct);
		array = new String[circleNameList.size()];
		array = (String[]) circleNameList.toArray(array);
		build.setItems(array, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				selectedCircle = array[which];
				tv_circle.setText(selectedCircle);
				selectedCluster = getClusterNameList(selectedCircle).get(0);
				tv_cluster.setText(selectedCluster);
				editor.putString(Circle, selectedCircle);
				editor.putString(Cluster, selectedCluster);
				editor.commit();
			}
		}).show();
	}

	private void onClusterSelect() {

		AlertDialog.Builder build = new AlertDialog.Builder(context);
		clusterNameList = getClusterNameList(selectedCircle);
		array = new String[clusterNameList.size()];
		array = (String[]) clusterNameList.toArray(array);
		build.setItems(array, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				selectedCluster = array[which];
				tv_cluster.setText(selectedCluster);
				editor.putString(Cluster, selectedCluster);
				editor.commit();
			}
		}).show();
	}

//	private void onSave() {
//		builder = new AlertDialog.Builder(context);
//		if (tv_product.getText().toString().startsWith("Please")) {
//			builder.setMessage("You must select one item at least!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//
//				}
//			}).show();
//		} else {
//			view = LayoutInflater.from(context).inflate(R.layout.filter_save, new LinearLayout(context));
//			ed_bookMark = (EditText) view.findViewById(R.id.ed_filter);
//			builder.setTitle("Save filter").setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//					String tag = ed_bookMark.getText().toString();
//					FilterBean bean = filterMarHelper.getFilterBean(tag, loginID);
//					if (null == tag || "".equals(tag)) {
//						ToastUtil.showToast(getActivity(), "Data can not be empty！");
//						return;
//					} else if (null != bean) {
//						ToastUtil.showToast(getActivity(), "Already exist in the database！");
//					} else {
//						int productID = 0;
//						String circleID = null;
//						String clusterID = null;
//						selectedProduct = tv_product.getText().toString();
//						selectedCircle = tv_circle.getText().toString();
//						selectedCluster = tv_cluster.getText().toString();
//						FilterBean bean2 = new FilterBean();
//						for (Product pro : productList) {
//							if (pro.getProductName().equals(selectedProduct)) {
//								productID = pro.getProductID();
//							}
//						}
//
//						for (Circle circle : circleList) {
//							if (circle.getCircleName().equals(selectedCircle)) {
//								circleID = circle.getCircleID();
//							}
//						}
//
//						for (Cluster cluster : clusterList) {
//							if (cluster.getClusterName().equals(selectedCluster)) {
//								clusterID = cluster.getClusterID();
//
//							}
//						}
//
//						bean2.setProductName(selectedProduct);
//						bean2.setCircleName(selectedCircle);
//						bean2.setClusterName(selectedCluster);
//						bean2.setCirlceID(circleID);
//						bean2.setClusterID(clusterID);
//						bean2.setProductID(productID);
//						bean2.setTag(tag);
//						if (productID == 0 && TextUtils.isEmpty(clusterID) && TextUtils.isEmpty(circleID)) {
//							ToastUtil.showToast(getActivity(), "You must select one item at least!");
//						}
//						filterMarHelper.addFilter(bean2, loginID);
//						// 重要的语句
//						filterList.clear();
//						filterLists.clear();
//						getDataFromDatabase();
//					}
//				}
//			}).setNegativeButton("Cancel", null).create().show();
//		}
//	}

	private void onQuery() {
//		if (tv_product.getText().toString().startsWith("Please")) {
//			builder.setMessage("You must select one item at least!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//
//				}
//			}).show();
//		} else {
//			Fragment queryFragment = new QueryResultFragment();
//			FragmentManager management = getFragmentManager();
//
//			bundle = new Bundle();
//			selectedProduct = tv_product.getText().toString();
//			selectedCircle = tv_circle.getText().toString();
//			selectedCluster = tv_cluster.getText().toString();
//
//			int productID = 0;
//			String circleID = null;
//			String clusterID = null;
//
//			for (Product pro : productList) {
//				if (pro.getProductName().equals(selectedProduct)) {
//					productID = pro.getProductID();
//
//				}
//			}
//
//			for (Circle circle : circleList) {
//				if (circle.getCircleName().equals(selectedCircle)) {
//					circleID = circle.getCircleID();
//				}
//			}
//
//			for (Cluster cluster : clusterList) {
//				if (cluster.getClusterName().equals(selectedCluster)) {
//					clusterID = cluster.getClusterID();
//
//				}
//			}
//
//			bundle.putInt("ProductID", productID);
//			bundle.putString("Product", selectedProduct);
//			bundle.putString("CircleID", circleID);
//			bundle.putString("Circle", selectedCircle);
//			bundle.putString("ClusterID", clusterID);
//			bundle.putString("Cluster", selectedCluster);
//
//			queryFragment.setArguments(bundle);
//			Log.i(LOG_TAG, bundle.toString());
//			management.beginTransaction().replace(R.id.container, queryFragment, "Query").addToBackStack(null).commit();
//
//		}

	}

	public void addToFilter() {

	}

	private void hideSoftKeyboard() {
		InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(ed_search.getWindowToken(), 0);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		Fragment filterFragment = new QueryResultFragment();
//		FragmentManager management = getFragmentManager();
//
//		Bundle bundle = new Bundle();
//		int productID = filterList.get(position).getProductID();
//		bundle.putInt("ProductID", productID);
//		bundle.putString("Product", filterList.get(position).getProductName());
//
//		String circleID = filterList.get(position).getCirlceID();
//		if (circleID != null) {
//			bundle.putString("CircleID", circleID);
//			bundle.putString("Circle", filterList.get(position).getCircleName());
//		}
//
//		String clusterID = filterList.get(position).getClusterID();
//		if (clusterID != null) {
//			bundle.putString("ClusterID", clusterID);
//			bundle.putString("Cluster", filterList.get(position).getClusterName());
//		}
//
//		filterList.get(position).getTag();
//
//		filterFragment.setArguments(bundle);
//		Log.i("bundle_query", bundle.toString());
//		management.beginTransaction().replace(R.id.container, filterFragment, "Query").addToBackStack(null).commit();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

}
