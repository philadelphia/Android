package com.example.myapplication.ui.fragment.other

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.FileProvider
import com.example.myapplication.R
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentNotificationBinding
import com.example.myapplication.ui.activity.DialogActivity
import com.example.myapplication.ui.activity.SecondActivity
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "NotificationFragment"

class NotificationFragment : BaseFragment(), View.OnClickListener {
    private lateinit var notificationManager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder
    private var file: File? = null
    private lateinit var viewBinding: FragmentNotificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        notificationManager = mActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        builder = NotificationCompat.Builder(mActivity, CHANNEL_NORMAL_NOTIFICATION)
        viewBinding = FragmentNotificationBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_notification
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListener()
    }

    private fun setClickListener() {
        viewBinding.btnCancel.setOnClickListener { view: View -> onClick(view) }
        viewBinding.btnCreateNotificationChannel.setOnClickListener(this)
        viewBinding.btnDeleteNotificationChannel.setOnClickListener(this)
        viewBinding.btnSend.setOnClickListener(this)
        viewBinding.btnSendMessage.setOnClickListener(this)
        viewBinding.btnBigTextStyle.setOnClickListener(this)
        viewBinding.btnInboxStyle.setOnClickListener(this)
        viewBinding.btnSendBigViewNotification.setOnClickListener(this)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = mActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannelNormal = NotificationChannel(CHANNEL_NORMAL_NOTIFICATION, "普通通知", NotificationManager.IMPORTANCE_LOW)
            val notificationChannelImportant = NotificationChannel(CHANNEL_IMPORTANT_NOTIFICATION, "重要通知", NotificationManager.IMPORTANCE_LOW)
            notificationManager.createNotificationChannel(notificationChannelNormal)
            notificationManager.createNotificationChannel(notificationChannelImportant)
        }
    }

    private fun sendNotification() {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        val intent = Intent(getContext(), DialogActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(activity, 0, intent, 0)
        builder.setContentIntent(pendingIntent)
        builder.setAutoCancel(true)
        builder.setContentTitle("title")
        builder.setContentText("Content--普通通知")
        builder.setSubText("subText")
        builder.setTicker("ticker")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
        builder.setWhen(System.currentTimeMillis())
        val notification = builder.build()
        notificationManager.notify(1, notification)
    }

    private fun sendImportantNotification() {
        val intent = Intent(getContext(), DialogActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(activity, 0, intent, 0)
        builder.setChannelId(CHANNEL_IMPORTANT_NOTIFICATION)
        builder.setContentIntent(pendingIntent)
        builder.setAutoCancel(true)
        builder.setContentTitle("title")
        builder.setContentText("这是一条重要通知")
        builder.setSubText("subText")
        builder.setTicker("ticker")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
        builder.setWhen(System.currentTimeMillis())
        val notification = builder.build()
        notificationManager.notify(2, notification)
    }

    private fun sendBigViewNotification() {
        val intent = Intent(getContext(), DialogActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val dismissIntent = Intent(getContext(), DialogActivity::class.java)
        //        dismissIntent.setAction(CommonConstants.ACTION_DISMISS);
        val piDismiss = PendingIntent.getService(getContext(), 0, dismissIntent, 0)
        val snoozeIntent = Intent(getContext(), DialogActivity::class.java)
        //        snoozeIntent.setAction(CommonConstants.ACTION_SNOOZE);
        val piSnooze = PendingIntent.getService(getContext(), 0, snoozeIntent, 0)
        //        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(),0,snoozeIntent,0);

//        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true)
        builder.setContentTitle("title")
        builder.setContentText("Content--普通通知")
        builder.setSubText("subText")
        builder.setTicker("ticker")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
        builder.setWhen(System.currentTimeMillis())
                .setStyle(NotificationCompat.BigTextStyle()
                        .bigText("Big"))
                .addAction(R.mipmap.ic_launcher, "Dismiss", piDismiss)
                .addAction(R.mipmap.icon_setting, "snooze", piSnooze)
        val notification = builder.build()
        notificationManager.notify(12, notification)
    }

    private fun captureAndSendNotification() {
        Log.i(TAG, "captureAndSendNotification: ")
        val bitmap = capture(activity)
        val flag = savePic(bitmap)
        Log.i(TAG, "captureAndSendNotification: flag = $flag")
        sendNotification1()
    }

    fun capture(pActivity: Activity?): Bitmap? {
        Log.i(TAG, "capture: ")
        var bitmap: Bitmap? = null
        val view = pActivity?.window?.decorView
        // 设置是否可以进行绘图缓存
        view?.isDrawingCacheEnabled = true
        // 如果绘图缓存无法，强制构建绘图缓存
        view?.buildDrawingCache()
        // 返回这个缓存视图
        bitmap = view?.drawingCache

        // 获取状态栏高度
        val frame = Rect()
        // 测量屏幕宽和高
        view?.getWindowVisibleDisplayFrame(frame)
        val statutsHeight = frame.top
        Log.d(TAG, "状态栏的高度为:$statutsHeight")
        val width = pActivity?.windowManager?.defaultDisplay?.width
        val height = pActivity?.windowManager?.defaultDisplay?.height
        // 根据坐标点和需要的宽和高创建bitmap
        bitmap = Bitmap.createBitmap(bitmap!!, 0, statutsHeight, width!!, height!! - statutsHeight)
        return bitmap
    }

    fun savePic(pBitmap: Bitmap?): Boolean {
        if (pBitmap == null) {
            Log.i(TAG, "savePic: btimap == null")
        }
        Log.i(TAG, "savePic: ")
        val appDir = File(Environment.getExternalStorageDirectory(), "Damily")
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val sdf = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US)
        val fileName = sdf.format(Date()) + ".jpg"
        //       String fileName = System.currentTimeMillis() + ".jpg";
        file = File(appDir, fileName)
        Log.i(TAG, "filepath : " + file?.absolutePath.toString())
        println(appDir.toString())
        if (pBitmap != null) {
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(file)
                pBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                try {
                    fos.flush()
                    fos.close()
                    Log.i(TAG, "savePic: write over!")
                    return true
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(mActivity.contentResolver,
                    appDir.absolutePath, fileName, null)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        ////         最后通知图库更新
        val scanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        scanIntent.data = Uri.fromFile(File("/sdcard/Damily/$fileName"))
        mActivity.sendBroadcast(scanIntent)
        sendNotification1()
        return false
    }

    private fun sendNotification1() {
        Log.i(TAG, "sendNotification1: ")
        val mManager = mActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = Notification.Builder(getContext())
        builder.setTicker("正在截屏...")
        builder.setContentTitle("已捕捉屏幕截图")
        builder.setContentText("点击以查看截屏")
        builder.setWhen(System.currentTimeMillis())
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeFile(file?.path))
        val intent = Intent(Intent.ACTION_VIEW)
        //        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.addCategory("android.intent.category.DEFAULT")
        intent.type = "image/*"
        intent.setDataAndType(Uri.fromFile(file), "image/*")
        //        Log.i(TAG, "uri: " + Uri.fromFile(new File("/sdcard/Damily/" + fileName) ));
        Log.i(TAG, "data: " + intent.data.toString())
        //        Bundle bundle=new Bundle();
//        bundle.putString("path",file.getAbsolutePath().toString());
//        intent.putExtras(bundle);
        val contentIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT, null)
        builder.setContentIntent(contentIntent)
        builder.setAutoCancel(true)
        val notification = builder.build()
        mManager.notify(0, notification)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    private fun sendCollapsedNotification() {
        builder.setAutoCancel(true)
        builder.setContentTitle("title")
        builder.setContentText("Content--折叠式通知")
        builder.setSubText("subText")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
        builder.setWhen(System.currentTimeMillis())

//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        val intent = Intent()
        val pendingIntent = PendingIntent.getActivity(activity, 0, intent, 0)
        builder.setContentIntent(pendingIntent)
        //        RemoteViews normalContentView = new RemoteViews(context.getPackageName(),R.layout.notification_normal);
//        normalContentView.setTextViewText(R.id.Text,"show me when collapsed!");
        val expandedContentView = RemoteViews(mActivity.packageName, R.layout.notification_big)
        expandedContentView.setTextViewText(R.id.Text, "show me when expanded!")
        val notification = builder.build()
        //        notification.contentView = normalContentView;
        notification.bigContentView = expandedContentView
        notificationManager.notify(12, notification)
    }

    private fun sendHangNotification() {
        builder.setAutoCancel(true)
        builder.setContentTitle("title")
        builder.setContentText("Content--悬挂式通知")
        builder.setSubText("subText")
        builder.priority = Notification.PRIORITY_DEFAULT
        builder.setCategory(Notification.CATEGORY_MESSAGE)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
        builder.setWhen(System.currentTimeMillis())
        val push = Intent()
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        push.setClass(mActivity, SecondActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(activity, 0, push, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setFullScreenIntent(pendingIntent, true)
        builder.color = Color.RED
        notificationManager.notify(12, builder.build())
    }

    private fun cancelNotification() {
        notificationManager.cancelAll()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.mymenu, menu)
        menu.removeItem(menu.size() - 1)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> Toast.makeText(getContext(), "imte1", Toast.LENGTH_SHORT).show()
            R.id.item2 -> Toast.makeText(getContext(), "imte2", Toast.LENGTH_SHORT).show()
            R.id.item3 -> Toast.makeText(getContext(), "imte3", Toast.LENGTH_SHORT).show()
            R.id.item4 -> Toast.makeText(getContext(), "imte4", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initView() {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_create_notification_channel -> createNotificationChannel()
            R.id.btn_delete_notification_channel -> {
                val manager = mActivity.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
                manager?.deleteNotificationChannel(CHANNEL_NORMAL_NOTIFICATION)
            }
            R.id.btn_send -> sendNotification()
            R.id.btn_send_message -> sendImportantNotification()
            R.id.btn_sendBigViewNotification -> sendBigViewNotification()
            R.id.btn_sendcollapse -> sendCollapsedNotification()
            R.id.btn_sendhang -> sendHangNotification()
            R.id.btn_bigTextStyle -> sendBigTextStyleNotification()
            R.id.btn_InboxStyle -> sendInboxStyleNotification()
            R.id.btn_cancel -> cancelNotification()
            R.id.btn_image -> captureAndSendNotification()
            R.id.btn_take_photo -> takePhoto()
            R.id.btn_take_photo_uri -> takePhotoWithSpecialUri()
            else -> {
            }
        }
    }

    private fun sendInboxStyleNotification() {
        val intent = Intent(getContext(), DialogActivity::class.java)
        val builder = NotificationCompat.Builder(getContext())
        builder.setAutoCancel(true)
        builder.setContentTitle("title")
        builder.setContentText("Content--这是一条BigTextStyle通知")
        builder.setSubText("subText")
        builder.setTicker("ticker")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
        builder.setWhen(System.currentTimeMillis())
        val pendingIntent = PendingIntent.getActivity(activity, 0, intent, 0)
        builder.addAction(R.drawable.ic_launcher, "Call Back", pendingIntent)
        builder.addAction(R.drawable.ic_launcher, "Call History", pendingIntent)
        val style = NotificationCompat.BigTextStyle(builder)
        style.bigText("你好，这是一个BigTextStyle风格的通知测试，\n 花间一壶酒，独酌无相亲。举杯邀明月，对应成三人。醒时同交欢，醉后各自归，永结无情游，相期邈云汉")
        val notification = style.build()
        notificationManager.notify(System.currentTimeMillis().toInt() / 1000, notification)
    }

    private fun sendBigTextStyleNotification() {
        val intent = Intent(mActivity, DialogActivity::class.java)
        builder.setAutoCancel(true)
        builder.setContentTitle("title")
        builder.setContentText("Content--普通通知")
        builder.setSubText("subText")
        builder.setTicker("ticker")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
        builder.setWhen(System.currentTimeMillis())
        val style = NotificationCompat.BigTextStyle(builder)
        style.bigText("Android 4.1 supports expandable notifications. In addition to normal notification view it is possible to define a big view which gets shown when notification is expanded. There are three styles to be used with the big view: big picture style, big text style, Inbox style. The following code demonstrates the usage of the BigTextStyle() which allows to use up to 256 dp.")
        style.setBigContentTitle("big style 通知")
        style.setSummaryText("消息摘要")
        val notification = style.build()
        notificationManager.notify(12, notification)
    }

    /**
     * 这种方式发起的拍摄不需要申请权限，因为是调用了系统的Intent，而且返回时会返回一个缩略图
     */
    private fun takePhoto() {
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        startActivityForResult(intent, 100)
    }

    /**
     * 这种方式发起的拍摄不需要申请权限，而且返回时会返回原图，因为图片太大会引发OOM
     * 所以需要使用URI的方法获取图片的引用
     */
    private fun takePhotoWithSpecialUri() {
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        intent.addCategory(Intent.CATEGORY_DEFAULT)
        //        fileName = getActivity().getCacheDir().toString() + "/" + System.currentTimeMillis()+".jpg";
//        fileName = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString() + "/" + System.currentTimeMillis()+".jpg";
        fileName = mActivity.externalCacheDir.toString() + "/" + System.currentTimeMillis() + ".jpg"
        val file = File(fileName)
        if (file.exists()) {
            file.delete()
        }
        val uri: Uri
        //在Android7.0(Android N)及以上版本
        uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            FileProvider.getUriForFile(mActivity, mActivity.applicationContext.packageName + ".provider", file) //通过FileProvider创建一个content类型的Uri
        } else {
            Uri.fromFile(file)
        }
        Log.i(TAG, "takePhotoWithSpecialUri: $uri")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            100 -> if (resultCode == Activity.RESULT_OK) {
                if (data != null && data.extras != null) {
                    //使用这个方式和使用data.getdata()获取Uri方式得到的图片大小是一样的，
                    val bitmap = data?.getStringExtra("data") as? Bitmap
                    viewBinding.img.setImageBitmap(bitmap)
                    val byteCount = bitmap?.byteCount
                    Log.i(TAG, "onActivityResult: bytecount ==" + byteCount?.toLong()?.div(1024) + "K")
                    //第二种方式取图片，从URI里面取，部分手机取不到URI需要从Content Provider里面取，
                    val uri: Uri?
                    uri = if (data.data != null) {
                        data.data
                    } else {
                        Uri.parse(MediaStore.Images.Media.insertImage(mActivity.contentResolver, bitmap, null, null))
                    }
                    //直接使用MediaStore也可以获取Bitmap对象
                    showImage(uri)
                    //第二种方法展示Image View
                    showImage(uri, viewBinding.img)
                    Log.i(TAG, "onActivityResult: uri==\t" + uri.toString())
                }
            }
            200 -> if (resultCode == Activity.RESULT_OK) {
                val file = File(fileName)
                val options = BitmapFactory.Options()
                options.inPurgeable = true
                //                    options.inSampleSize = 2;2
                val bitmap = BitmapFactory.decodeFile(file.absolutePath, options)
                Log.i(TAG, "onActivityResult: byteCount ==" + bitmap.byteCount / 1024 / 1024 + "M")
                viewBinding.img.setImageBitmap(bitmap)
                Log.i(TAG, "bitmap: width =  " + bitmap.width)
                Log.i(TAG, "bitmap: height =  " + bitmap.height)
                Log.i(TAG, "imageView: width =  " + viewBinding.img.width)
                Log.i(TAG, "imageView: height=" + viewBinding.img.height)
            }
            else -> {
            }
        }
    }

    private fun showImage(uri: Uri?) {
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, uri)
            viewBinding.img.setImageBitmap(bitmap)
            val byteCount = bitmap.byteCount
            Log.i(TAG, "showImage: byteCount ==" + byteCount / 1024 + "K")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun showImage(uri: Uri?, imageView: ImageView) {
        try {
            uri?.let {
                val parcelFileDescriptor = activity?.contentResolver?.openFileDescriptor(uri, "r")
                val fileDescriptor = parcelFileDescriptor?.fileDescriptor
                val bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)
                imageView.setImageBitmap(bitmap)
                val byteCount = bitmap.byteCount
                Log.i(TAG, "showImage: byteCount ==" + byteCount / 1024 + "K")
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val CHANNEL_NORMAL_NOTIFICATION = "normal_notification"
        const val CHANNEL_IMPORTANT_NOTIFICATION = "important_notification"
        var fileName: String? = null
    }
}