package com.example.myapplication.ui.fragment.manager

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import android.view.View.OnTouchListener
import android.widget.AbsListView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ImageView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.adapter.MyBaseAdapter
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class TestFragment : Fragment(), OnTouchListener, AbsListView.OnScrollListener {
    private var packageManager: PackageManager? = null
    private val installedPackages: MutableList<PackageInfo> = ArrayList()
    private var listView: ListView? = null
    private var imageview: ImageView? = null
    private var myAdapter: MyBaseAdapter? = null
    private val lastVisibleItem = 0
    override fun onAttach(context: Context) {
        Log.i(TAG, "onAttach: ")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        packageManager = context!!.packageManager
        initView(view)
        myAdapter = MyBaseAdapter(installedPackages, context)
        listView!!.adapter = myAdapter
        listView!!.emptyView = imageview
        registerForContextMenu(listView!!)
        initDatas()
        return view
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unregisterForContextMenu(listView!!)
    }

    private fun initView(view: View) {
        listView = view.findViewById<View>(R.id.listview) as ListView
        imageview = view.findViewById<View>(R.id.img) as ImageView
        //        listView.setOnItemClickListener(this);
        listView!!.setOnTouchListener(this)
        listView!!.setOnScrollListener(this)
    }

    fun initDatas() {
        Log.i(TAG, "initDatas: ")
        installedPackages.clear()
        installedPackages.addAll(packageManager!!.getInstalledPackages(0))
        Log.i(TAG, "initDatas:  size == " + installedPackages.size)
        myAdapter!!.notifyDataSetChanged()
    }

    //    @Override
    //    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
    //        Toast.makeText(getContext(), "第" + position + "item was clicked", Toast.LENGTH_SHORT).show();
    //    }
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu.add(0, 0, 0, "delete")
        menu.add(0, 1, 1, "save")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val menuInfo: AdapterContextMenuInfo
        menuInfo = item.menuInfo as AdapterContextMenuInfo
        when (item.itemId) {
            0 -> deleteItem(menuInfo.position)
            1 -> {
            }
            else -> {
            }
        }
        return super.onContextItemSelected(item)
    }

    private fun deleteItem(position: Int) {
        installedPackages.removeAt(position)
        myAdapter!!.notifyDataSetChanged()
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> Log.i(TAG, "onTouch: keydown")
            MotionEvent.ACTION_UP -> Log.i(TAG, "onTouch: keyup")
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_OUTSIDE -> Log.i(TAG, "onTouch: outside")
            MotionEvent.ACTION_POINTER_DOWN -> Log.i(TAG, "onTouch: ACTION_POINTER_DOWN")
            MotionEvent.ACTION_POINTER_UP -> Log.i(TAG, "onTouch:ACTION_POINTER_UP ")
            MotionEvent.ACTION_CANCEL -> Log.i(TAG, "onTouch: cancel")
        }
        return false
    }

    override fun onScrollStateChanged(absListView: AbsListView, scrollState: Int) {
        when (scrollState) {
            AbsListView.OnScrollListener.SCROLL_STATE_IDLE -> Log.i(TAG, "onScrollStateChanged: 滑动停止")
            AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL -> Log.i(TAG, "onScrollStateChanged: 正在滑动....")
            AbsListView.OnScrollListener.SCROLL_STATE_FLING -> Log.i(TAG, "onScrollStateChanged: 已经松开手指惯性滑动")
            else -> {
            }
        }
    }

    override fun onScroll(absListView: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        Log.d(TAG, "onScroll: firstVisibleItem $firstVisibleItem")
        Log.d(TAG, "onScroll: visibleItemCount $visibleItemCount")
        Log.d(TAG, "onScroll: totalItemCount $totalItemCount")

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

    companion object {
        private const val TAG = "TestFragment"
    }
}