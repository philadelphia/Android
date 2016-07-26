package com.example.myapplication.ui.fragment;


import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;

public class NotificationFragment extends Fragment implements View.OnClickListener {
    private NotificationManager notificationManager;
    private Context context;
    private Button btn_send;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        notificationManager =(NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        return  view;
    }

    public void initView(View view){
        btn_send = (Button) view.findViewById(R.id.btn_send);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send:
            sendNotification();
                break;
        }
    }

    private void sendNotification() {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setTicker("dd");
        Notification notification = builder.build();
        notificationManager.notify(System.currentTimeMillis(), notification);
    }
}
