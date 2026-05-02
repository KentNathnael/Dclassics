package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private List<book> bookList;

    public BookAdapter(List<book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_featured_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        book currentBook = bookList.get(position);
        double tvRating = currentBook.getRating();

        holder.imgBook.setImageResource(currentBook.getBookID());
        holder.tvBookTitle.setText(currentBook.getTitle());
        holder.tvBookAuthor.setText(currentBook.getAuthor());
        holder.tvStars.setText(getStars(currentBook.getRating()));
        holder.tvRating.setText(String.format("%.1f", tvRating));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBook;
        TextView tvBookTitle, tvBookAuthor, tvStars, tvRating;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.imgBook);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor);
            tvStars = itemView.findViewById(R.id.tvStars);
            tvRating = itemView.findViewById(R.id.tvRating);
        }
    }

    private String getStars(double rating) {
        int fullStars = (int) Math.round(rating); // 4.9 → 5
        StringBuilder stars = new StringBuilder();

        for (int i = 0; i < fullStars; i++) {
            stars.append("★");
        }

        return stars.toString();
    }
}