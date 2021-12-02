package com.example.instabook.presenter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.ui.BottomNavigation;
import com.example.instabook.ui.TelaLogin;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
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
                        //chama as preferencias
                        SharedPreferences prefs = tela.getSharedPreferences("preferencia", MODE_PRIVATE);
                        //chama o editor de preferencias
                        SharedPreferences.Editor ed = prefs.edit();
                        try {
                            //procura na response que vem no onResponse o valor id, e coloca na string userId
                            JSONObject teste = response;
                            String userId = response.getString("id");
                            //procura na response que vem no onResponse o valor nome, e coloca na string username
                            String username = response.getString("nome");
                            //procura na response que vem no onResponse o valor idade, e coloca na string age
                            String age = response.getString("idade");
                            //coloca eles no editor de preferencias
                            ed.putString("id", userId);
                            ed.putString("nome", username);
                            ed.putString("idade", age);
                            //salva tudo
                            ed.apply();
                        } catch (JSONException e) {
                            //Se der erro ao encontrar o valor na response faz um toast
                            Toast.makeText(tela.getApplicationContext(), "Ocorreu algum erro ao recuperar dados do usuário",
                                    Toast.LENGTH_SHORT).show();
                        }

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