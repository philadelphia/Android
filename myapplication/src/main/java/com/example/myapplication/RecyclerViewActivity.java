package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.adapter.ItemAdapter;
import com.example.myapplication.databinding.ActivityRecyclerviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * 此页面是为了验证ViewGroup布局变化时的动画效果
 */
public class RecyclerViewActivity extends BaseActivity<ActivityRecyclerviewBinding> implements View.OnClickListener {
    private static final String TAG = "TestActivity";
    private List<Item> itemList = new ArrayList<>();
    private ItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected ActivityRecyclerviewBinding initBinding() {
       return ActivityRecyclerviewBinding.inflate(getLayoutInflater());
    }


    private void initView() {
        mAdapter = new ItemAdapter(itemList);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {

    }


    private void initData() {
        for (int i = 0; i < 20; i++) {
            itemList.add(new Item(i + 1));
        }
        mAdapter.notifyDataSetChanged();
    }

    public static class Item {
        public String key;

        public Item(int key) {
            this.key = String.valueOf(key);
        }
    }
}
