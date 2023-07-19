package com.example.api;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.api.Models.Platillo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Models.CartaItem;
import com.example.api.adapters.CartaAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CartaFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartaAdapter cartaAdapter;

    private List<CartaItem> cartaItemList = new ArrayList<>();;

    public CartaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_carta, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));



        makeGetRequest("https://giecom.azurewebsites.net/RestauranteApi/Platillo/listadoActual");
        cartaItemList.add(new CartaItem(CartaItem.TYPE_TITLE, "Bebidas"));
        cartaItemList.add(new CartaItem(CartaItem.TYPE_CARD, "Título 4", "$8.00"));
        cartaItemList.add(new CartaItem(CartaItem.TYPE_CARD, "Título 5", "$9.00"));
        cartaItemList.add(new CartaItem(CartaItem.TYPE_TITLE, "Extras"));
        cartaItemList.add(new CartaItem(CartaItem.TYPE_CARD, "Título 6", "$8.00"));
        cartaItemList.add(new CartaItem(CartaItem.TYPE_CARD, "Título 7", "$9.00"));
        // Agrega más secciones y cartas según tus necesidades


        cartaAdapter = new CartaAdapter(cartaItemList);
        recyclerView.setAdapter(cartaAdapter);

        return rootView;
    }


    public static void makeGetRequest(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Error en la solicitud GET
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    // Aquí puedes procesar la respuesta de la API

                } else {
                    // Error en la respuesta de la API
                    System.out.println("Error in GET request: " + response.code());
                }
            }
        });
    }



    public void GetBebidas(){

    }

    public void getExtas(){

    }
}

