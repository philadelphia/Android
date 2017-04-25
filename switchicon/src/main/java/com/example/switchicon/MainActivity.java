package com.example.switchicon;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.btn_icon1)
    Button btnIcon1;
    @BindView(R.id.btn_icon2)
    Button btnIcon2;
    @BindView(R.id.btn_normal)
    Button btnNormal;
    private ComponentName mDefault;
    private ComponentName mDouble11;
    private ComponentName mDouble12;
    private PackageManager mPm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPm = getApplicationContext().getPackageManager();
        mDefault = getComponentName();
        mDouble11 = new ComponentName(this, "com.example.switchicon.Test11");
        mDouble12 = new ComponentName(this, "com.example.switchicon.Test12");

    }


    @OnClick({R.id.btn_icon1, R.id.btn_icon2, R.id.btn_normal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_icon1:
                disableComponent(mDefault);
                disableComponent(mDouble12);
                enableComponent(mDouble11);
                break;
            case R.id.btn_icon2:
                disableComponent(mDefault);
                disableComponent(mDouble11);
                enableComponent(mDouble12);
                break;
            case R.id.btn_normal:
                disableComponent(mDouble11);
                disableComponent(mDouble12);
                enableComponent(mDefault);
                break;
            default:
                break;

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = mPm.queryIntentActivities(intent, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
