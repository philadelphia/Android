package com.example.myapplication.ui.fragment.other;


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
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.ui.activity.DialogActivity;
import com.example.myapplication.ui.activity.SecondActivity;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationFragment extends Fragment implements View.OnClickListener {
    private final String TAG = NotificationFragment.class.getSimpleName();
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.btn_sendBigViewNotification)
    Button btnSendBigViewNotification;
    @BindView(R.id.btn_sendcollapse)
    Button btnSendcollapse;
    @BindView(R.id.btn_sendhang)
    Button btnSendhang;
    @BindView(R.id.btn_bigTextStyle)
    Button btnBigTextStyle;
    @BindView(R.id.btn_InboxStyle)
    Button btnInboxStyle;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_image)
    Button btnImage;
    @BindView(R.id.btn_take_photo)
    Button btnTakePhoto;
    @BindView(R.id.btn_take_photo_uri)
    Button btnTakePhotoUri;
    @BindView(R.id.img)
    ImageView imageView;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private Context context;

    private File file;
    public static String fileName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        context = getContext();
        notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(getContext());
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void sendNotification() {
        Log.i(TAG, "sendNotification");
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        Intent intent = new Intent(getContext(), DialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--普通通知");
        builder.setSubText("subText");
        builder.setTicker("ticker");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());
        Notification notification = builder.build();
        notificationManager.notify(12, notification);
    }


    private void sendBigViewNotification() {
        Intent intent = new Intent(getContext(), DialogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Intent dismissIntent = new Intent(getContext(), DialogActivity.class);
//        dismissIntent.setAction(CommonConstants.ACTION_DISMISS);
        PendingIntent piDismiss = PendingIntent.getService(getContext(), 0, dismissIntent, 0);


        Intent snoozeIntent = new Intent(getContext(), DialogActivity.class);
//        snoozeIntent.setAction(CommonConstants.ACTION_SNOOZE);
        PendingIntent piSnooze = PendingIntent.getService(getContext(), 0, snoozeIntent, 0);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,snoozeIntent,0);

//        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--普通通知");
        builder.setSubText("subText");
        builder.setTicker("ticker");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Big"))
                .addAction(R.mipmap.ic_launcher, "Dismiss", piDismiss)
                .addAction(R.mipmap.icon_setting, "snooze", piSnooze);
        Notification notification = builder.build();
        notificationManager.notify(12, notification);
    }

    private void captureAndSendNotification() {
        Log.i(TAG, "captureAndSendNotification: ");
        Bitmap bitmap = capture(getActivity());
        boolean flag = savePic(bitmap);
        Log.i(TAG, "captureAndSendNotification: flag = " + flag);

        sendNotification1();
    }

    public Bitmap capture(Activity pActivity) {
        Log.i(TAG, "capture: ");
        Bitmap bitmap = null;
        View view = pActivity.getWindow().getDecorView();
        // 设置是否可以进行绘图缓存
        view.setDrawingCacheEnabled(true);
        // 如果绘图缓存无法，强制构建绘图缓存
        view.buildDrawingCache();
        // 返回这个缓存视图
        bitmap = view.getDrawingCache();

        // 获取状态栏高度
        Rect frame = new Rect();
        // 测量屏幕宽和高
        view.getWindowVisibleDisplayFrame(frame);
        int statutsHeight = frame.top;
        Log.d(TAG, "状态栏的高度为:" + statutsHeight);

        int width = pActivity.getWindowManager().getDefaultDisplay().getWidth();
        int height = pActivity.getWindowManager().getDefaultDisplay().getHeight();
        // 根据坐标点和需要的宽和高创建bitmap
        bitmap = Bitmap.createBitmap(bitmap, 0, statutsHeight, width, height - statutsHeight);
        return bitmap;
    }

    public boolean savePic(Bitmap pBitmap) {
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
                    return true;
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
        NotificationManager mManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
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
        intent.setDataAndType(Uri.fromFile(file), "image/*");
//        Log.i(TAG, "uri: " + Uri.fromFile(new File("/sdcard/Damily/" + fileName) ));

        Log.i(TAG, "data: " + intent.getData().toString());
//        Bundle bundle=new Bundle();
//        bundle.putString("path",file.getAbsolutePath().toString());
//        intent.putExtras(bundle);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT, null);
        builder.setContentIntent(contentIntent);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        mManager.notify(0, notification);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    private void sendCollapsedNotification() {
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--折叠式通知");
        builder.setSubText("subText");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);
        builder.setContentIntent(pendingIntent);
//        RemoteViews normalContentView = new RemoteViews(context.getPackageName(),R.layout.notification_normal);
//        normalContentView.setTextViewText(R.id.Text,"show me when collapsed!");
        RemoteViews expandedContentView = new RemoteViews(context.getPackageName(), R.layout.notification_big);
        expandedContentView.setTextViewText(R.id.Text, "show me when expanded!");

        Notification notification = builder.build();
//        notification.contentView = normalContentView;
        notification.bigContentView = expandedContentView;

        notificationManager.notify(12, notification);


    }

    private void sendHangNotification() {
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--悬挂式通知");
        builder.setSubText("subText");
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        builder.setCategory(Notification.CATEGORY_MESSAGE);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        push.setClass(getActivity(), SecondActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, push, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(pendingIntent, true);
        builder.setColor(Color.RED);
        notificationManager.notify(12, builder.build());

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

    @Override
    @OnClick({R.id.btn_send, R.id.btn_sendBigViewNotification, R.id.btn_sendcollapse,
            R.id.btn_sendhang, R.id.btn_bigTextStyle, R.id.btn_InboxStyle,
            R.id.btn_cancel, R.id.btn_image, R.id.btn_take_photo, R.id.btn_take_photo_uri})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                sendNotification();
                break;

            case R.id.btn_sendBigViewNotification:
                sendBigViewNotification();
                break;

            case R.id.btn_sendcollapse:
                sendCollapsedNotification();
                break;

            case R.id.btn_sendhang:
                sendHangNotification();
                break;

            case R.id.btn_bigTextStyle:
                sendBigTextStyleNotification();
                break;

            case R.id.btn_InboxStyle:
                sendInboxStyleNotification();
                break;

            case R.id.btn_cancel:
                cancelNotification();
                break;
            case R.id.btn_image:
                captureAndSendNotification();
                break;
            case R.id.btn_take_photo:
                takePhoto();
                break;
            case R.id.btn_take_photo_uri:
                takePhotoWithSpecialUri();
                break;
            default:
                break;
        }
    }

    private void sendInboxStyleNotification() {
        Intent intent = new Intent(getContext(), DialogActivity.class);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--这是一条BigTextStyle通知");
        builder.setSubText("subText");
        builder.setTicker("ticker");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());

        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);
        builder.addAction(R.drawable.ic_launcher, "Call Back", pendingIntent);
        builder.addAction(R.drawable.ic_launcher, "Call History", pendingIntent);

        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(builder);
        style.bigText("你好，这是一个BigTextStyle风格的通知测试，\n 花间一壶酒，独酌无相亲。举杯邀明月，对应成三人。醒时同交欢，醉后各自归，永结无情游，相期邈云汉");

        Notification notification = style.build();

        notificationManager.notify((int) System.currentTimeMillis() / 1000, notification);
    }

    private void sendBigTextStyleNotification() {
        Intent intent = new Intent(getContext(), DialogActivity.class);
        builder.setAutoCancel(true);
        builder.setContentTitle("title");
        builder.setContentText("Content--普通通知");
        builder.setSubText("subText");
        builder.setTicker("ticker");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle(builder);
        style.bigText("Android 4.1 supports expandable notifications. In addition to normal notification view it is possible to define a big view which gets shown when notification is expanded. There are three styles to be used with the big view: big picture style, big text style, Inbox style. The following code demonstrates the usage of the BigTextStyle() which allows to use up to 256 dp.");
        style.setBigContentTitle("big style 通知");
        style.setSummaryText("消息摘要");
        Notification notification = style.build();
        notificationManager.notify(12, notification);
    }

    /**
     * 这种方式发起的拍摄不需要申请权限，因为是调用了系统的Intent，而且返回时会返回一个缩略图
     */
    private void takePhoto() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(intent, 100);
    }

    /**
     * 这种方式发起的拍摄不需要申请权限，而且返回时会返回原图，因为图片太大会引发OOM
     * 所以需要使用URI的方法获取图片的引用
     */
    private void takePhotoWithSpecialUri() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        fileName = getActivity().getCacheDir().toString() + "/" + System.currentTimeMillis()+".jpg";
//        fileName = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + "/" + System.currentTimeMillis()+".jpg";
        fileName = getActivity().getExternalCacheDir().toString() + "/" + System.currentTimeMillis() + ".jpg";
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        Uri uri;
        //在Android7.0(Android N)及以上版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(getActivity(), context.getApplicationContext().getPackageName() + ".provider", file);//通过FileProvider创建一个content类型的Uri
        } else {
            uri = Uri.fromFile(file);
        }
        Log.i(TAG, "takePhotoWithSpecialUri: " + uri.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, 200);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 100:
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null && data.getExtras() != null) {
                        //使用这个方式和使用data.getdata()获取Uri方式得到的图片大小是一样的，
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(bitmap);
                        int byteCount = bitmap.getByteCount();
                        Log.i(TAG, "onActivityResult: bytecount ==" + byteCount / 1024 + "K");
                        //第二种方式取图片，从URI里面取，部分手机取不到URI需要从Content Provider里面取，
                        Uri uri;
                        if (data.getData() != null) {
                            uri = data.getData();
                        } else {
                            uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
                        }
                        //直接使用MediaStore也可以获取Bitmap对象
                        showImage(uri);
                        //第二种方法展示Image View
                        showImage(uri, imageView);
                        Log.i(TAG, "onActivityResult: uri==\t" + uri.toString());
                    }
                }
                break;
            case 200:
                if (resultCode == Activity.RESULT_OK) {
                    File file = new File(fileName);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPurgeable = true;
//                    options.inSampleSize = 2;2
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                    Log.i(TAG, "onActivityResult: byteCount ==" + bitmap.getByteCount() / 1024 / 1024 + "M");
                    imageView.setImageBitmap(bitmap);
                    Log.i(TAG, "bitmap: width =  " + bitmap.getWidth());
                    Log.i(TAG, "bitmap: height =  " + bitmap.getHeight());
                    Log.i(TAG, "imageView: width =  " + imageView.getWidth());
                    Log.i(TAG, "imageView: height=" + imageView.getHeight());
                }
                break;
            default:
        }
    }

    private void showImage(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            imageView.setImageBitmap(bitmap);
            int byteCount = bitmap.getByteCount();
            Log.i(TAG, "showImage: byteCount ==" + byteCount / 1024 + "K");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showImage(Uri uri, ImageView imageView){
        try {
            ParcelFileDescriptor parcelFileDescriptor = getActivity().getContentResolver().openFileDescriptor(uri,"r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            imageView.setImageBitmap(bitmap);
            int byteCount = bitmap.getByteCount();
            Log.i(TAG, "showImage: byteCount ==" + byteCount / 1024 + "K");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
