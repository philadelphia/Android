package com.example.myapplication.ui.fragment;


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.activity.SecondActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationFragment extends Fragment implements View.OnClickListener {
    private final  String TAG = NotificationFragment.class.getSimpleName();
    private NotificationManager notificationManager;
    private Notification.Builder builder;
    private Context context;
    private Button btn_send;
    private Button btn_sendCollapse;
    private Button btn_sendHang;
    private Button btn_cancel;
    private  Button btn_img;
    private File file;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

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
        btn_img = (Button) view.findViewById(R.id.btn_image);

        btn_send.setOnClickListener(this);
        btn_sendCollapse.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        btn_sendHang.setOnClickListener(this);
        btn_img.setOnClickListener(this);
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
            case  R.id.btn_image:
                captureAndSendNotification();
                break;
            default:
                break;
        }
    }

    private void captureAndSendNotification() {
        Log.i(TAG, "captureAndSendNotification: ");
       Bitmap bitmap =  capture(getActivity());
        boolean flag = savePic(bitmap);
        Log.i(TAG, "captureAndSendNotification: flag = " + flag);

        sendNotification1();
    }

    public Bitmap capture(Activity pActivity){
        Log.i(TAG, "capture: ");
        Bitmap bitmap=null;
        View view=pActivity.getWindow().getDecorView();
        // 设置是否可以进行绘图缓存
        view.setDrawingCacheEnabled(true);
        // 如果绘图缓存无法，强制构建绘图缓存
        view.buildDrawingCache();
        // 返回这个缓存视图
        bitmap=view.getDrawingCache();

        // 获取状态栏高度
        Rect frame=new Rect();
        // 测量屏幕宽和高
        view.getWindowVisibleDisplayFrame(frame);
        int stautsHeight=frame.top;
        Log.d("jiangqq", "状态栏的高度为:"+stautsHeight);

        int width=pActivity.getWindowManager().getDefaultDisplay().getWidth();
        int height=pActivity.getWindowManager().getDefaultDisplay().getHeight();
        // 根据坐标点和需要的宽和高创建bitmap
        bitmap=Bitmap.createBitmap(bitmap, 0, stautsHeight, width, height-stautsHeight);
        return bitmap;
    }

    public boolean savePic(Bitmap pBitmap){
        if (pBitmap == null) {
            Log.i(TAG, "savePic: btimap == null");
        }
        Log.i(TAG, "savePic: ");
        File appDir = new File(Environment.getExternalStorageDirectory(), "Damily");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
        String fileName = sdf.format(new Date()) + ".jpg";
//       String fileName = System.currentTimeMillis() + ".jpg";
        file = new File(appDir, fileName);
        Log.i(TAG, "filepath : " + file.getAbsolutePath().toString());
        System.out.println(appDir.toString());
        if (pBitmap != null) {

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                pBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                try {
                    fos.flush();
                    fos.close();
                    Log.i(TAG, "savePic: write over!");
                    return  true;
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                    appDir.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
////         最后通知图库更新
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(Uri.fromFile(new File("/sdcard/Damily/" + fileName)));
        getActivity().sendBroadcast(scanIntent);
        sendNotification1();
        return false;


        }

    private void sendNotification1() {
        Log.i(TAG, "sendNotification1: ");
        NotificationManager mManager = (NotificationManager)getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(getContext());

        builder.setTicker("正在截屏...");
        builder.setContentTitle("已捕捉屏幕截图");
        builder.setContentText("点击以查看截屏");
        builder.setWhen(System.currentTimeMillis());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeFile(file.getPath()));

        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("image/*");
        intent.setDataAndType( Uri.fromFile(file), "image/*");
//        Log.i(TAG, "uri: " + Uri.fromFile(new File("/sdcard/Damily/" + fileName) ));

        Log.i(TAG, "data: " + intent.getData().toString());
//        Bundle bundle=new Bundle();
//        bundle.putString("path",file.getAbsolutePath().toString());
//        intent.putExtras(bundle);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, intent,PendingIntent.FLAG_ONE_SHOT,null);
        builder.setContentIntent(contentIntent);
        builder.setAutoCancel(true);

       Notification notification = builder.build();
        mManager.notify(0, notification);
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.mymenu, menu);
        menu.removeItem(menu.size() - 1);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(getContext(), "imte1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(getContext(), "imte2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(getContext(), "imte3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                Toast.makeText(getContext(), "imte4", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
