package com.example.servicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.btn_startService1)
    Button btnStartService1;
    @BindView(R.id.btn_startService2)
    Button btnStartService2;
    @BindView(R.id.btn_bindService1)
    Button btnBindService1;
    @BindView(R.id.btn_bindService2)
    Button btnBindService2;
    @BindView(R.id.btn_stopService)
    Button btnStopService;
    @BindView(R.id.btn_unbindService)
    Button btnUnbindService;
    private MyServiceConnection myServiceConnection = new MyServiceConnection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_startService1, R.id.btn_startService2, R.id.btn_bindService1, R.id.btn_bindService2, R.id.btn_stopService, R.id.btn_unbindService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_startService1:
                startService(new Intent(MainActivity.this, MyService.class));
                break;
            case R.id.btn_startService2:
                startService(new Intent(MainActivity.this, MyService.class));
                break;
            case R.id.btn_bindService1:
                bindService(new Intent(MainActivity.this, MyService.class), myServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_bindService2:
                bindService(new Intent(MainActivity.this, MyService.class), myServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_stopService:
                stopService(new Intent(MainActivity.this, MyService.class));
                break;
            case R.id.btn_unbindService:
                unbindService(myServiceConnection);
                break;

        }
    }

    private class MyServiceConnection implements ServiceConnection{

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
