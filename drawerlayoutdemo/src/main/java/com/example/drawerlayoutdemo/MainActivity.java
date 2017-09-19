package com.example.drawerlayoutdemo;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;

    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(mToolBar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);

        //TextInputLayout
        TextInputLayout textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);

        //开启计数功能
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(10);
        textInputLayout.getEditText().addTextChangedListener(new MinLengthTextWatcher(textInputLayout, "长度应低于6位数"));

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "linearLayout------onTouch: " + event.getAction());
                return false;
            }
        });
        Button button = (Button) findViewById(R.id.click);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "button------onTouch: " + event.getAction());
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "button --- onClick: ");
            }
        });



    }

    static class MinLengthTextWatcher implements TextWatcher {
        private TextInputLayout textInputLayout;
        private String errorString;

        public MinLengthTextWatcher(TextInputLayout textInputLayout, String errorString) {
            this.textInputLayout = textInputLayout;
            this.errorString = errorString;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.i(TAG, "beforeTextChanged: ");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.i(TAG, "onTextChanged: ");
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.i(TAG, "afterTextChanged: ");

            if (textInputLayout.getEditText().getText().toString().trim().length() <= 6) {
                textInputLayout.setErrorEnabled(false);
//                textInputLayout.getEditText().setFilters((new InputFilter[]{new InputFilter.LengthFilter(6)}));
            } else {
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(errorString);
            }

        }
    }

}
