package com.example.atm.ui.login;


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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.atm.MainActivity;
import com.example.atm.R;
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


public class LoginActivity extends Activity implements View.OnClickListener, LoginContract.View {

    private EditText ed_userid, ed_password;
    private Button loginButton;
    private Button forgouPassword;
    private static final String TAG = "LoginActivity";
    private SharedPreferences sp;
    private SharedPreferences preferences;
    private ProgressDialog progressDialog;
    private Editor edit;
    private Call<LoginResult> login;
    private Observable<LoginResult> loginRxJava;
    private LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyNetworkStatus.getNetworkConnection(this, TAG);

        sp = getSharedPreferences("login", MODE_APPEND);
        boolean hasLogined = sp.getBoolean("hasLogined", false);

        setContentView(R.layout.activity_login);

        initView();

//        if (!hasLogined) {
//            setContentView(R.layout.activity_login);
//
//            ed_userid = (EditText) findViewById(R.id.edit1);
//            ed_password = (EditText) findViewById(R.id.edit2);
//            loginButton = (Button) findViewById(R.id.loginbutton);
//            forgouPassword = (Button) findViewById(R.id.btn_forgetPassword);
//            loginButton.setOnClickListener(this);
//            forgouPassword.setOnClickListener(this);
//            setPresenter(new LoginPresenter(this));
//            preferences = getSharedPreferences("config", MODE_PRIVATE);
//            edit = preferences.edit();
//
//        } else {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
////            finish();
//        }



    }

    private void initView() {
        setPresenter(new LoginPresenter(this));
        ed_userid = (EditText) findViewById(R.id.edit1);
        ed_password = (EditText) findViewById(R.id.edit2);
        ed_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO){
                    Log.i(TAG, "onEditorAction: gogogog");
                    loginPresenter.login();
                    return  true;
                }
                return false;
            }

        });
        loginButton = (Button) findViewById(R.id.loginbutton);
        forgouPassword = (Button) findViewById(R.id.btn_forgetPassword);
        loginButton.setOnClickListener(this);
        forgouPassword.setOnClickListener(this);
        preferences = getSharedPreferences("config", MODE_PRIVATE);
        edit = preferences.edit();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loging.....");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbutton:
//				final ProgressDialog dialog = new ProgressDialog(this).show(
//						this, "", "Loading...");
//              login(userName, userPassword);
//				loginByRxjava(userName, userPassword);
                Log.i(TAG, "onClick: begin to call presenter.login");
                loginPresenter.login();
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
        ApiClientRxJava apiClient = MyRetrofit.getInstance().create(ApiClientRxJava.class);
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
                        Log.i(TAG, "onNext: loginResult=== " + loginResult.toString());
                        Intent intent = new Intent(
                                LoginActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    @Override
    public String getUserID() {
        return ed_userid.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return ed_password.getText().toString().trim();
    }

    @Override
    public void showDialog() {
        progressDialog.show();
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }


    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.loginPresenter = presenter;
    }

    @Override
    public void onSuccess() {
        Log.i(TAG, "onSuccess: ");
        hideDialog();
        Intent intent = new Intent(
                LoginActivity.this,
                MainActivity.class);
        startActivity(intent);
//        finish();

    }

    @Override
    public void onFailed() {
        hideDialog();
        Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.dettatch(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        hideDialog();
    }

    @Override
    public void finish()
    {
        if (progressDialog != null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }

    @Override
    public void attemptLogin() {
        InputMethodManager imm = (InputMethodManager) LoginActivity.this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(loginButton.getWindowToken(), 0);

        // Reset errors.
        ed_userid.setError(null);
        ed_password.setError(null);

        // Store values at the time of the login attempt.
        String userName = ed_userid.getText().toString().trim();
        String password = ed_password.getText().toString().trim();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(userName)) {
            ed_userid.setError("不能为空");
            focusView = ed_userid;
            cancel = true;
        }

        // Check for a valid userid.
        if (TextUtils.isEmpty(password)) {
            ed_password.setError("不能为空");
            focusView = ed_password;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            ed_password.setError("不合法,密码长度需大于4");
            focusView = ed_password;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();

        }
    }


    private boolean isPasswordValid(String password) {
            return password.length() > 4 ;
        }
}
