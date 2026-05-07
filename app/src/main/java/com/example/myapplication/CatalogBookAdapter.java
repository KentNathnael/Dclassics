package com.example.myapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatalogBookAdapter extends RecyclerView.Adapter<CatalogBookAdapter.ViewHolder> {

    private List<book> bookList;

    public CatalogBookAdapter(List<book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public CatalogBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_catalog_books, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogBookAdapter.ViewHolder holder, int position) {
        book currentBook = bookList.get(position);

        holder.imgBook.setImageResource(currentBook.getBookID());
        holder.tvTitle.setText(currentBook.getTitle());
        holder.tvAuthor.setText(currentBook.getAuthor());
        holder.tvPrice.setText(currentBook.getPrice());

//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
//            intent.putExtra("image", currentBook.getBookID());
//            intent.putExtra("title", currentBook.getTitle());
//            intent.putExtra("author", currentBook.getAuthor());
//            intent.putExtra("price", currentBook.getPrice());
//            v.getContext().startActivity(intent);
//        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBook;
        TextView tvTitle, tvAuthor, tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.imgBook);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}