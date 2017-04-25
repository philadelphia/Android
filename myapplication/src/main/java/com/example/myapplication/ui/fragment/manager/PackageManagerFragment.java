package com.example.myapplication.ui.fragment.manager;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.myapplication.R;
import com.example.myapplication.adapter.RecyclerViewAdapter;
import com.example.myapplication.utils.CustomItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackageManagerFragment extends Fragment implements CustomItemClickListener {
    private PackageManager packageManager;
    private List<PackageInfo> installedPackages = new ArrayList<>();
    private List<String> pkgNameList;
    private RecyclerViewAdapter myAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private static final String TAG = "PackageManagerFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_package_manager, container,false);
        packageManager = getContext().getPackageManager();
        ButterKnife.bind(this, view);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        myAdapter = new RecyclerViewAdapter(installedPackages);
        myAdapter.setOnCustomeItemClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayout.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        registerForContextMenu(recyclerView);
        recyclerView.setAdapter(myAdapter);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            private RecyclerViewAdapter.MyViewHolder viewHolder;
            @Override
            public boolean isItemViewSwipeEnabled() {
                return  true;
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return  true;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                int swipFlags = ItemTouchHelper.START | ItemTouchHelper.END;

                return makeMovementFlags(dragFlags, swipFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Collections.swap(installedPackages, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                myAdapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                installedPackages.remove(viewHolder.getAdapterPosition());
                myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                //滑动时自己实现背景及图片
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    //dX大于0时向右滑动，小于0向左滑动
                    View itemView = viewHolder.itemView;//获取滑动的view
                    Resources resources = getContext().getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher);//获取删除指示的背景图片
                    int padding = 10;//图片绘制的padding
                    int maxDrawWidth = 2 * padding + bitmap.getWidth();//最大的绘制宽度
                    Paint paint = new Paint();
//                    paint.setColor(resources.getColor(R.color.btninvalid));
                    int x = Math.round(Math.abs(dX));
                    int drawWidth = Math.min(x, maxDrawWidth);//实际的绘制宽度，取实时滑动距离x和最大绘制距离maxDrawWidth最小值
                    int itemTop = itemView.getBottom() - itemView.getHeight();//绘制的top位置
                    //向右滑动
                    if (dX > 0) {
                        //根据滑动实时绘制一个背景
                        c.drawRect(itemView.getLeft(), itemTop, drawWidth, itemView.getBottom(), paint);
                        //在背景上面绘制图片
                        if (x > padding) {//滑动距离大于padding时开始绘制图片
                            //指定图片绘制的位置
                            Rect rect = new Rect();//画图的位置
                            rect.left = itemView.getLeft() + padding;
                            rect.top = itemTop + (itemView.getBottom() - itemTop - bitmap.getHeight()) / 2;//图片居中
                            int maxRight = rect.left + bitmap.getWidth();
                            rect.right = Math.min(x, maxRight);
                            rect.bottom = rect.top + bitmap.getHeight();
                            //指定图片的绘制区域
                            Rect rect1 = null;
                            if (x < maxRight) {
                                rect1 = new Rect();//不能再外面初始化，否则dx大于画图区域时，删除图片不显示
                                rect1.left = 0;
                                rect1.top = 0;
                                rect1.bottom = bitmap.getHeight();
                                rect1.right = x - padding;
                            }
                            c.drawBitmap(bitmap, rect1, rect, paint);
                        }
                        float alpha = 1.0f - Math.abs(dX) / (float) itemView.getWidth();
                        itemView.setAlpha(alpha);
                        //绘制时需调用平移动画，否则滑动看不到反馈
                        itemView.setTranslationX(dX);
                    } else {
                        //如果在getMovementFlags指定了向左滑动（ItemTouchHelper。START）时则绘制工作可参考向右的滑动绘制，也可直接使用下面语句交友系统自己处理
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }
                } else {
                    //拖动时有系统自己完成
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }

        }).attachToRecyclerView(recyclerView);

        initData();
        return view;

    }

    public void initData() {
        Log.i(TAG, "initData: ");
        installedPackages.clear();
        installedPackages.addAll(packageManager.getInstalledPackages(0));
        myAdapter.notifyDataSetChanged();
        Log.i(TAG, "installedPackages: " + installedPackages.size());


    }

    @OnClick(R.id.recyclerView)
    public void onClick() {

    }

    @Override
    public void onItemClick(View v, int position) {
        Snackbar.make(recyclerView, "ddd", Snackbar.LENGTH_SHORT).show();
//        Log.i(TAG, "onItemClick: " + position);
//        PopupMenu menu = new PopupMenu(getContext(), v,Gravity.CENTER);
//        menu.inflate(R.menu.popup_menu);
//        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return true;
//            }
//        });
////
////
//////        menu.setGravity(Gravity.CENTER);
//        menu.show();


    }

    @Override
    public void onItemLongClick(View v, int position) {

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
    public boolean onContextItemSelected(MenuItem item) {
        if (item == null) {
            Log.i(TAG, "onContextItemSelected: item == null");
        }
        int position = -1;
        position = myAdapter.getPosition();
        Log.i(TAG, "position == :"+ position);
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (menuInfo == null) {
            Log.i(TAG, "menuInfo: null");

        }
        switch (item.getItemId()) {
            case 0:
                addOneItems(position, new PackageInfo());
                return  true;
            case 1:
                signAsImportant();
                return  true;
            case 2:
                deleteItem(position);
                Log.i(TAG, "deleting......: ");
                return  true;
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


    private void signAsImportant() {
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unregisterForContextMenu(recyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
