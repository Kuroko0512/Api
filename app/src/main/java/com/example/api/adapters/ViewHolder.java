package com.example.api.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView titleTextView;
    public TextView priceTextView;
    public Button addButton;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleTextView);
        priceTextView = itemView.findViewById(R.id.priceTextView);
        addButton = itemView.findViewById(R.id.addButton);
    }
}
