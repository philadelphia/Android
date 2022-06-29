package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentShareBinding
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ShareFragment : Fragment() {
    private lateinit var binding: FragmentShareBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentShareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.btnTest.setOnClickListener { view: View? -> onViewClicked() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun onViewClicked() {
//
        val list: MutableList<BroadBandEntity> = ArrayList()
        val broadBandEntity1 = BroadBandEntity("1", "123")
        val broadBandEntity2 = BroadBandEntity("1", "123")
        val broadBandEntity3 = BroadBandEntity("1", "123")
        list.add(broadBandEntity1)
        list.add(broadBandEntity2)
        list.add(broadBandEntity3)
        val broadBandEntity = BroadBandEntity("1", "123")

//        最后会报错： java.lang.NoClassDefFoundError: java.util.Objects
//        因为Objects类实在JDK1.7才引入的。但是Android4.0（16）的手机自带的JDK是1.6版本的。所以....
        Log.i(TAG, "onViewClicked: " + list.contains(broadBandEntity1))
        Log.i(TAG, "onViewClicked: " + (broadBandEntity == broadBandEntity1))
    }

    class BroadBandEntity(var broadband_id: String, var item_name: String) {

        override fun equals(o: Any?): Boolean {
            if (this === o) return true
            if (o == null || javaClass != o.javaClass) return false
            val that = o as BroadBandEntity
            return broadband_id == that.broadband_id &&
                    item_name == that.item_name
        }

        override fun hashCode(): Int {
            return Objects.hash(broadband_id, item_name)
        }

    }

    companion object {
        private const val TAG = "ShareFragment"
    }
}