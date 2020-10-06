package com.example.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tao.ZT.Zhang on 2017/9/21.
 */

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    @BindView(R.id.btn_startService)
    Button btnStartService;
    @BindView(R.id.btn_bindService)
    Button btnBindService;
    @BindView(R.id.btn_unbindService)
    Button btnUnbindService;

    private  MyServiceConnection myServiceConnection = new MyServiceConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_startService, R.id.btn_bindService, R.id.btn_unbindService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startService:
                startService(new Intent(SecondActivity.this, MyService.class));
                break;
            case R.id.btn_bindService:
                bindService(new Intent(SecondActivity.this, MyService.class) , myServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbindService:
                unbindService(myServiceConnection);
                break;
        }
    }



    private static class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder myBinder = (MyService.MyBinder) iBinder;
            MyService myService = (MyService) myBinder.getService();
            Log.i(TAG, "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    }


}
