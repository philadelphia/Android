package com.example.myapplication.ui.fragment.database;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentBlank2Binding;
import com.example.myapplication.db.MyDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateDBFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "CreateDBFragment";
    private static final String TABLE_NAME = "app.db";


    private MyDBHelper dbHelper;

    private FragmentBlank2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBlank2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        binding.btnCreateDB.setOnClickListener(this);
        binding.btnDeleteDB.setOnClickListener(this);
        binding.btnModifyDB.setOnClickListener(this);
        binding.btnQueryDB.setOnClickListener(this);
    }

    public void onClick(View view) {
        dbHelper = MyDBHelper.getInstance(getContext());
        switch (view.getId()) {
            case R.id.btn_createDB:
                Log.i(TAG, "onClick: btn_createDB");
                createDB();
                break;
            case R.id.btn_deleteDB:
                Log.i(TAG, "onClick: btn_deleteDB");
                deleteBD();
                break;
            case R.id.btn_modifyDB:
                Log.i(TAG, "onClick: btn_modifyDB");
                btnModifyDB();
                break;
            case R.id.btn_queryDB:
                Log.i(TAG, "onClick: query_DB");
                queryDB();
                break;
            default:
                break;
        }
    }


    public void createDB() {
        dbHelper.getWritableDatabase();
    }

    private void deleteBD() {
        dbHelper.getWritableDatabase().execSQL("");
    }

    private void btnModifyDB() {
    }

    private void queryDB() {
    }
}
