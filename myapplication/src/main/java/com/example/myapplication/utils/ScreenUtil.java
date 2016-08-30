package com.delta.ams.common.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.PowerManager;
import android.view.WindowManager;

/**
 * Screen工具类
 * @author Yang-BJ.Li
 *
 */
public class ScreenUtil
{
	/**
	 * 
	 * @param context
	 * @param WH(参数传入w或者h)如果是w则返回width,如果是h则返回height
	 * @return width或者height
	 */
	public static int getScreenWH (Context context, String WH)
	{
		WindowManager wm = (WindowManager) context.getSystemService (Context.WINDOW_SERVICE);
		Point size = new Point ();
		wm.getDefaultDisplay ().getSize (size);
		int width = size.x;
		int height = size.y;
		if (WH.equalsIgnoreCase ("w"))
		{
			return width;// 获取屏幕分辨率宽度
		}
		else
		{
			return height;
		}

	}

	public static int dp2px (Context context, float dp)
	{
		final float scale = context.getResources ().getDisplayMetrics ().density;
		return (int) (dp * scale + 0.5f);
	}

	public static int px2dp (Context context, float px)
	{
		final float scale = context.getResources ().getDisplayMetrics ().density;
		return (int) (px / scale + 0.5f);
	}

	public static int getScreenHeightWithDp (Context context)
	{
		int heightPx = getScreenWH (context, "h");
		int heightDp = px2dp (context, heightPx);
		return heightDp;
	}

	public static int getScreenHeightWithPx (Context context)
	{
		int heightPx = getScreenWH (context, "h");
		return heightPx;
	}

	public static int getScreenWidthWithPx (Context context)
	{
		int widthPx = getScreenWH (context, "w");
		return widthPx;
	}

	public static int getScreenWidthWithDp (Context context)
	{
		int widthPx = getScreenWH (context, "w");
		int widthDp = px2dp (context, widthPx);
		return widthDp;
	}

	public static boolean isScreenOn (Context context)
	{
		PowerManager pm = (PowerManager) context.getSystemService (Context.POWER_SERVICE);

		//isScreenOn被pm.isInteractive ()代替,但是isInteractive需要api20以上
		if (pm.isScreenOn ())
		{
			return true;
		}
		return false;
	}
}
