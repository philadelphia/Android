package com.example.atm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.atm.ui.login.view.LoginActivity;

public class LogoutActivity extends Activity {
    private Button  btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_logout);
        btnLogout  = (Button) findViewById(R.id.logoutbutton);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(LogoutActivity.this,
                        LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
    }
}
