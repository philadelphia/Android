package com.example.processkeepalive.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

import com.example.processkeepalive.aidl.IMyAidlInterface;

public class RemoteService extends Service {
    private static final String TAG = "RemoteService";
    private RemoteBinder myBinder;
    private ServiceConnection remoteServiceConnection;

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        if (myBinder == null){
            myBinder = new RemoteBinder();
        }
        remoteServiceConnection = new RemoteServiceConnection();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        bindService(new Intent(RemoteService.this, LocalService.class), remoteServiceConnection, Context.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }


    class RemoteBinder extends IMyAidlInterface.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName() throws RemoteException {
            return    "i am remote Service";
        }
    }

    class  RemoteServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: 本地服务已经链接");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            // 连接出现了异常断开了，RemoteService被杀掉了
            Toast.makeText(RemoteService.this, "本地服务被干掉", Toast.LENGTH_LONG).show();
            // 启动RemoteCastielService
            startService(new Intent(RemoteService.this, LocalService.class));
            bindService(new Intent(RemoteService.this, LocalService.class), remoteServiceConnection, Context.BIND_IMPORTANT);
        }
    }

}
