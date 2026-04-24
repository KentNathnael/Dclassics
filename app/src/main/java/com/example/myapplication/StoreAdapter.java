package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private List<Store> storeList;

    public StoreAdapter(List<Store> storeList) {
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_store, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Store store = storeList.get(position);
        holder.tvName.setText(store.getName());
        holder.imgStore.setImageResource(store.getImageResId());
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgStore;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStore = itemView.findViewById(R.id.imgStore);
            tvName = itemView.findViewById(R.id.tvStoreName);
        }
    }
}