package com.example.myapplication.ui.fragment.manager;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.MyBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements View.OnTouchListener,AbsListView.OnScrollListener {
    private PackageManager packageManager;
    private List<PackageInfo> installedPackages = new ArrayList<PackageInfo>();
    private ListView listView;
    private ImageView imageview;
    private  MyBaseAdapter myAdapter;
    private static final String TAG = "TestFragment";
    private int lastVisibleItem;

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach: ");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        packageManager = getContext().getPackageManager();
        initView(view);
        myAdapter = new MyBaseAdapter(installedPackages, getContext());
        listView.setAdapter(myAdapter);
        listView.setEmptyView(imageview);
        registerForContextMenu(listView);
        initDatas();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterForContextMenu(listView);
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        imageview = (ImageView) view.findViewById(R.id.img);
//        listView.setOnItemClickListener(this);
        listView.setOnTouchListener(this);
        listView.setOnScrollListener(this);

    }

    public void initDatas(){
        Log.i(TAG, "initDatas: ");
        installedPackages.clear();
        installedPackages.addAll(packageManager.getInstalledPackages(0));
        Log.i(TAG, "initDatas:  size == " + installedPackages.size());
        myAdapter.notifyDataSetChanged();

    }
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        Toast.makeText(getContext(), "第" + position + "item was clicked", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"delete");
        menu.add(0,1,1,"save");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        switch (item.getItemId()){
            case 0:
                deleteItem( menuInfo.position);
                break;
            case 1:
                break;
            default:
                break;

        }
        return super.onContextItemSelected(item);
    }

    private void deleteItem(int position) {
        installedPackages.remove(position);
        myAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouch: keydown");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouch: keyup");
                break;
            case MotionEvent.ACTION_MOVE:
//                Log.i(TAG, "onTouch: ACTION_MOVE ");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.i(TAG, "onTouch: outside");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.i(TAG, "onTouch: ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.i(TAG, "onTouch:ACTION_POINTER_UP ");
                break;
            case  MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "onTouch: cancel");
                break;
        }
        return false;
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        switch (scrollState){
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:    //stop scroll
                Log.i(TAG, "onScrollStateChanged: 滑动停止");
                
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:     //scrolling
                Log.i(TAG, "onScrollStateChanged: 正在滑动....");
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:    //手指松开后惯性滑动
                Log.i(TAG, "onScrollStateChanged: 已经松开手指惯性滑动");
                break;
            default:
                break;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount ) {
        Log.d(TAG, "onScroll: firstVisibleItem " + firstVisibleItem );
        Log.d(TAG, "onScroll: visibleItemCount " + visibleItemCount);
        Log.d(TAG, "onScroll: totalItemCount " + totalItemCount);

//        if (firstVisibleItem + visibleItemCount  == totalItemCount && totalItemCount > 0){
//            Log.e(TAG, "onScroll: 滑动到了最下面");
//        }else
//            Log.i(TAG, "onScroll: 还没滑动到底");
//
//        if (firstVisibleItem > lastVisibleItem){
//            Log.e(TAG, "onScroll: 向上滑动");
//        }else if (firstVisibleItem < lastVisibleItem){
//            Log.e(TAG, "onScroll: 向下滑动");
//        }
//       lastVisibleItem = firstVisibleItem;
//        Log.i(TAG, "onScroll: lastVisibleItem == " + lastVisibleItem);
//
    }


}
