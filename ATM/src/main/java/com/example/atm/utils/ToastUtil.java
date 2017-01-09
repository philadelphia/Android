package com.example.atm.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

@SuppressWarnings("unused")
public class ToastUtil {

	private static Toast mToast;
	private static ProgressDialog mDialog;

	public static void showToast(Context context, String text) {
		if (mToast == null) {
			mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(text);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}
}
