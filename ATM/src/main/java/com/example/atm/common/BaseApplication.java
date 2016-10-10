package com.example.atm.common;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.PowerManager;
import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;

/**
 * This Class provides some common functions may need by Android Project.you can
 * reuse it in your project to avoid redefining it again.
 * 
 * @author Tao.ZT.Zhang
 * 
 */
/**
 * @author Jie-BJ.Liu
 * 
 */
public class BaseApplication extends Application {

	private static Context context;
	private static AlertDialog.Builder builder;
	private static NotificationManager notificationManager;
	private static PowerManager	powerManager;
	public static Handler mHandler;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();

		builder = new AlertDialog.Builder(context);
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		powerManager = (PowerManager) getSystemService(POWER_SERVICE);
//		DisplayImageOptions options = new DisplayImageOptions.Builder()
//				.cacheInMemory(true).cacheOnDisc(true)
//				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//				.bitmapConfig(Bitmap.Config.RGB_565)
//				.showImageOnLoading(R.drawable.ic_launcher)
//				.showImageForEmptyUri(R.drawable.ic_launcher)
//				.showImageOnFail(R.drawable.ic_launcher).build();
//
//		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//				this).memoryCacheExtraOptions(480, 800)
//				.discCacheExtraOptions(800, 800, null)
//				.memoryCache(new WeakMemoryCache())
//				.memoryCacheSize(2 * 1024 * 1024)
//				.discCacheSize(50 * 1024 * 1024).discCacheFileCount(1000)
//				.defaultDisplayImageOptions(options).build();
//		ImageLoader.getInstance().init(config);config
	}



	/**
	 * 
	 * @return context
	 */
	public static Context getContext() {
		return context;

	}

	/**
	 * 
	 * @return AlertDialog.Builder
	 */
	public static AlertDialog.Builder getAlertBuilder() {
		return builder;
	}

	/**
	 * @return notificationManager
	 */
	public static NotificationManager getNotificationManager() {
		return notificationManager;
	}

	public static Handler getHandler() {
		return mHandler;
	}

	public static void setHandler(Handler handler) {
		mHandler = handler;
	}

	public static PowerManager getPowerManager(){
		return powerManager;
	}
	
}
