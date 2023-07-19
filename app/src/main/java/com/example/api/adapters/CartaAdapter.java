package com.example.api.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Models.CartaItem;
import com.example.api.R;

import java.util.List;

public class CartaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CartaItem> itemList;

    public CartaAdapter(List<CartaItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == CartaItem.TYPE_TITLE) {
            View titleView = inflater.inflate(R.layout.item_card_title, parent, false);
            return new TitleViewHolder(titleView);
        } else {
            View cardView = inflater.inflate(R.layout.item_card, parent, false);
            return new CardViewHolder(cardView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CartaItem item = itemList.get(position);
        if (item.getType() == CartaItem.TYPE_TITLE) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
            titleViewHolder.titleTextView.setText(item.getTitle());
        } else {
            CardViewHolder cardViewHolder = (CardViewHolder) holder;
            cardViewHolder.titleTextView.setText(item.getCardTitle());
            cardViewHolder.priceTextView.setText(item.getCardPrice());
            cardViewHolder.addButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getType();
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
        }
    }

    private static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView priceTextView;
        Button addButton;

        CardViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            addButton = itemView.findViewById(R.id.addButton);
            addButton.setVisibility(View.GONE); // Oculta el botón en las tarjetas de título
        }
    }
}



