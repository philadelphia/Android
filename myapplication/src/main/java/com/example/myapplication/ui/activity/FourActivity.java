package com.example.myapplication.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.WindowManager;
import android.widget.Button;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FourActivity extends AppCompatActivity {

    @BindView(R.id.btn_screenShot)
    Button btnScreenShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_screenShot)
    public void onClick() {
    }
}
