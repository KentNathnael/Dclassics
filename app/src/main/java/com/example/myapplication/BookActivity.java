package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
    private TextView tvNewArrivals;
    private ImageButton btnMenu;
    private LinearLayout menuDropdown;
    private boolean isMenuVisible = false;
    private TextView tabFiction, tabNonFiction;
    private List<book> fictionBooks, nonFictionBooks;
    private List<book> fictionNewArrivals, nonFictionNewArrivals;
    private EditText etSearch;
    private boolean isFictionSelected = true;
    private TextView tvNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        rvBooks = findViewById(R.id.rvBooks);
        rvNewArrivals = findViewById(R.id.rvNewArrivals);

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

        tabFiction = findViewById(R.id.tabFiction);
        tabNonFiction = findViewById(R.id.tabNonFiction);
        tvNotFound = findViewById(R.id.tvNotFound);

        rvBooks = findViewById(R.id.rvBooks);
        rvNewArrivals = findViewById(R.id.rvNewArrivals);
        tvNewArrivals = findViewById(R.id.tvNewArrivals);

        setupData();
        setupRecyclerViews();
        showFiction();

        tabFiction.setOnClickListener(v -> {
            etSearch.setText("");
            showFiction();
        });
        tabNonFiction.setOnClickListener(v -> {
            etSearch.setText("");
            showNonFiction();
        });

        etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterBooks(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterBooks(String query) {
        List<book> sourceCatalog = isFictionSelected ? fictionBooks : nonFictionBooks;
        List<book> sourceNewArrivals = isFictionSelected ? fictionNewArrivals : nonFictionNewArrivals;

        List<book> filteredCatalog = filterByTitle(sourceCatalog, query);
        List<book> filteredNewArrivals = filterByTitle(sourceNewArrivals, query);

        catalogAdapter = new CatalogBookAdapter(filteredCatalog);
        newArrivalAdapter = new CatalogBookAdapter(filteredNewArrivals);

        rvBooks.setAdapter(catalogAdapter);
        rvNewArrivals.setAdapter(newArrivalAdapter);

        boolean catalogEmpty = filteredCatalog.isEmpty();
        boolean newArrivalsEmpty = filteredNewArrivals.isEmpty();

        rvBooks.setVisibility(catalogEmpty ? View.GONE : View.VISIBLE);
        tvNewArrivals.setVisibility(newArrivalsEmpty ? View.GONE : View.VISIBLE);
        rvNewArrivals.setVisibility(newArrivalsEmpty ? View.GONE : View.VISIBLE);

        tvNotFound.setVisibility((catalogEmpty && newArrivalsEmpty) ? View.VISIBLE : View.GONE);
    }

    private List<book> filterByTitle(List<book> books, String query) {
        if (query == null || query.trim().isEmpty()) {
            return books;
        }
        String lowerQuery = query.trim().toLowerCase();
        List<book> filtered = new ArrayList<>();
        for (book b : books) {
            if (b.getTitle() != null && b.getTitle().toLowerCase().contains(lowerQuery)) {
                filtered.add(b);
            }
        }
        return filtered;
    }

    private void resetSectionVisibility() {
        rvBooks.setVisibility(View.VISIBLE);
        tvNewArrivals.setVisibility(View.VISIBLE);
        rvNewArrivals.setVisibility(View.VISIBLE);
        tvNotFound.setVisibility(View.GONE);
    }

    private void setupData() {
        fictionBooks = new ArrayList<>();
        fictionBooks.add(new book(R.drawable.inabluemoon, "In a Blue Moon", "Ilana Tan", 4, "Rp 139.000"));
        fictionBooks.add(new book(R.drawable.f2, "Judge Stone", "Viola Davis, James Patterson", 3.9, "Rp 279.000"));
        fictionBooks.add(new book(R.drawable.f3, "To the Bridge of The World", "Eowyn Ivey", 3.5, "Rp 339.000"));
        fictionBooks.add(new book(R.drawable.theoofgolden, "Theo Of Golden", "Allen Levi", 4.8,"Rp 449.000"));

        nonFictionBooks = new ArrayList<>();
        nonFictionBooks.add(new book(R.drawable.nonf1, "Strangers", "Belle Burden", 4, "Rp 739.000"));
        nonFictionBooks.add(new book(R.drawable.atomichab, "Atomic Habits", "James Clear", 4, "Rp 279.000"));
        nonFictionBooks.add(new book(R.drawable.nonf3, "The Night We Met", "Abby Jimenez", 4, "Rp 399.000"));
        nonFictionBooks.add(new book(R.drawable.murdle, "Murdle Vol.1", "G.T.Karber", 4, "Rp 449.000"));

        fictionNewArrivals = new ArrayList<>();
        fictionNewArrivals.add(new book(R.drawable.nf1, "Just Friend", "Haley Pham", 3, "Rp 239.000"));
        fictionNewArrivals.add(new book(R.drawable.nf2, "The Correspondent", "Virginia Evans", 3, "Rp 279.000"));
        fictionNewArrivals.add(new book(R.drawable.heated_riv, "Heated Rivalry", "Rachel Reid", 4,"Rp 549.000"));
        fictionNewArrivals.add(new book(R.drawable.nf4, "The Faraway Inn", "Sarah Beth Durst", 6, "Rp 319.000"));

        nonFictionNewArrivals = new ArrayList<>();
        nonFictionNewArrivals.add(new book(R.drawable.newnonf1, "Night", "Elie Wiesel", 4, "Rp 539.000"));
        nonFictionNewArrivals.add(new book(R.drawable.newnonf2, "The Let Them Theory", "Mel Robbins", 4, "Rp 779.000"));nonFictionNewArrivals.add(new book(R.drawable.newnonf3, "Project Hail Mary", "Andry Weir", 4, "Rp 849.000"));nonFictionNewArrivals.add(new book(R.drawable.stand, "Stand", "Cory Booker", 4, "Rp 429.000"));
    }

    private void setupRecyclerViews() {
        rvBooks.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        rvNewArrivals.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
    }

    private void showFiction() {
        isFictionSelected = true;
        resetSectionVisibility();
        catalogAdapter = new CatalogBookAdapter(fictionBooks);
        newArrivalAdapter = new CatalogBookAdapter(fictionNewArrivals);

        rvBooks.setAdapter(catalogAdapter);
        rvNewArrivals.setAdapter(newArrivalAdapter);

        tabFiction.setTextColor(getColor(R.color.primary_dark));
        tabNonFiction.setTextColor(getColor(R.color.dark_gray));

        tabFiction.setPaintFlags(tabFiction.getPaintFlags() | android.graphics.Paint.UNDERLINE_TEXT_FLAG);
        tabNonFiction.setPaintFlags(tabNonFiction.getPaintFlags() & (~android.graphics.Paint.UNDERLINE_TEXT_FLAG));
    }

    private void showNonFiction() {
        isFictionSelected = false;
        resetSectionVisibility();
        catalogAdapter = new CatalogBookAdapter(nonFictionBooks);
        newArrivalAdapter = new CatalogBookAdapter(nonFictionNewArrivals);

        rvBooks.setAdapter(catalogAdapter);
        rvNewArrivals.setAdapter(newArrivalAdapter);

        tabNonFiction.setTextColor(getColor(R.color.primary_dark));
        tabFiction.setTextColor(getColor(R.color.dark_gray));

        tabNonFiction.setPaintFlags(tabNonFiction.getPaintFlags() | android.graphics.Paint.UNDERLINE_TEXT_FLAG);
        tabFiction.setPaintFlags(tabFiction.getPaintFlags() & (~android.graphics.Paint.UNDERLINE_TEXT_FLAG));
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