package com.example.tablayoutdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {

    @BindView(R.id.btn_default)
    Button btnDefault;
    @BindView(R.id.btn_scrollableTablayout)
    Button btnScrollableTablayout;
    @BindView(R.id.btn_iconandFonts)
    Button btnIconandFonts;
    @BindView(R.id.btn_iconOnly)
    Button btnIconOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @OnClick({R.id.btn_default, R.id.btn_scrollableTablayout, R.id.btn_iconandFonts, R.id.btn_iconOnly})
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (view.getId()) {
            case R.id.btn_default:
                intent.putExtra("Flag" , 1);
                startActivity(intent);
                break;
            case R.id.btn_scrollableTablayout:
                intent.putExtra("Flag" , 2);
                startActivity(intent);
                break;
            case R.id.btn_iconandFonts:
                intent.putExtra("Flag" , 3);
                startActivity(intent);
                break;
            case R.id.btn_iconOnly:
                intent.putExtra("Flag" , 4);
                startActivity(intent);
                break;
        }
    }
}
