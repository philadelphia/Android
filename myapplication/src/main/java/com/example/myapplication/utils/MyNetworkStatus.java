/*
 * Delta CONFIDENTIAL
 *
 * (C) Copyright Delta Electronics, Inc. 2014 All Rights Reserved
 *
 * NOTICE:  All information contained herein is, and remains the
 * property of Delta Electronics. The intellectual and technical
 * concepts contained herein are proprietary to Delta Electronics
 * and are protected by trade secret, patent law or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Delta Electronics.
 */

/**
 * @author Tao.ZT.Zhang
 */
package com.example.myapplication.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.myapplication.R;


public class MyNetworkStatus  {
	private static ConnectivityManager connectivityManager;

	public static void getNetworkConnection(final Context context, String title) {
		connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info == null) {
			Toast.makeText(context, "Network not available", Toast.LENGTH_SHORT)
					.show();
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setIcon(R.mipmap.ic_exclamation_red)
					.setMessage(" No Connection")
					.setPositiveButton("SETTINGS", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent();

							intent.setAction(Intent.ACTION_MAIN);
							intent.addCategory("android.intent.category.LAUNCHER");
							intent.setFlags(0x10200000);
							intent.setComponent(new ComponentName(
									"com.android.settings",
									"com.android.settings.Settings"));
							context.startActivity(intent);
						}

					}).setNegativeButton("Cancel", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					}).show();
		}
	}

	public static boolean checkNetwork(Context context) {
		connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info != null && info.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}

	public String getNetworkType(Context context) {
		connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		int type = -1 ;
		if (info != null && info.isConnected()) {
			type = info.getType();
		}

		switch (type){
			case ConnectivityManager.TYPE_MOBILE:
				return "Moblie";
			case ConnectivityManager.TYPE_WIFI:
				return "WIFI";
			case ConnectivityManager.TYPE_WIMAX:
				return "WI-MXA";
			case ConnectivityManager.TYPE_ETHERNET:
				return "ETHERNET";
			default:
				return null;
		}
	}

	
}
