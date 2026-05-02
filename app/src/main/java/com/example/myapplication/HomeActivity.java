package com.example.myapplication;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView tvGreeting;
    private ImageButton btnMenu;
    private LinearLayout menuDropdown;
    private boolean isMenuVisible = false;

    RecyclerView rvFeatured, rvRecommended;
    List<book> featuredBookList, recommendedBookList;
    BookAdapter FeaturedBookAdapter, RecommendedBookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//      Greetings
        tvGreeting = findViewById(R.id.tvGreeting);

        SharedPreferences preferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String username = preferences.getString("username", "User");

        tvGreeting.setText("Hello, " + username);

//        hamburger
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

//        feature recylcer view
        rvFeatured = findViewById(R.id.rvFeatured);

        featuredBookList = new ArrayList<>();
        featuredBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 4.9));
        featuredBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 3.8));
        featuredBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 4));
        featuredBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 4.5));

        FeaturedBookAdapter = new BookAdapter(featuredBookList);

        rvFeatured.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        rvFeatured.setAdapter(FeaturedBookAdapter);

        rvRecommended = findViewById(R.id.rvRecommended);

        recommendedBookList = new ArrayList<>();
        recommendedBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 5));
        recommendedBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 5));
        recommendedBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 5));
        recommendedBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 5));

        RecommendedBookAdapter = new BookAdapter(recommendedBookList);

        rvRecommended.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        rvRecommended.setAdapter(RecommendedBookAdapter);
    }
    protected void togglehamburger() {
        if (isMenuVisible) {
            menuDropdown.setVisibility(View.GONE);
            isMenuVisible = false;
        } else {
            menuDropdown.setVisibility(View.VISIBLE);
            isMenuVisible = true;
        }
    }
}