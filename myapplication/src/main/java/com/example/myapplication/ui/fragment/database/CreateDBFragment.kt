package com.example.myapplication.ui.fragment.database

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBlank2Binding
import com.example.myapplication.db.MyDBHelper

/**
 * A simple [Fragment] subclass.
 */
class CreateDBFragment : Fragment(), View.OnClickListener {
    private lateinit var dbHelper: MyDBHelper
    private lateinit var binding: FragmentBlank2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBlank2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.btnCreateDB.setOnClickListener(this)
        binding.btnDeleteDB.setOnClickListener(this)
        binding.btnModifyDB.setOnClickListener(this)
        binding.btnQueryDB.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        dbHelper = MyDBHelper.getInstance(context)
        when (view.id) {
            R.id.btn_createDB -> {
                Log.i(TAG, "onClick: btn_createDB")
                createDB()
            }
            R.id.btn_deleteDB -> {
                Log.i(TAG, "onClick: btn_deleteDB")
                deleteBD()
            }
            R.id.btn_modifyDB -> {
                Log.i(TAG, "onClick: btn_modifyDB")
                btnModifyDB()
            }
            R.id.btn_queryDB -> {
                Log.i(TAG, "onClick: query_DB")
                queryDB()
            }
            else -> {
            }
        }
    }

    private fun createDB() {
        dbHelper.writableDatabase
    }

    private fun deleteBD() {
        dbHelper.writableDatabase?.execSQL("")
    }

    private fun btnModifyDB() {}
    private fun queryDB() {}

    companion object {
        private const val TAG = "CreateDBFragment"
        private const val TABLE_NAME = "app.db"
    }
}