package com.example.myapplication.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Tao.ZT.Zhang
 */

public class AndroidBaseViewFragment extends Fragment {
    private static final String TAG = "AndroidBaseViewFragment";
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView: ");
        MainActivity.getToolbar().setTitle("Android 基本组件");
        View view = inflater.inflate(R.layout.fragment_android_base_view, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.tv1, R.id.tv2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv1:
                break;
            case R.id.tv2:
                break;
        }
    }
}
