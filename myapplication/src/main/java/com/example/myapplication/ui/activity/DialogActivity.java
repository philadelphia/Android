package com.example.myapplication.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.databinding.ActivityDialogBinding;

public class DialogActivity extends BaseActivity implements View.OnClickListener {
    private ActivityDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDialogBinding.inflate(getLayoutInflater());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dialog;
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnDialog.setOnClickListener(this);
        binding.btnProgressDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Dialog:
                break;

            case R.id.btn_progressDialog:
                showProgressDialog();
                break;

            default:
        }
    }

    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);// 创建进度对话框对象
        progressDialog.setTitle("标题"); // 设置标题
        progressDialog.setMessage("加载中..."); // 设置消息
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show(); // 显示进度条
    }
}
