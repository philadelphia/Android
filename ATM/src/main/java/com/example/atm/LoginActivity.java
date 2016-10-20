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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.atm.apiInterface.ApiClient;
import com.example.atm.apiInterface.ApiClientRxJava;
import com.example.atm.bean.LoginResult;
import com.example.atm.utils.MyNetworkStatus;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.utils.RxsRxSchedulers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class LoginActivity extends Activity implements View.OnClickListener {

	private EditText EloginId, Epassword;
	private Button loginButton;
	private Button forgouPassword;
	private static final String TAG = "LoginActivity";
	private SharedPreferences sp;
	private SharedPreferences preferences;
	private Editor edit;
	private String userName;
	private String userPassword;
	private Call<LoginResult> login;
	private Observable<LoginResult> loginRxJava;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
			forgouPassword = (Button) findViewById(R.id.btn_forgetPassword);
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
		switch (v.getId()) {
			case R.id.loginbutton:
//				final ProgressDialog dialog = new ProgressDialog(this).show(
//						this, "", "Loading...");
				userName = EloginId.getText().toString().trim();
				userPassword = Epassword.getText().toString().trim();
//				login(userName, userPassword);
				loginByRxjava(userName, userPassword);
				break;

			case R.id.textview:
//				Intent intent = new Intent(LoginActivity.this,
//						ForgotPasswordActivity.class);
//				startActivity(intent);
				break;
			default:
				break;


		}
	}

	private void testConnectivity() {
		ConnectivityManager connectivityManager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
							startActivity(intent);
						}
					}).setNegativeButton("Cancel", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			}).show();
		}
	}

	private void login(String userName, String userPassword) {
		Log.i(TAG, "login: ");
		testConnectivity();
		if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword)) {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					LoginActivity.this);
			builder.setIcon(R.mipmap.alm)
					.setMessage("Login ID or password cannot be empty!")
					.setPositiveButton("OK", null).show();
//			dialog.dismiss();

		}
		ApiClient apiClient = MyRetrofit.initRetrofit().create(ApiClient.class);
		login = apiClient.login(userName, userPassword);
		login.enqueue(new Callback<LoginResult>() {
			@Override
			public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
				if (response.code() == 200 && response.message().equalsIgnoreCase("OK")) {
					LoginResult loginResult = response.body();
					if ("Successful".equals(loginResult
							.getResult())) {
//						edit.putString("LoginID",
//								userName);
//						edit.commit();
						Intent intent = new Intent(
								LoginActivity.this,
								MainActivity.class);
						startActivity(intent);
						finish();
//						dialog.dismiss();
					}
				}
			}

			@Override
			public void onFailure(Call<LoginResult> call, Throwable t) {

			}
		});
	}

	private void loginByRxjava(String userName, String userPassword) {
		Log.i(TAG, "loginByRxjava: ");
//		testConnectivity();
//		if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(userPassword)) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(
//					LoginActivity.this);
//			builder.setIcon(R.mipmap.alm)
//					.setMessage("Login ID or password cannot be empty!")
//					.setPositiveButton("OK", null).show();
//		}

		ApiClientRxJava apiClient = MyRetrofit.initRetrofit().create(ApiClientRxJava.class);
		loginRxJava = apiClient.login(userName, userPassword);
		loginRxJava
		.compose(RxsRxSchedulers.io_main())
 		.subscribe(new Subscriber<LoginResult>() {
			@Override
			public void onCompleted() {
				Log.i(TAG, "onCompleted: ");

			}

			@Override
			public void onError(Throwable e) {
				Log.i(TAG, "onError: ");
			}

			@Override
			public void onNext(LoginResult loginResult) {
				Log.i(TAG, "onNext: loginResult=== " + loginResult.toString() );
				Intent intent = new Intent(
						LoginActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

}
