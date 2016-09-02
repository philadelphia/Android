package com.example.myapplication.ui.manager;


import android.content.ClipData;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

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
    //    private  RecyclerView recyclerView;


    private static final String TAG = "PackageManagerFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "onCreateView: ");
        final View view = inflater.inflate(R.layout.fragment_package_manager, null);
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
                break;
            case 1:
                signAsImportant();
                break;
            case 2:
                deleteItem(position);
                Log.i(TAG, "deleting......: ");
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
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
