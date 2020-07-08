package com.example.myapplication.ui.activity.lauchMode;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
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
public class ActivityA extends Activity {
    private static final String TAG = "ActivityA";
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_a);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate: this hashCode == " + hashCode());
        Log.i(TAG, "onCreate: current TaskID == " + getTaskId());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent: ");
        Log.i(TAG, "onNewIntent: this hashCode == " + hashCode());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
//        if (activityManager != null){
//            ActivityManager.AppTask appTask = activityManager.getAppTasks().get(0);
//            ActivityManager.RecentTaskInfo taskInfo = appTask.getTaskInfo();
//        }

    }


    @OnClick(R.id.btn)
    public void onViewClicked() {
        Intent intent = new Intent(this, ActivityB.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}
