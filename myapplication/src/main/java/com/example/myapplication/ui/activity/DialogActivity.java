package com.example.myapplication.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogActivity extends BaseActivity {


    @BindView(R.id.btn_Dialog)
    Button btnDialog;
    @BindView(R.id.btn_progressDialog)
    Button btnProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }


    @OnClick({R.id.btn_Dialog, R.id.btn_progressDialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_Dialog:
                break;
            case R.id.btn_progressDialog:
                showProgressDialog();
                break;
        }
    }

    private void showProgressDialog(){
        ProgressDialog progressDialog = new ProgressDialog(this);// 创建进度对话框对象
        progressDialog.setTitle("标题"); // 设置标题
        progressDialog.setMessage("加载中..."); // 设置消息
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show(); // 显示进度条
    }
}
