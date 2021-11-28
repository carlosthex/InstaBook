package com.example.instabook.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.ui.BottomNavigation;
import com.example.instabook.ui.TelaLogin;
import com.example.instabook.ui.fragments.FragmentHome;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter implements Response.ErrorListener{

    private TelaLogin tela;

    public LoginPresenter(TelaLogin act) {
        this.tela = act;
    }

    public void autenticarUsuario(TextInputLayout email, TextInputLayout senha) {
        RequestQueue queue = Volley.newRequestQueue(tela.getApplicationContext());

        String EmailLoginP = email.getEditText().getText().toString();
        String SenhaLoginP = senha.getEditText().getText().toString();

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", EmailLoginP);
            postData.put("senha", SenhaLoginP);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest requisicao = new JsonObjectRequest(Request.Method.POST,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/usuario/auth", postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(tela.getApplicationContext(), "Autenticação realizada com sucesso",
                                Toast.LENGTH_SHORT).show();
                        Intent bottomNav = new Intent(tela.getApplicationContext(), BottomNavigation.class);
                        tela.startActivity(bottomNav);
                    }
                },this);
        queue.add(requisicao);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(tela.getApplicationContext(), "Erro ao realizar a autenticação",
                Toast.LENGTH_SHORT).show();
    }
}