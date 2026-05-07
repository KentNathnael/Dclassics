package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity {

    private RecyclerView rvBooks, rvNewArrivals;
    private CatalogBookAdapter catalogAdapter, newArrivalAdapter;
    private List<book> catalogBooks, newArrivalBooks;
    private TextView menuHome, menuBooks, menuStores, menuLogout;
    private ImageButton btnMenu;
    private LinearLayout menuDropdown;
    private boolean isMenuVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        rvBooks = findViewById(R.id.rvBooks);
        rvNewArrivals = findViewById(R.id.rvNewArrivals);

        setupCatalogBooks();
        setupNewArrivals();

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
            Intent intent = new Intent(BookActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        menuBooks.setOnClickListener(v -> {
            menuDropdown.setVisibility(View.GONE);
            isMenuVisible = false;
        });

        menuStores.setOnClickListener(v -> {
            Intent intent = new Intent(BookActivity.this, StoreActivity.class);
            startActivity(intent);
        });

        menuLogout.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("UserSession", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(BookActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

    }

    private void setupCatalogBooks() {
        catalogBooks = new ArrayList<>();

        catalogBooks.add(new book(R.drawable.logo, "In a Blue Moon", "Ilana Tan", 4, "Rp 132.000"));
        catalogBooks.add(new book(R.drawable.logo, "Judge Stone", "D. A. Mishani", 3.9, "Rp 149.000"));
        catalogBooks.add(new book(R.drawable.logo, "To the Bridge", "Nancy Rommelmann", 3.5, "Rp 128.000"));
        catalogBooks.add(new book(R.drawable.logo, "Just Friends", "Ana Huang", 4.8,"Rp 139.000"));

        catalogAdapter = new CatalogBookAdapter(catalogBooks);

        rvBooks.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        rvBooks.setAdapter(catalogAdapter);
    }

    private void setupNewArrivals() {
        newArrivalBooks = new ArrayList<>();

        newArrivalBooks.add(new book(R.drawable.logo, "The Correspondent", "Virginia Evans", 3, "Rp 155.000"));
        newArrivalBooks.add(new book(R.drawable.logo, "The Let Them Theory", "Mel Robbins", 4,"Rp 165.000"));
        newArrivalBooks.add(new book(R.drawable.logo, "Murdle", "G. T. Karber", 6, "Rp 120.000"));

        newArrivalAdapter = new CatalogBookAdapter(newArrivalBooks);

        rvNewArrivals.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        rvNewArrivals.setAdapter(newArrivalAdapter);
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