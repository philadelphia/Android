package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityNetworkBinding;
import com.example.myapplication.entity.Result;
import com.example.myapplication.network.HttpHelper;
import com.example.myapplication.network.okhttp.HttpCallBack;

import java.util.HashMap;
import java.util.Map;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {
    private String url = "http://c.3g.163.com/photo/api/set/0001%2F2250172.json";
    private Map<String, Object> parmas = new HashMap<>();
    private static final String TAG = "NetworkActivity";
    private ActivityNetworkBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetworkBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_click)
            HttpHelper.getInstance().post(url, parmas, new HttpCallBack<Result>() {
                @Override
                public void onFailed(String error) {
                    super.onFailed(error);
                }

                @Override
                public void onSuccess(Result result) {
                    binding.content.setText(result.getPostid());
                }
            });

    }
}
