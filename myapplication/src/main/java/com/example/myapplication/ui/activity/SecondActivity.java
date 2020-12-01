package com.example.myapplication.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.HandlerActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivitySecondBinding;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = SecondActivity.class.getSimpleName();
    private String flag;
    private long mExitTime = 0;
    private ActivitySecondBinding binding;

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

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        Log.e(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.btnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_click) {
            Intent intent = new Intent(this, HandlerActivity.class);
            startActivity(intent);
        }


//        if (flag.equalsIgnoreCase("singleTop")) {
//            intent.putExtra("Flag", "singleTop");
//            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);   // 等同于在secondActivity的lauchMode设置singleTop
//            startActivity(intent);
//        } else if (flag.equalsIgnoreCase("singleTask")) {
//            intent.putExtra("Flag", "singleTask");
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // 等同于在secondActivity的lauchMode设置singleTask
//            startActivity(intent);
//        } else if (flag.equalsIgnoreCase("singleInstance")) {
//            intent.putExtra("Flag", "singleInstance");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 等同于在secondActivity的lauchMode设置singleInstance
//            startActivity(intent);
//        } else if (flag.equalsIgnoreCase("normal")) {
//            intent.putExtra("Flag", "normal");
//            startActivity(intent);
//        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mExitTime = System.currentTimeMillis();
            if ((System.currentTimeMillis() - mExitTime) > 1000) {
                showDialog();

            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("确定应用");
        builder.setMessage("你是否确定要退出应用");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }


}
