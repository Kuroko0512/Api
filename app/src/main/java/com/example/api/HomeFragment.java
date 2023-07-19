package com.example.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private Button loginButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private SharedPreferences sharedPreferences;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Obtén una referencia a los elementos de la interfaz de usuario
        loginButton = rootView.findViewById(R.id.loginButton);
        usernameEditText = rootView.findViewById(R.id.usernameEditText);
        passwordEditText = rootView.findViewById(R.id.passwordEditText);

        // Inicializa SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("crendencial", Context.MODE_PRIVATE);

        // Configura el OnClickListener para el botón
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        return rootView;
    }

    private void performLogin() {
        OkHttpClient client = new OkHttpClient();

        // Obtén los valores de los campos EditText
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Crea el objeto JSON con los datos de usuario
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("idUsuario", 0);
            jsonBody.put("nombre_usuario", username);
            jsonBody.put("contrasena", password);
            jsonBody.put("fkrol", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Crea la solicitud POST
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, jsonBody.toString());

        Request request = new Request.Builder()
                .url("https://giecom.azurewebsites.net/RestauranteApi/Usuario/Login")
                .post(requestBody)
                .build();

        // Realiza la solicitud
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    try {
                        JSONObject jsonResponse = new JSONObject(responseData);
                        String token = jsonResponse.getString("token"); // Obtener el valor del token
                        // Guardar el token en SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", token);
                        editor.apply();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(requireContext(), "Credenciales correctas", Toast.LENGTH_SHORT).show();
                        }
                    });
                    // Realizar cualquier acción adicional con los datos de respuesta
                } else {
                    requireActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    });
                    String errorMessage = response.message();
                    // Realizar cualquier acción adicional con el mensaje de error
                }
            }
        });
    }
}
