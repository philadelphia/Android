package com.example.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.Result;
import com.example.myapplication.network.HttpHelper;
import com.example.myapplication.network.okhttp.HttpCallBack;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NetworkActivity extends AppCompatActivity {
    private String url = "http://c.3g.163.com/photo/api/set/0001%2F2250172.json";
    @BindView(R.id.btn_click)
    Button btnClick;
    @BindView(R.id.content)
    TextView content;
    private Map<String, Object> parmas = new HashMap<>();
    private static final String TAG = "NetworkActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void onViewClicked() {
        HttpHelper.getInstance().post(url, parmas, new HttpCallBack<Result>() {
            @Override
            public void onFailed(String error) {
                super.onFailed(error);
            }

            @Override
            public void onSuccess(Result result) {
                content.setText(result.getPostid());
            }
        });

    }
}
