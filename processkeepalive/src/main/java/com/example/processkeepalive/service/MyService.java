package com.example.processkeepalive.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import com.example.processkeepalive.R;

public class MyService extends Service {
    private static final String TAG = "MyService";
    private int count = 0;
    private PendingIntent pintent;
    private MyBinder binder = new MyBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        return binder;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "run: count == " + count++);
//                    if (count == 200)
//                        stopSelf();
//                        break;
                }
            }
        }).start();

//        Notification notification = new Notification(R.mipmap.ic_launcher,
//                "猴子服务启动中",
//                System.currentTimeMillis());
//        pintent= PendingIntent.getService(this, 0, intent, 0);
//        notification.setLatestEventInfo(this, "猴子服务",
//                "防止被杀掉！", pintent);

        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);


        //设置service为前台进程，避免手机休眠时系统自动杀掉该服务
        startForeground(startId, builder.build());

        return super.onStartCommand(intent, flags, startId);

    }


    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        startService(new Intent(this, MyService.class));
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    class MyBinder extends Binder {

        public void print(){
            Log.i(TAG, "instance bind: " );
        }
    }
}
