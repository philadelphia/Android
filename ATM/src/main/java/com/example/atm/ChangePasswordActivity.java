package com.example.atm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.delta.common.BaseApplication;
import com.delta.common.utils.GsonTools;
import com.delta.mprotecht.R;
import com.delta.mprotecht.common.utils.MyNetworkStatus;
import com.delta.mprotecht.common.utils.MyStringRequest;
import com.delta.mprotecht.common.utils.ToastUtil;
import com.delta.mprotecht.common.utils.Url;
import com.delta.mprotecht.entities.LoginResult;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyNetworkStatus.getNetworkConnection(this, TAG);
		setFinishOnTouchOutside(false);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.password_change);

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
			} else {
				MyStringRequest myStringRequest = new MyStringRequest(
						Method.POST, Url.CHANGE_PASSWORD_URL,
						new Listener<String>() {

							@Override
							public void onResponse(String response) {
								if (null != response || "".equals(response)) {
									String str = response.substring(0,
											response.length());
									LoginResult loginResult = GsonTools
											.changeGsonToBean(str,
													LoginResult.class);
									String s = loginResult.getMessage();
									intent = new Intent(
											getApplicationContext(),
											ChangePasswordSureActivity.class);
									bundle.putString("result", s);
									intent.putExtras(bundle);
									startActivityForResult(intent, 1);
									dialog.dismiss();
								} else {
									builder.setMessage(
											"Current password does not match! or Current password and new password can not be same! or Login ID does not match!")
											.setPositiveButton("OK", null)
											.show();
									dialog.dismiss();
								}
							}
						}, new ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError error) {
								builder.setMessage(
										"Current password does not match! or Current password and new password can not be same! or Login ID does not match!")
										.setPositiveButton("OK", null).show();
								dialog.dismiss();
								dialog.dismiss();
							}
						});
				myStringRequest.addHeaders("LoginID", loginID);
				myStringRequest.addHeaders("CurPass", txt_current_Password);
				myStringRequest.addHeaders("NewPass", txt_new_Password);
				BaseApplication.getRequestQueue().add(myStringRequest);

			}
			break;
		default:
			break;
		}
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
}
