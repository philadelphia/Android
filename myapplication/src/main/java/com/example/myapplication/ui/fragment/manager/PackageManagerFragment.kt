package com.example.myapplication.ui.fragment.manager

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import android.widget.AdapterView.AdapterContextMenuInfo
import androidx.appcompat.view.ActionMode
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import com.example.myapplication.R
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.databinding.FragmentPackageManagerBinding
import com.example.myapplication.ui.activity.ThirdActivity
import com.example.myapplication.utils.ItemMoveCallBack
import com.example.myapplication.utils.OnRecyclerViewItemClickListener
import com.example.myapplication.utils.OnStartDragListener
import com.google.android.material.snackbar.Snackbar
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class PackageManagerFragment : Fragment(), OnStartDragListener {
    private var packageManager: PackageManager? = null
    private val installedPackages: MutableList<PackageInfo?> = ArrayList()
    private val pkgNameList: List<String>? = null
    private var myAdapter: RecyclerViewAdapter? = null
    private var actionMode: ActionMode? = null
    private var itemTouchHelper: ItemTouchHelper? = null
    private var binding: FragmentPackageManagerBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView: ")
        binding = FragmentPackageManagerBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initData()
    }

    private fun initView() {
        packageManager = context!!.packageManager
        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

//        myAdapter = new RecyclerViewItemTouchAdapter(installedPackages);
        myAdapter = RecyclerViewAdapter(installedPackages, this)
        binding!!.recyclerView.adapter = myAdapter
        //        binding.recyclerView.addItemDecoration(new MyItemDecoration(getActivity(),LinearLayoutManager.VERTICAL, R.drawable.itemdecoration));
//        binding.recyclerView.addItemDecoration(new MyItemDecoration1(LinearLayoutManager.VERTICAL));
//        binding.recyclerView.addItemDecoration(new MyItemDecoration2(LinearLayoutManager.VERTICAL));
//        binding.recyclerView.addItemDecoration(new TimeLineItemDecoration());
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding!!.recyclerView.layoutManager = linearLayoutManager


//        itemTouchHelper = new ItemTouchHelper(new ItemTouchCallBack(getActivity(), myAdapter));
//        MessageItemTouchCallBack itemTouchCallBack = new MessageItemTouchCallBack(myAdapter);
//        ItemSwipeCallBack itemTouchCallBack = new ItemSwipeCallBack(getContext(),myAdapter);
        val itemTouchCallBack: ItemMoveCallBack<*> = ItemMoveCallBack<Any?>(context, myAdapter)
        itemTouchHelper = ItemTouchHelper(itemTouchCallBack)
        itemTouchHelper!!.attachToRecyclerView(binding!!.recyclerView)
        binding!!.recyclerView.addOnItemTouchListener(object : OnRecyclerViewItemClickListener(binding!!.recyclerView) {
            override fun onItemClick(viewHolder: RecyclerView.ViewHolder) {
                Snackbar.make(binding!!.recyclerView, "ddd", Snackbar.LENGTH_SHORT).show()
                Log.i(TAG, "onItemClick: " + viewHolder.layoutPosition)
                Log.i(TAG, "onItemClick: " + viewHolder.hashCode())
            }

            override fun onItemLongPress(viewHolder: RecyclerView.ViewHolder) {
//                第一条数据不支持长按拖拽，其他的都可以
                if (viewHolder.layoutPosition != 0) {
                    itemTouchHelper!!.startDrag(viewHolder)
                }
            }
        })
        //        registerForContextMenu(binding.recyclerView);

//        flb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (actionMode == null) {
//                    actionMode = ((MainActivity) getActivity()).startSupportActionMode(new ActionMode.Callback() {
//                        @Override
//                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
//                            return true;
//                        }
//
//                        @Override
//                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                            switch (item.getItemId()) {
//                                case R.id.action_delete:
//                                    Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
//                            }
//                            return false;
//                        }
//
//                        @Override
//                        public void onDestroyActionMode(ActionMode mode) {
//                            actionMode = null;
//                        }
//                    });
//                }
//            }
//        });
        binding!!.flab.setOnClickListener { v ->
            val intent = Intent(activity, ThirdActivity::class.java)
            val location = IntArray(2)
            //获取FloatingActionButton的位置
            v.getLocationOnScreen(location)

//                location[0] += location[0] + flb.getWidth() / 2;
            intent.putExtra("location", location)
            startActivity(intent)
            activity!!.overridePendingTransition(0, 0)
        }

//        setOnSrollListener();
    }

    //    private void setOnSrollListener() {
    //        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    //            private final int HIDE_THRESHOLD = 5;
    //            private int scrolledDistance = 0;
    //            private boolean controlsVisible = true;
    //            int distance = coorlayout.getHeight() - flb.getTop();
    //
    //            @Override
    //            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    //                super.onScrollStateChanged(recyclerView, newState);
    //                Log.i(TAG, "onScrollStateChanged: ");
    //                switch (newState){
    //                    case RecyclerView.SCROLL_STATE_IDLE:
    //                        Log.i(TAG, " SCROLL_STATE_IDLE");
    //                        break;
    //                    case RecyclerView.SCROLL_STATE_DRAGGING:
    //                        Log.i(TAG, "SCROLL_STATE_DRAGGING: ");
    //                        break;
    //                    case RecyclerView.SCROLL_STATE_SETTLING:
    //                        Log.i(TAG, "SCROLL_STATE_SETTLING: ");
    //                        break;
    //                }
    //            }
    //
    //            @Override
    //            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    //                super.onScrolled(recyclerView, dx, dy);
    //                Log.i(TAG, "onScrolled: ");
    //                Log.i(TAG, "dy==  " + dy);
    //                Log.i(TAG, "onScrolled: distance " + distance);
    //
    //                //上滑隐藏
    //               if (dy > HIDE_THRESHOLD && controlsVisible) {
    //                    Log.i(TAG, "onScrollChange: 往上滑动");
    //                   flb.animate().translationY(400).setInterpolator(new LinearOutSlowInInterpolator()).setDuration(200).start();
    //                   controlsVisible = false;
    //                } else if (dy < -HIDE_THRESHOLD && !controlsVisible) {
    //                    Log.i(TAG, "onScrollChange: 往下滑动");
    //                   flb.animate().translationY(0).setInterpolator(new AccelerateInterpolator(2)).start();
    //                   controlsVisible = true;
    //                }
    //            }
    //
    //        });
    //    }
    override fun onDestroyView() {
        Log.i(TAG, "onDestroyView: ")
        super.onDestroyView()
        unregisterForContextMenu(binding!!.recyclerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
        if (actionMode != null) {
            actionMode!!.finish()
            actionMode = null
        }
    }

    protected inner class MyOnItemTouchHelperCallBack : ItemTouchHelper.Callback() {
        override fun isItemViewSwipeEnabled(): Boolean {
            return true
        }

        override fun isLongPressDragEnabled(): Boolean {
            //默认都不能被长安拖拽，然后再ItemTouchListener 中处理
            return false
        }

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
//
            var dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            if (recyclerView.layoutManager is GridLayoutManager) {
                dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            } else if (recyclerView.layoutManager is LinearLayoutManager) {
                dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            }
            val swipeFlags = ItemTouchHelper.END
            return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, swipeFlags)
        }

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            Log.i(TAG, "onMove: ")
            //第一条数据不能被别的数据挤走
            if (target.layoutPosition == 0) return false
            Collections.swap(installedPackages, viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun onMoved(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, fromPos: Int, target: RecyclerView.ViewHolder, toPos: Int, x: Int, y: Int) {
            Log.i(TAG, "onMoved: ")
            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
            recyclerView.adapter!!.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            Log.i(TAG, "onSwiped: ")
            installedPackages.removeAt(viewHolder.adapterPosition)
            myAdapter!!.notifyItemRemoved(viewHolder.adapterPosition)
        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            Log.i(TAG, "onSelectedChanged: actionState ==$actionState")
            (viewHolder as? RecyclerViewAdapter.MyViewHolder)?.itemView?.setBackgroundColor(resources.getColor(R.color.colorAccent))
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            viewHolder.itemView.setBackgroundColor(resources.getColor(R.color.cardview_light_background))
        }

        override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
            //滑动时自己实现背景及图片
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                //dX大于0时向右滑动，小于0向左滑动
                val backGroundView = viewHolder.itemView.findViewById<View>(R.id.item_background) //获取滑动的view
                val contentView = viewHolder.itemView.findViewById<View>(R.id.itemContent) //获取滑动的view
                //                //向右滑动
                if (dX > 0) {
                    //根据滑动实时绘制一个背景
                    val alpha = 1.0f - Math.abs(dX) / contentView.width.toFloat()
                    contentView.alpha = alpha
                    //绘制时需调用平移动画，否则滑动看不到反馈
                    contentView.translationX = dX
                    if (dX < contentView.width / 2) {
                        viewHolder.itemView.translationX = contentView.width / 2.toFloat()
                        var view: View
                    }
                } else {
                    //如果在getMovementFlags指定了向左滑动（ItemTouchHelper。START）时则绘制工作可参考向右的滑动绘制，也可直接使用下面语句交友系统自己处理
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            } else {
                //拖动时有系统自己完成
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
    }

    fun initData() {
        Log.i(TAG, "initData: ")
        installedPackages.clear()
        installedPackages.addAll(packageManager!!.getInstalledPackages(0))
        for (i in installedPackages.indices) {
            val packageInfo = installedPackages[i]
            Log.i(TAG, "packageName ===: " + packageInfo!!.packageName)
            Log.i(TAG, "icon=== : " + packageInfo.applicationInfo.icon)
        }
        myAdapter!!.notifyDataSetChanged()
        Log.i(TAG, "installedPackages: " + installedPackages.size)
    }

    override fun registerForContextMenu(view: View) {
        Log.i(TAG, "registerForContextMenu: ")
        super.registerForContextMenu(view)
    }

    override fun unregisterForContextMenu(view: View) {
        super.unregisterForContextMenu(view)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenuInfo?) {
        menu.add(0, v.id, 0, "Add") //groupId, itemId, order, title
        menu.add(0, v.id, 1, "Delete")
        menu.add(0, v.id, 2, "Mark")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item == null) {
            Log.i(TAG, "onContextItemSelected: item == null")
        }
        val position = 34
        //        int position = recyclerView.getAdapter().get
//        Log.i(TAG, "position == :" + position);
        val menuInfo = item.menuInfo as AdapterContextMenuInfo
        if (menuInfo == null) {
            Log.i(TAG, "menuInfo: null")
        }
        return when (item.order) {
            0 -> {
                addOneItems(position, PackageInfo())
                true
            }
            1 -> {
                deleteItem(position)
                Log.i(TAG, "deleting......: ")
                true
            }
            2 -> {
                signAsImportant(position)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun addOneItems(position: Int, packageInfo: PackageInfo) {
        installedPackages.add(position, packageInfo)
        myAdapter!!.notifyItemInserted(position)
    }

    private fun deleteItem(position: Int) {
        Log.i(TAG, "deleteItem: $position")
        installedPackages.removeAt(position)
        myAdapter!!.notifyItemRemoved(position)
        binding!!.recyclerView.itemAnimator = DefaultItemAnimator()
    }

    private fun signAsImportant(position: Int) {}
    override fun startDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper!!.startDrag(viewHolder)
    }

    companion object {
        //    private RecyclerViewItemTouchAdapter myAdapter;
        private const val TAG = "PackageManagerFragment"
    }
}