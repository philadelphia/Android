package com.example.activitytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_secondActivity)
    Button btnSecondActivity;
    private static final String TAG = "MainActivity";
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tv_scaleType)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//       img.setScaleType(ImageView.ScaleType.CENTER);
//       img.setScaleType(ImageView.ScaleType.CENTER_CROP);
//       img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//       img.setScaleType(ImageView.ScaleType.FIT_CENTER);
//       img.setScaleType(ImageView.ScaleType.FIT_START);
//       img.setScaleType(ImageView.ScaleType.FIT_END);
//       img.setScaleType(ImageView.ScaleType.FIT_XY);
       img.setScaleType(ImageView.ScaleType.MATRIX);

        textView.setText("Scale Type==" + img.getScaleType());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
        int heightPixels = getResources().getDisplayMetrics().heightPixels;
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        Log.i(TAG, "onResume:heightPixels== " + heightPixels);
        Log.i(TAG, "onResume:widthPixels== " + widthPixels);
        Log.i(TAG, "onResume:xdpi== " + xdpi);
        Log.i(TAG, "onResume:ydpi== " + ydpi);

        int inTargetDensity = getResources().getDisplayMetrics().densityDpi;
        Log.i(TAG, "onResume:inTargetDensity ==  " + inTargetDensity);


        float density = getResources().getDisplayMetrics().density;
        Log.i(TAG, "onResume:density ==  " + density);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @OnClick(R.id.btn_secondActivity)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: ");
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: ");
    }

}
