package com.example.myapplication;

public class book {
    private int bookID;
    private String title;
    private String author;

    private double rating;
    public book(int bookID, String title, String author, double rating){
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public int getBookID() {
        return bookID;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
