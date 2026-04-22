package com.example.myapplication;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.SharedPreferences;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.animate()
                            .scaleX(0.97f)
                            .scaleY(0.97f)
                            .setDuration(100)
                            .start();
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();
                    break;
            }
            return false;

        });

        btnLogin.setOnClickListener(v -> validateLogin());
    }

    protected void validateLogin(){
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(username.isEmpty()){
            etUsername.setError(getString(R.string.error_username));
            etUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etPassword.setError(getString(R.string.error_password));
            etPassword.requestFocus();
            return;
        }

        if(!password.matches(".*[a-zA-Z].*") || !password.matches(".*[0-9].*")){
            etPassword.setError(getString(R.string.error_password_format));
            etPassword.requestFocus();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.apply();

        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();

    }
}