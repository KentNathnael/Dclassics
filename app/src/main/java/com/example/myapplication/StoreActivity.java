package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    RecyclerView rvStores;
    StoreAdapter adapter;
    List<Store> storeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookstore);

        rvStores = findViewById(R.id.rvStores);

        rvStores.setLayoutManager(new LinearLayoutManager(this));

        storeList = new ArrayList<>();

        storeList.add(new Store(R.drawable.periplus, "Periplus - Plaza Indonesia"));
        storeList.add(new Store(R.drawable.gramedia, "Gramedia - Grand Indonesia"));
        storeList.add(new Store(R.drawable.kino, "Kinokuniya - PIK"));
        storeList.add(new Store(R.drawable.gunungagung, "Gunung Agung - Depok"));

        adapter = new StoreAdapter(storeList);
        rvStores.setAdapter(adapter);
    }
}