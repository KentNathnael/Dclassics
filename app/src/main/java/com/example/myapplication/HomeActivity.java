package com.example.myapplication;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class HomeActivity extends AppCompatActivity {

    private TextView tvGreeting;
    private ImageButton btnMenu;
    private LinearLayout menuDropdown;
    private boolean isMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvGreeting = findViewById(R.id.tvGreeting);

        SharedPreferences preferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String username = preferences.getString("username", "User");

        tvGreeting.setText("Hello, " + username);

        btnMenu = findViewById(R.id.btnMenu);
        menuDropdown = findViewById(R.id.menuDropdown);

        btnMenu.setOnClickListener(v -> togglehamburger());

        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);

        mainLayout.setOnClickListener(v -> {
            if (isMenuVisible) {
                menuDropdown.setVisibility(View.GONE);
                isMenuVisible = false;
            }
        });
    }
    protected void togglehamburger(){
        if (isMenuVisible) {
            menuDropdown.setVisibility(View.GONE);
            isMenuVisible = false;
        } else {
            menuDropdown.setVisibility(View.VISIBLE);
            isMenuVisible = true;
        }
    }
}