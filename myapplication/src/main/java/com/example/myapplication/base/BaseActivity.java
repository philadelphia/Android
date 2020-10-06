package com.example.myapplication.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        binder = ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
    }

    protected abstract int getLayoutId();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binder != null){
            binder.unbind();
        }
    }
}
