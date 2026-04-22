package com.example.myapplication;

public class book {
    private int bookID;
    private String title;
    private String author;

    public book(int bookID, String title, String author){
        this.bookID = bookID;
        this.title = title;
        this.author = author;
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
}
