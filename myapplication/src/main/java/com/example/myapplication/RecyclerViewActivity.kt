package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.ItemAdapter
import com.example.myapplication.databinding.ActivityRecyclerviewBinding
import java.util.*

/**
 * 此页面是为了验证ViewGroup布局变化时的动画效果
 */
class RecyclerViewActivity : BaseActivity<ActivityRecyclerviewBinding>(), View.OnClickListener {
    private val itemList: MutableList<Item> = ArrayList()
    private var mAdapter: ItemAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
    }

    override fun initBinding(): ActivityRecyclerviewBinding {
        return ActivityRecyclerviewBinding.inflate(layoutInflater)
    }

    private fun initView() {
        mAdapter = ItemAdapter(itemList)
        mBinding!!.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding!!.recyclerView.adapter = mAdapter
    }

    override fun onClick(view: View) {}
    private fun initData() {
        for (i in 0..19) {
            itemList.add(Item(i + 1))
        }
        mAdapter!!.notifyDataSetChanged()
    }

    class Item(key: Int) {
        @JvmField
        var key: String

        init {
            this.key = key.toString()
        }
    }

    companion object {
        private const val TAG = "TestActivity"
    }
}