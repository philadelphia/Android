package com.example.processkeepalive.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.processkeepalive.aidl.IMyAidlInterface;

public class LocalService extends Service {
    private static final String TAG = "LocalService";
    private  IBinder binder ;
    private MyServiceConnection myServiceConnection;

    public LocalService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return  binder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        if (binder == null){
            binder = new LocalBinder();
        }
        myServiceConnection = new MyServiceConnection();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        bindService(new Intent(LocalService.this, RemoteService.class), myServiceConnection, Context.BIND_IMPORTANT);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    class LocalBinder extends IMyAidlInterface.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName() throws RemoteException {
            return "i am localService";
        }
    }

    class MyServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            String serviceName = null;
            try {
                serviceName =   IMyAidlInterface.Stub.asInterface(iBinder).getName();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
//            IMyAidlInterface.Stub.asInterface(iBinder);
            Log.i(TAG, serviceName + ":----onServiceConneted: 远程服务连接上了");

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: 远程服务断开了");
            // 连接出现了异常断开了，RemoteService被杀掉了
            Toast.makeText(LocalService.this, "远程服务被干掉", Toast.LENGTH_LONG).show();
            startService(new Intent(LocalService.this, RemoteService.class));
            bindService(new Intent(LocalService.this, RemoteService.class), myServiceConnection, Context.BIND_IMPORTANT);
        }
    }

}
