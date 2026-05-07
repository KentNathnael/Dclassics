package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView tvGreeting, menuHome, menuBooks, menuStores, menuLogout;
    private ImageButton btnMenu, btnStorePrev, btnStoreNext;
    private LinearLayout menuDropdown;
    private boolean isMenuVisible = false;
    private int currentStorePosition = 0;
    private RecyclerView rvFeatured, rvRecommended, rvStores;
    private List<book> featuredBookList, recommendedBookList;
    private List<Store> storeList;
    private HomeBookAdapter FeaturedBookAdapter, RecommendedBookAdapter;
    private StoreAdapter storeAdapter;

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
        menuHome = findViewById(R.id.menuHome);
        menuBooks = findViewById(R.id.menuBooks);
        menuStores = findViewById(R.id.menuStores);
        menuLogout = findViewById(R.id.menuLogout);

        btnMenu.setOnClickListener(v -> togglehamburger());

        ConstraintLayout mainLayout = findViewById(R.id.mainLayout);

        mainLayout.setOnClickListener(v -> {
            if (isMenuVisible) {
                menuDropdown.setVisibility(View.GONE);
                isMenuVisible = false;
            }
        });

//      Dropdown
        menuHome.setOnClickListener(v -> {
            menuDropdown.setVisibility(View.GONE);
            isMenuVisible = false;
        });

        menuBooks.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BookActivity.class);
            startActivity(intent);
        });

        menuStores.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, StoreActivity.class);
            startActivity(intent);
        });

        menuLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });


//        feature recylcer view
        rvFeatured = findViewById(R.id.rvFeatured);

        featuredBookList = new ArrayList<>();
        featuredBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 4.9, "Rp 139.000"));
        featuredBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 3.8, "Rp 139.000"));
        featuredBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 4, "Rp 139.000"));
        featuredBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 4.5, "Rp 139.000"));

        FeaturedBookAdapter = new HomeBookAdapter(featuredBookList);

        rvFeatured.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        rvFeatured.setAdapter(FeaturedBookAdapter);

        rvRecommended = findViewById(R.id.rvRecommended);

        recommendedBookList = new ArrayList<>();
        recommendedBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 5, "Rp 139.000"));
        recommendedBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 5, "Rp 139.000"));
        recommendedBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 5, "Rp 139.000"));
        recommendedBookList.add(new book(R.drawable.logo, "Atomic Habits", "James", 5, "Rp 139.000"));

        RecommendedBookAdapter = new HomeBookAdapter(recommendedBookList);

        rvRecommended.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        rvRecommended.setAdapter(RecommendedBookAdapter);

        rvStores = findViewById(R.id.rvStores);
        btnStorePrev = findViewById(R.id.btnStorePrev);
        btnStoreNext = findViewById(R.id.btnStoreNext);

        setupStoreCarousel();
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

    private void setupStoreCarousel() {
        storeList = new ArrayList<>();

        storeList.add(new Store(R.drawable.logo, "Gramedia - Grand Indonesia"));
        storeList.add(new Store(R.drawable.logo, "Kinokuniya - Plaza Senayan"));
        storeList.add(new Store(R.drawable.logo, "Periplus - PIM"));

        storeAdapter = new StoreAdapter(storeList);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvStores.setLayoutManager(layoutManager);
        rvStores.setAdapter(storeAdapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rvStores);

        btnStoreNext.setOnClickListener(v -> {
            if (currentStorePosition < storeList.size() - 1) {
                currentStorePosition++;
                rvStores.smoothScrollToPosition(currentStorePosition);
            }
        });

        btnStorePrev.setOnClickListener(v -> {
            if (currentStorePosition > 0) {
                currentStorePosition--;
                rvStores.smoothScrollToPosition(currentStorePosition);
            }
        });

        rvStores.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position = layoutManager.findFirstVisibleItemPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        currentStorePosition = position;
                    }
                }
            }
        });
    }
}