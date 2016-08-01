package com.example.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Window;
import android.widget.Button;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {
    private final String TAG = SecondActivity.class.getSimpleName();
    @BindView(R.id.btn_click)
    Button btnClick;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        flag = getIntent().getStringExtra("Flag");
        Log.e(TAG, "flag ==== " + flag);
        if (flag.equalsIgnoreCase("explode")) {
            Log.e(TAG, "flag: " + flag);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        } else if (flag.equalsIgnoreCase("slide")) {
            Log.e(TAG, "flag: " + flag);
            getWindow().setEnterTransition(new Slide());
            getWindow().setExitTransition(new Slide());
        } else if (flag.equalsIgnoreCase("fade")) {
            Log.e(TAG, "flag: " + flag);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
        }


        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart: ");
        super.onRestart();
    }


    @Override
    protected void onResume() {
        Log.e(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @OnClick(R.id.btn_click)
    public void onClick() {
        Intent intent = new Intent(this, SecondActivity.class);

        if (flag.equalsIgnoreCase("singleTop")) {
            intent.putExtra("Flag", "singleTop");
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 等同于在secondActivity的lauchMode设置singleTop
            startActivity(intent);
        } else if (flag.equalsIgnoreCase("singleTask")) {
            intent.putExtra("Flag", "singleTask");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 等同于在secondActivity的lauchMode设置singleTask
            startActivity(intent);
        } else if (flag.equalsIgnoreCase("singleInstance")) {
            intent.putExtra("Flag", "singleInstance");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 等同于在secondActivity的lauchMode设置singleInstance
            startActivity(intent);
        } else if (flag.equalsIgnoreCase("normal")) {
            intent.putExtra("Flag", "normal");
            startActivity(intent);
        }




    }
}
