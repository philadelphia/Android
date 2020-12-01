package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivitySplashBinding;


public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        countDownTimer.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.imgBackGround.setOnClickListener(this);
        binding.btnTimter.setOnClickListener(this);
    }

    @Override
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
            binding.btnTimter.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            binding.btnTimter.setText("跳过( 0 s)");
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
