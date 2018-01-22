package com.example.myapplication.ui.fragment.manager;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.ui.activity.ThirdActivity;
import com.example.myapplication.utils.ItemMoveCallBack;
import com.example.myapplication.utils.ItemSwipeCallBack;
import com.example.myapplication.utils.ItemTouchHelper;
import com.example.myapplication.utils.MessageItemTouchCallBack;
import com.example.myapplication.utils.OnRecyclerViewItemClickListener;
import com.example.myapplication.utils.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackageManagerFragment extends Fragment implements OnStartDragListener {
    private PackageManager packageManager;
    private List<PackageInfo> installedPackages = new ArrayList<>();
    private List<String> pkgNameList;
    private RecyclerViewAdapter myAdapter;
//    private RecyclerViewItemTouchAdapter myAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.flab)
    FloatingActionButton flb;
//    @BindView(R.id.coorlayout)
//    CoordinatorLayout coorlayout;

    private static final String TAG = "PackageManagerFragment";
    private ActionMode actionMode;
    private ItemTouchHelper itemTouchHelper;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        final View view = inflater.inflate(R.layout.fragment_package_manager, container, false);
        packageManager = getContext().getPackageManager();
        ButterKnife.bind(this, view);
        ((MainActivity) getActivity()).getFloatingActionBar().setVisibility(View.GONE);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);

//        myAdapter = new RecyclerViewItemTouchAdapter(installedPackages);
        myAdapter = new RecyclerViewAdapter(installedPackages,this);
        recyclerView.setAdapter(myAdapter);
//        recyclerView.addItemDecoration(new MyItemDecoration(getActivity(),LinearLayoutManager.VERTICAL, R.drawable.itemdecoration));
//        recyclerView.addItemDecoration(new MyItemDecoration1(LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new MyItemDecoration2(LinearLayoutManager.VERTICAL));
//        recyclerView.addItemDecoration(new TimeLineItemDecoration());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


//        itemTouchHelper = new ItemTouchHelper(new ItemTouchCallBack(getActivity(), myAdapter));
//        MessageItemTouchCallBack itemTouchCallBack = new MessageItemTouchCallBack(myAdapter);
//        ItemSwipeCallBack itemTouchCallBack = new ItemSwipeCallBack(getContext(),myAdapter);
        ItemMoveCallBack itemTouchCallBack = new ItemMoveCallBack(getContext(),myAdapter);
        itemTouchHelper = new ItemTouchHelper(itemTouchCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnItemTouchListener(new OnRecyclerViewItemClickListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                Snackbar.make(recyclerView, "ddd", Snackbar.LENGTH_SHORT).show();
                Log.i(TAG, "onItemClick: " + viewHolder.getLayoutPosition());
                Log.i(TAG, "onItemClick: " + viewHolder.hashCode());

            }

            @Override
            public void onItemLongPress(RecyclerView.ViewHolder viewHolder) {
//                第一条数据不支持长按拖拽，其他的都可以
                if (viewHolder.getLayoutPosition() != 0) {
                    itemTouchHelper.startDrag(viewHolder);
                }
            }
        });
//        registerForContextMenu(recyclerView);

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
        flb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ThirdActivity.class);
                int[] location = new int[2];
                //获取FloatingActionButton的位置
                v.getLocationOnScreen(location);

//                location[0] += location[0] + flb.getWidth() / 2;
                intent.putExtra("location", location);
                startActivity(intent);
                getActivity().overridePendingTransition(0,0);

            }
        });

//        setOnSrollListener();

        initData();
        return view;

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

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView: ");
        super.onDestroyView();
        unregisterForContextMenu(recyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        if (actionMode != null) {
            actionMode.finish();
            actionMode = null;
        }
    }


    protected class MyOnItemTouchHelperCallBack extends Callback {


        public MyOnItemTouchHelperCallBack() {

        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            //默认都不能被长安拖拽，然后再ItemTouchListener 中处理
            return false;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }

            int swipeFlags = ItemTouchHelper.END;
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Log.i(TAG, "onMove: ");
            //第一条数据不能被别的数据挤走
            if (target.getLayoutPosition() == 0) return false;
            Collections.swap(installedPackages, viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        @Override
        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
            Log.i(TAG, "onMoved: ");
            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
            recyclerView.getAdapter().notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Log.i(TAG, "onSwiped: ");
            installedPackages.remove(viewHolder.getAdapterPosition());
            myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);
            Log.i(TAG, "onSelectedChanged: actionState ==" + actionState);
            if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {

                viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }

        }


        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            //滑动时自己实现背景及图片
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                //dX大于0时向右滑动，小于0向左滑动
                View backGroundView = viewHolder.itemView.findViewById(R.id.item_background);//获取滑动的view
                View contentView = viewHolder.itemView.findViewById(R.id.itemContent);//获取滑动的view
//                //向右滑动
                if (dX > 0) {
                    //根据滑动实时绘制一个背景
                    float alpha = 1.0f - Math.abs(dX) / (float) contentView.getWidth();
                    contentView.setAlpha(alpha);
                    //绘制时需调用平移动画，否则滑动看不到反馈
                    contentView.setTranslationX(dX);
                    if (dX < contentView.getWidth() / 2){
                        viewHolder.itemView.setTranslationX(contentView.getWidth() / 2);
                        View view ;

                    }
                } else {
                    //如果在getMovementFlags指定了向左滑动（ItemTouchHelper。START）时则绘制工作可参考向右的滑动绘制，也可直接使用下面语句交友系统自己处理
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            } else {
                //拖动时有系统自己完成
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }


    }

    public void initData() {
        Log.i(TAG, "initData: ");
        installedPackages.clear();
        installedPackages.addAll(packageManager.getInstalledPackages(0));
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            Log.i(TAG, "packageName ===: " + packageInfo.packageName);
            Log.i(TAG, "icon=== : " + packageInfo.applicationInfo.icon);
        }
        myAdapter.notifyDataSetChanged();
        Log.i(TAG, "installedPackages: " + installedPackages.size());

    }

    @Override
    public void registerForContextMenu(View view) {
        Log.i(TAG, "registerForContextMenu: ");
        super.registerForContextMenu(view);
    }

    @Override
    public void unregisterForContextMenu(View view) {
        super.unregisterForContextMenu(view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, v.getId(), 0, "Add");//groupId, itemId, order, title
        menu.add(0, v.getId(), 1, "Delete");
        menu.add(0, v.getId(), 2, "Mark");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item == null) {
            Log.i(TAG, "onContextItemSelected: item == null");
        }
        int position = 34;
//        int position = recyclerView.getAdapter().get
//        Log.i(TAG, "position == :" + position);
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (menuInfo == null) {
            Log.i(TAG, "menuInfo: null");

        }
        switch (item.getOrder()) {
            case 0:
                addOneItems(position, new PackageInfo());
                return true;

            case 1:
                deleteItem(position);
                Log.i(TAG, "deleting......: ");
                return true;
            case 2:
                signAsImportant(position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void addOneItems(int position, PackageInfo packageInfo) {
        installedPackages.add(position, packageInfo);
        myAdapter.notifyItemInserted(position);

    }

    private void deleteItem(int position) {
        Log.i(TAG, "deleteItem: " + position);
        installedPackages.remove(position);
        myAdapter.notifyItemRemoved(position);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private void signAsImportant(int position) {

    }

    @Override
    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
