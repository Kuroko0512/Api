package com.example.api.Models;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.List;

public class CartaItem {

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_CARD = 1;

    private int type;
    private String title;
    private String cardTitle;
    private String cardPrice;

    public CartaItem(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public CartaItem(int type, String cardTitle, String cardPrice) {
        this.type = type;
        this.cardTitle = cardTitle;
        this.cardPrice = cardPrice;
    }

    public int getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardPrice() {
        return cardPrice;
    }
}

