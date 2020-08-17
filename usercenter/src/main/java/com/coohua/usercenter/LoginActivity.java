package com.coohua.usercenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.coohua.common.eventbus.LoginEvent;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button btnLogin;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        input = findViewById(R.id.input);
        btnLogin = findViewById(R.id.login);

        init();
    }

    private void init() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EventBus.getDefault().post(new LoginEvent(input.getText().toString()));
                finish();
            }
        });
    }
}
