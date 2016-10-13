package com.example.atm;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.delta.common.BaseApplication;
import com.delta.common.utils.GsonTools;
import com.delta.mprotecht.R;
import com.delta.mprotecht.common.utils.MyNetworkStatus;
import com.delta.mprotecht.common.utils.MyStringRequest;
import com.delta.mprotecht.common.utils.Url;
import com.delta.mprotecht.entities.LoginBean;

public class LoginActivity extends Activity implements View.OnClickListener {

	private EditText EloginId, Epassword;
	private Button loginButton;
	private TextView forgouPassword;
	private final String TAG = "login";
	private SharedPreferences sp;
	private SharedPreferences preferences;
	private Editor edit;
	private String loginName;
	private String loginPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);

		MyNetworkStatus.getNetworkConnection(this, TAG);

		sp = getSharedPreferences("login", MODE_APPEND);
		boolean hasLogined = sp.getBoolean("hasLogined", false);

		preferences = getSharedPreferences("config", MODE_PRIVATE);
		edit = preferences.edit();

		if (!hasLogined) {
			setContentView(R.layout.activity_login);

			EloginId = (EditText) findViewById(R.id.edit1);
			Epassword = (EditText) findViewById(R.id.edit2);
			loginButton = (Button) findViewById(R.id.loginbutton);
			forgouPassword = (TextView) findViewById(R.id.textview);


			loginButton.setOnClickListener(this);
			forgouPassword.setOnClickListener(this);
		} else {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}

	}

	@Override
	public void onClick(View v) {

		ConnectivityManager connectivityManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_exclamation_red)
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
							startActivity(intent);
						}

					}).setNegativeButton("Cancel", new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					}).show();
		} else {
			switch (v.getId()) {

			case R.id.loginbutton:

				@SuppressWarnings("static-access")
				final ProgressDialog dialog = new ProgressDialog(this).show(
						this, "", "Loading...");

				loginName = EloginId.getText().toString().trim();
				loginPassword = Epassword.getText().toString().trim();

				if ("".equals(loginName) || "".equals(loginPassword)) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							LoginActivity.this);
					builder.setIcon(R.drawable.alm)
							.setMessage("Login ID or password cannot be empty!")
							.setPositiveButton("OK", null).show();
					dialog.dismiss();
					break;
				} else {

					MyStringRequest stringRequest = new MyStringRequest(
							Method.POST, Url.uRL_LOGIN_POST,
							new Response.Listener<String>() {

								@Override
								public void onResponse(String response) {

									if (null != response || "".equals(response)) {
										if ("Message".equals(response
												.substring(2, 9))) {
											AlertDialog.Builder builder = new AlertDialog.Builder(
													LoginActivity.this);
											builder.setMessage(
													"Current password does not match! or Current password and new password can not be same! or Login ID does not match!")
													.setPositiveButton("OK",
															null).show();
											dialog.dismiss();
										} else {
											String string = response;
											LoginBean loginBean = GsonTools
													.changeGsonToBean(string,
															LoginBean.class);
											if ("Successful".equals(loginBean
													.getResult())) {
												edit.putString("LoginID",
														loginName);
												edit.commit();
												Intent intent = new Intent(
														LoginActivity.this,
														MainActivity.class);
												dialog.dismiss();
												startActivity(intent);
												Editor editor = sp.edit();
												editor.putBoolean("hasLogined",
														true);
												editor.commit();
												finish();
											}
										}
									}
								}
							}, new Response.ErrorListener() {
								@Override
								public void onErrorResponse(VolleyError error) {
									if (null != error
											&& error.networkResponse != null) {
										if (error.networkResponse.statusCode == 400) {
											AlertDialog.Builder builder = new AlertDialog.Builder(
													LoginActivity.this);
											builder.setIcon(R.drawable.alm)
													.setMessage(
															"The login ID or password is incorrect. Try again or click Forgot Password.")
													.setPositiveButton("OK",
															null).show();
											dialog.dismiss();
											
										} else if (error.networkResponse.statusCode == 408) {
											AlertDialog.Builder builder = new AlertDialog.Builder(
													LoginActivity.this);
											builder.setIcon(R.drawable.alm)
													.setMessage(
															"Connection Timeout")
													.setPositiveButton("OK",
															null).show();
											dialog.dismiss();
										} else if (error.networkResponse.statusCode == 403) {
											AlertDialog.Builder builder = new AlertDialog.Builder(
													LoginActivity.this);
											builder.setIcon(R.drawable.alm)
													.setMessage(
															"Connection Timeout")
													.setPositiveButton("OK",
															null).show();
											dialog.dismiss();
										}
									}
								}
							});
					stringRequest.addHeaders("LoginID", loginName);
					stringRequest.addHeaders("Password", loginPassword);
					BaseApplication.getRequestQueue().add(stringRequest);
					stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000,// 默认超时时间
							2,// 默认最大尝试次数
							DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
					break;
				}
			case R.id.textview:
				Intent intent = new Intent(LoginActivity.this,
						ForgotPasswordActivity.class);
				startActivity(intent);
			}
		}

	}
}
