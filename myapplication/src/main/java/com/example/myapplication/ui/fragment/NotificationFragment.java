package com.example.myapplication.ui.fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.myapplication.R;
import com.example.myapplication.ui.activity.SecondActivity;

public class NotificationFragment extends Fragment implements View.OnClickListener {
    private final  String TAG = NotificationFragment.class.getSimpleName();
    private NotificationManager notificationManager;
    private Notification.Builder builder;
    private Context context;
    private Button btn_send;
    private Button btn_sendCollapse;
    private Button btn_sendHang;

    private Button btn_cancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView");
        context = getContext();

        notificationManager =(NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new Notification.Builder(getActivity());
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        initView(view);
        return  view;
    }

    public void initView(View view){
        btn_send = (Button) view.findViewById(R.id.btn_send);
        btn_cancel = (Button)view.findViewById(R.id.btn_cancel);
        btn_sendHang = (Button) view.findViewById(R.id.btn_sendhang);
        btn_sendCollapse = (Button) view.findViewById(R.id.btn_sendcollapse);

        btn_send.setOnClickListener(this);
        btn_sendCollapse.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_sendHang.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send:
            sendNotification();
                break;

            case R.id.btn_sendcollapse:
                sendCollapsedNotification();
                break;

            case  R.id.btn_sendhang:
                sendHangNotification();
                break;

            case R.id.btn_cancel:
                cancelNotification();
                break;
        }
    }

    private void sendCollapsedNotification() {
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--折叠式通知");
        builder.setSubText("subText");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,intent,0);
        builder.setContentIntent(pendingIntent);
//        RemoteViews normalContentView = new RemoteViews(context.getPackageName(),R.layout.notification_normal);
//        normalContentView.setTextViewText(R.id.Text,"show me when collapsed!");
        RemoteViews expandedContentView = new RemoteViews(context.getPackageName(), R.layout.notification_big);
        expandedContentView.setTextViewText(R.id.Text,"show me when expanded!");

        Notification notification = builder.build();
//        notification.contentView = normalContentView;
        notification.bigContentView = expandedContentView;

        notificationManager.notify(12,notification);


    }

    private void sendHangNotification() {
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--悬挂式通知");
        builder.setSubText("subText");
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        builder.setCategory(Notification.CATEGORY_MESSAGE);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(getActivity(), SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,push,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(pendingIntent,true);
        builder.setColor(Color.RED);
        notificationManager.notify(12, builder.build());

    }


    private void sendNotification() {
        Log.i(TAG,"sendNotification");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,intent,0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--普通通知");
        builder.setSubText("subText");
        builder.setTicker("ticker");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());
        Notification notification = builder.build();
        notificationManager.notify(12, notification);
    }


    private void cancelNotification() {
        notificationManager.cancelAll();
    }

}
