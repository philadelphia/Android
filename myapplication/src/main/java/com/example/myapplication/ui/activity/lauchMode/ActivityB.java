package com.example.myapplication.ui.activity.lauchMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author zhangtao
 * @date 2019-11-18
 **/
public class ActivityB extends Activity {
    private static final String TAG = "ActivityB";
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_b);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate: current TaskID" + getTaskId());
        Log.i(TAG, "onCreate: this hashCode == " + hashCode());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
        Log.i(TAG, "onCreate: this hashCode == " + hashCode());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        Intent intent = new Intent(this, ActivityA.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
