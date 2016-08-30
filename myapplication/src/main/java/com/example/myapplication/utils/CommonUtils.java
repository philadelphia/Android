package com.delta.ams.common.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 
 * @author V.Wenju.Tian
 * 
 */
public class CommonUtils {

	public static <T extends View> T findView(View view, int resId) {

		View mview = view.findViewById(resId);
		return (T) mview;

	}

	/**
	 * 隐藏键盘操作
	 * 
	 * @param context
	 *            上下文
	 * @param view
	 *            点击
	 */
	public static void hideKeyBoard(final Context context, View view) {
		view.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) context
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}

		});

	}

	/**
	 * 判断String是否为纯数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 保留两位小数
	 * 
	 * @param editText
	 */
	public static void setEditTextPoint(final EditText editText) {
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.toString().contains(".")) {
					if (s.length() - 1 - s.toString().indexOf(".") > 2) {
						Log.e("tag", "----" + s.length() + "--" + s.toString()
								+ "--" + editText.getText().toString() + "--"
								+ s.toString().indexOf("."));
						s = s.toString().subSequence(0,
								s.toString().indexOf(".") + 2);
						editText.setText(s);
						Log.e("tag", s.length() + "--" + s.toString() + "--"
								+ editText.getText().toString() + "--"
								+ s.toString().indexOf("."));
						editText.setSelection(s.length());
					}
				}
				if (s.toString().trim().substring(0).equals(".")) {
					s = "0" + s;
					editText.setText(s);
					editText.setSelection(2);
				}

				if (s.toString().startsWith("0")
						&& s.toString().trim().length() > 1) {
					if (!s.toString().substring(1, 2).equals(".")) {
						editText.setText(s.subSequence(0, 1));
						editText.setSelection(1);
						return;
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

		});

	}

	/**
	 * 判断屏幕是否是竖屏
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isPortrait(Context context) {

		return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
	}

}
