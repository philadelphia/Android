package com.example.atm.ui.search.view;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.example.atm.apiInterface.ApiClientRxJava;
import com.example.atm.ui.search.QueryResultFragment;
import com.example.atm.ui.search.SearchResultFragment;
import com.example.atm.ui.search.presenter.ISearchPresenter;
import com.example.atm.ui.search.presenter.SearchPresenterImpl;
import com.example.atm.utils.Constatnts;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.MainActivity;

import com.example.atm.entities.PCFilter;


import com.example.atm.entities.FilterBean;
import com.example.atm.utils.RxsRxSchedulers;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;


public class SearchFragment extends Fragment implements OnClickListener, OnItemClickListener , ISearchView{
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
    private PCFilter.PrDetailBean selectedProduct;
    private PCFilter.PrCircleDetailBean selectedCircle;
    private PCFilter.PrClusterDetailBean selectedCluster;
    private String selectedProductName;
    private String selectedCircleName;
    private String selectedClusterName;
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
    private ProgressDialog progressDialog;
    private Call<PCFilter> allProducts;
    private ISearchPresenter searchPresenter;
    private int index_product;
    private int index_circle;
    private int selectedProductID;
    private int selectedCircleID;
    private int selectedClusterID;
    private Observable<PCFilter> allProductsRxjava;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
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
        initDialog();
        searchPresenter = new SearchPresenterImpl(this);
        inflate = inflater.inflate(R.layout.fragment_search_filter, container, false);
        sharePreference = context.getSharedPreferences("SelectedItem", Context.MODE_PRIVATE);
        editor = sharePreference.edit();

        // initialize UI
        initView();

        return inflate;
    }

    private void initDialog() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
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
        searchPresenter.fetchFilterData("drc");
//        fetchFilterDataByRxjava("drc");

        tv_product.setText(sharePreference.getString(Constatnts.SelectedProductName, "Please select a product"));
        tv_circle.setText(sharePreference.getString(Constatnts.SelectedCircleName, "Please select a circle"));
        tv_cluster.setText(sharePreference.getString(Constatnts.SelectedClusterName, "Please select a cluster"));
        selectedProductID = sharePreference.getInt(Constatnts.SelectedProductID , 0);
        selectedCircleID = sharePreference.getInt(Constatnts.SelectedCircleID, 0);
        selectedClusterID = sharePreference.getInt(Constatnts.SelectedClusterID, 0);
//        Log.i(TAG,selectedProductID + " --------" + selectedProductName);
//        Log.i(TAG, selectedCircleID + " --------" + selectedCircleName);
//        Log.i(TAG, selectedClusterID + " --------" + selectedClusterName);
    }


    public void fetchFilterDataByRxjava(final String loginID) {
        Log.i(TAG, "fetchFilterData: ");
        ApiClientRxJava apiClient = MyRetrofit.getInstance().create(ApiClientRxJava.class);
        allProductsRxjava = apiClient.getAllProducts(loginID);
        allProductsRxjava.compose(RxsRxSchedulers.io_main()).subscribe(new Subscriber<PCFilter>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
                progressDialog.dismiss();
            }

            @Override
            public void onNext(PCFilter pcFilter) {
                Log.i(TAG, "onNext: ");
                progressDialog.dismiss();
                productList.clear();
                productList.addAll(pcFilter.getPrDetail());
                circleList.clear();
                circleList.addAll(pcFilter.getPrCircleDetail());
                clusterList.clear();
                clusterList.addAll(pcFilter.getPrClusterDetail());
            }
        });

    }

    private ArrayList<String> getProductNameList() {
        Log.i(TAG, "getProductNameList");
        ArrayList<String> nameLists = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            String productName = productList.get(i).getProductName();
            nameLists.add(productName);
        }
        Log.i("productNameList", productNameList.toString());
        return nameLists;
    }

    private ArrayList<String> getCircleNameList(PCFilter.PrDetailBean selectProduct) {
        Log.i(TAG, "getCircleNameList");
        ArrayList<String> nameLists = new ArrayList<>();
        String[] cirids = selectProduct.getCircleID().split(",");
        for (String circleid : cirids) {
            Log.i(TAG, "circleid: " + Integer.parseInt(circleid));
            for (PCFilter.PrCircleDetailBean circle : circleList)
                if (Integer.parseInt(circleid) == (circle.getCircleID())) {
                    nameLists.add(circle.getCircleName());
                }
        }
        Log.i(TAG, "nameLists.size=== " + nameLists.size());
        return nameLists;

    }

    public ArrayList<String> getClusterNameList(PCFilter.PrCircleDetailBean selectCircle) {
        ArrayList<String> nameLists = new ArrayList<>();
        String[] clusters = selectCircle.getClusterID().split(",");
        for (String string : clusters) {
            for (PCFilter.PrClusterDetailBean cluster : clusterList) {
                if (string.equals(cluster.getClusterID())) {
                    nameLists.add(cluster.getClusterName());
                }
            }

        }

        return nameLists;
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach");

        super.onDetach();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
        hideSoftKeyboard();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop");

        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search:
                onSearch();
                break;

            case R.id.tv_product:
                Log.i(TAG, "product was clicked");
                onProductSelect();
                break;

            case R.id.tv_circle:
                Log.i(TAG, "circle was clicked");
                if (TextUtils.isEmpty(selectedProductName)) {
                    break;
                }
                onCircleSelect();
                break;

            case R.id.tv_cluster:
                Log.i(TAG, "cluster was clicked");
                if (TextUtils.isEmpty(selectedCircleName)) {
                    break;
                }
//			onClusterSelect();
                break;

            case R.id.bt_save:
                Log.i(TAG, "save was clicked");
//			onSave();
                break;

            case R.id.bt_query:
                Log.i(TAG, "query was clicked");
                onQuery();
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
            Log.i(TAG, "arguments == " + bundle.toString());
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
                index_product = which;

                selectedProductName = array[which];
                selectedProduct = productList.get(index_product);
                selectedProductID = selectedProduct.getProductID();
                Log.i(TAG, "onClick: selectedProductID" + selectedProductID);
                tv_product.setText(selectedProductName);
                Log.i(TAG, "circleNameList: " + getCircleNameList(selectedProduct));

                tv_cluster.setText(selectedClusterName);
                editor.putInt(Constatnts.SelectedProductID, selectedProductID);
                editor.putString(Constatnts.SelectedProductName, selectedProductName);


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
                index_circle = which;
                selectedCircle = circleList.get(index_circle);
                selectedCircleID = selectedCircle.getCircleID();
                Log.i(TAG, "onClick: selectedCircleID" + selectedCircleID);
                selectedCircleName = array[which];
                tv_circle.setText(selectedCircleName);

                editor.putInt(Constatnts.SelectedCircleID,selectedCircleID);
                editor.putString(Constatnts.SelectedCircleName, selectedCircleName);

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
                selectedClusterName = array[which];
                selectedCluster = clusterList.get(which);
                selectedClusterID = selectedCluster.getClusterID();
                Log.i(TAG, "onClick: selectedClusterID" + selectedClusterID);
                tv_cluster.setText(selectedClusterName);
                editor.putInt(Constatnts.SelectedClusterID, selectedClusterID);
                editor.putString(Constatnts.SelectedClusterName, selectedClusterName);
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
        if (tv_product.getText().toString().startsWith("Please")) {
            builder.setMessage("You must select one item at least!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            }).show();
        } else {
            Fragment queryFragment = new QueryResultFragment();
            FragmentManager management = getFragmentManager();
            bundle = new Bundle();
            bundle.putInt(Constatnts.SelectedProductID, selectedProductID);
            bundle.putString(Constatnts.SelectedProductName, selectedProductName);
            bundle.putInt(Constatnts.SelectedCircleID, selectedCircleID);
            bundle.putString(Constatnts.SelectedCircleName, selectedCircleName);
            bundle.putInt(Constatnts.SelectedClusterID, selectedClusterID);
            bundle.putString(Constatnts.SelectedCircleName, selectedClusterName);
            queryFragment.setArguments(bundle);
            Log.i(TAG, bundle.toString());
            management.beginTransaction().replace(R.id.container, queryFragment, "Query").addToBackStack(null).commit();
        }

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
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        HttpCallUtil.cancelCall(allProducts);
    }


    @Override
    public void showData(PCFilter pcFilter) {
        productList.clear();
        productList.addAll(pcFilter.getPrDetail());
        circleList.clear();
        circleList.addAll(pcFilter.getPrCircleDetail());
        clusterList.clear();
        clusterList.addAll(pcFilter.getPrClusterDetail());
    }

    @Override
    public void setPresenter(ISearchPresenter presenter) {
        this.searchPresenter = presenter;
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
