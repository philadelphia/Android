package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.img_backGround)
    ImageView imgBackGround;
    @BindView(R.id.btn_timter)
    Button btnTimter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        countDownTimer.start();
    }

    @OnClick({R.id.img_backGround, R.id.btn_timter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_backGround:
                break;
            case R.id.btn_timter:
                goToLogInOrMainActivity();
                break;
        }
    }

    private CountDownTimer countDownTimer = new CountDownTimer(3200, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnTimter.setText("跳过(" + millisUntilFinished / 1000 +"s)");
        }

        @Override
        public void onFinish() {
            btnTimter.setText("跳过( 0 s)");
            goToLogInOrMainActivity();
        }
    };

    private void goToLogInOrMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
