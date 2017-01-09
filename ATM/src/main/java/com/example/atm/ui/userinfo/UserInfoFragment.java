package com.example.atm.ui.userinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.atm.utils.ToastUtil;
import com.example.atm.MainActivity;
import com.example.atm.R;


public class UserInfoFragment extends Fragment implements View.OnClickListener {

	private TextView et_name, et_email;
	private TextView tv_changepassword;

	private Intent intent;
	private Bundle bundle;

	private String LoginID = null;

	private SharedPreferences preferences;
	private AlertDialog.Builder builder;
	private final String TAG = "User Info";

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//		MyNetworkStatus.getNetworkConnection(getActivity(), TAG);
		View root = inflater.inflate(R.layout.fragment_userinfo, container,
				false);
		preferences = getActivity().getSharedPreferences("config",
				Context.MODE_PRIVATE);
		LoginID = preferences.getString("LoginID", null);

		et_name = (TextView) root.findViewById(R.id.et_name);
		et_email = (TextView) root.findViewById(R.id.et_email);
		tv_changepassword = (TextView) root
				.findViewById(R.id.tv_changepassword);

		builder = new AlertDialog.Builder(getActivity());
		bundle = new Bundle();

		tv_changepassword.setOnClickListener(this);

		initData();
		return root;
	}

	@Override
	public void onResume() {
		super.onResume();
		((MainActivity) getActivity())
				.setActionBarTitle(getString(R.string.title_user_info) ,null);
	}

	private void initData() {



	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_changepassword:
//			intent = new Intent(getActivity(), ChangePasswordActivity.class);
			if (LoginID != null) {
//				bundle.putString("loginID", LoginID);
//				intent.putExtras(bundle);
//				startActivity(intent);
			} else {
				ToastUtil.showToast(getActivity(),
						"Background data is incorrect!");
			}
			break;
		default:
			break;
		}
	}
}
