package com.example.myapplication;

public class Store {
    private int imageResId;
    private String name;

    public Store(int imageResId, String name) {
        this.imageResId = imageResId;
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getName() {
        return name;
    }
}