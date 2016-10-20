package com.example.atm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.UpdatePasswordResult;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyNetworkStatus;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.utils.ToastUtil;
import com.example.atm.utils.Url;

import java.lang.reflect.Method;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChangePasswordActivity extends Activity implements OnClickListener {

	private EditText current_Password, new_Password, confirm_Password;

	private String txt_current_Password, txt_new_Password,
			txt_confirm_Password;

	private Button bt_Update;
	private String loginID = null;

	private final String TAG = "Change_Pasword";
	private AlertDialog.Builder builder;
	private Intent intent;
	private Bundle bundle;
	private Call<UpdatePasswordResult> updatePasswordResultCall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyNetworkStatus.getNetworkConnection(this, TAG);
		setFinishOnTouchOutside(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_change_password);

		current_Password = (EditText) this.findViewById(R.id.current_Password);
		confirm_Password = (EditText) this.findViewById(R.id.confirm_Password);
		new_Password = (EditText) this.findViewById(R.id.new_Password);
		bt_Update = (Button) this.findViewById(R.id.bt_Update);

		loginID = getIntent().getExtras().getString("loginID");
		builder = new AlertDialog.Builder(this);
		bt_Update.setOnClickListener(this);
		bundle = new Bundle();
	}

	@SuppressWarnings("static-access")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_Update:
			String cur_Password = current_Password.getText().toString().trim();
			String n_Password = new_Password.getText().toString().trim();
			String con_Password = confirm_Password.getText().toString().trim();
			final ProgressDialog dialog = new ProgressDialog(this).show(this,
					"", "loading...");
			txt_current_Password = current_Password.getText().toString().trim();
			txt_new_Password = new_Password.getText().toString().trim();
			txt_confirm_Password = confirm_Password.getText().toString().trim();
			if (cur_Password.length() > 20) {
				ToastUtil.showToast(this,
						"current_Password length can not exceed 20!");
				dialog.dismiss();
			} else if (n_Password.length() > 20) {
				ToastUtil.showToast(this,
						"new_Password length can not exceed 20!");
				dialog.dismiss();
			} else if (con_Password.length() > 20) {
				ToastUtil.showToast(this,
						"confirm_Password length can not exceed 20!!");
				dialog.dismiss();
			} else if (txt_current_Password == null
					|| "".equals(txt_current_Password)
					|| txt_new_Password == null || "".equals(txt_new_Password)
					|| txt_confirm_Password == null
					|| "".equals(txt_confirm_Password)) {
				ToastUtil.showToast(this, "The data can not be empty!");
				dialog.dismiss();
				return;
			} else if (!txt_confirm_Password.equals(txt_new_Password)) {
				ToastUtil
						.showToast(this,
								"The new password and confirm password must be the same!");
				dialog.dismiss();
				return;
			} else if (txt_current_Password.equals(txt_new_Password)) {
				ToastUtil.showToast(this,
						"Current password and  New password can not be same!");
				dialog.dismiss();
				return;
			}

			break;
		}
	}

	private void updateUserPassword(String loginID, String currentPassword, String newPassword) {
		ApiClient apiClient = MyRetrofit.getInstance().create(ApiClient.class);
		updatePasswordResultCall = apiClient.updateUserPassword(loginID, currentPassword, newPassword);
		updatePasswordResultCall.enqueue(new Callback<UpdatePasswordResult>() {
			@Override
			public void onResponse(Call<UpdatePasswordResult> call, Response<UpdatePasswordResult> response) {
				if (HttpCallUtil.isResponseValid(response)){
					UpdatePasswordResult body = response.body();
					if(body.getMessage().equalsIgnoreCase("Successfully Updated")){
						builder.setMessage("Update Successful")
								.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {

									}
								})
								.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {

									}
								}).show();
					}

				}
			}

			@Override
			public void onFailure(Call<UpdatePasswordResult> call, Throwable t) {

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (resultCode) {
		case RESULT_OK:
			Bundle b = data.getExtras();
			String str = b.getString("result");
			if ("result".equals(str)) {
				finish();
			}
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		HttpCallUtil.cancelCall(updatePasswordResultCall);
	}
}
