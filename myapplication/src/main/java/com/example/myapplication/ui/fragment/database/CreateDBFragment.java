package com.example.myapplication.ui.fragment.database;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.db.MyDBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateDBFragment extends Fragment {

    private static final String TAG = "CreateDBFragment";
    private static final  String TABLE_NAME = "app.db";
    @BindView(R.id.btn_createDB)
    Button btnCreateDB;
    @BindView(R.id.btn_deleteDB)
    Button btnDeleteDB;
    @BindView(R.id.btn_modifyDB)
    Button btnModifyDB;
    @BindView(R.id.query_DB)
    Button queryDB;

    private MyDBHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.btn_createDB, R.id.btn_deleteDB,R.id.btn_modifyDB, R.id.query_DB})
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
            case R.id.query_DB:
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
