package com.example.atm.ui.userinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.atm.ChangePasswordActivity;
import com.example.atm.apiInterface.ApiClient;
import com.example.atm.bean.UserInfo;
import com.example.atm.utils.HttpCallUtil;
import com.example.atm.utils.MyRetrofit;
import com.example.atm.utils.ToastUtil;
import com.example.atm.MainActivity;
import com.example.atm.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;


public class UserInfoFragment extends Fragment implements View.OnClickListener {

	private TextView et_name;
	private TextView et_email;
	private Button  btn_changepassword;

	private Intent intent;
	private Bundle bundle;

	private String LoginID = null;

	private SharedPreferences preferences;
	private AlertDialog.Builder builder;

	private Call<UserInfo> userInfo;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		MyNetworkStatus.getNetworkConnection(getActivity(), TAG);
		View root = inflater.inflate(R.layout.fragment_userinfo, container,
				false);
		preferences = getActivity().getSharedPreferences("config",
				Context.MODE_PRIVATE);
		LoginID = preferences.getString("LoginID", "drc");

		et_name = (TextView) root.findViewById(R.id.et_name);
		et_email = (TextView) root.findViewById(R.id.et_email);
		btn_changepassword = (Button) root
				.findViewById(R.id.btn_changepassword);

		builder = new AlertDialog.Builder(getActivity());
		bundle = new Bundle();

		btn_changepassword.setOnClickListener(this);

		getUserInfo("drc");
		return root;
	}

	@Override
	public void onResume() {
		super.onResume();
		MainActivity.setActionBarTitle(getString(R.string.title_user_info) ,null);
	}

	private void getUserInfo(String loginID) {
		userInfo = MyRetrofit.getInstance().create(ApiClient.class).getUserInfo(loginID);
		userInfo.enqueue(new Callback<UserInfo>() {
			@Override
			public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
				if (HttpCallUtil.isResponseValid(response)){
					UserInfo body = response.body();
					showUserInfo(body);
				}
			}

			@Override
			public void onFailure(Call<UserInfo> call, Throwable t) {

			}
		});

	}

	private void showUserInfo(UserInfo body) {
		et_name.setText(body.getName());
		et_email.setText(body.getEmail());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_changepassword:
			intent = new Intent(getActivity(), ChangePasswordActivity.class);
			if (LoginID != null) {
				bundle.putString("loginID", LoginID);
				intent.putExtras(bundle);
				startActivity(intent);
			} else {
				ToastUtil.showToast(getActivity(),
						"Background data is incorrect!");
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		HttpCallUtil.cancelCall(userInfo);
	}
}
