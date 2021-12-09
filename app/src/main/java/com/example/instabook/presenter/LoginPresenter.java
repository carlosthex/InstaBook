package com.example.instabook.presenter;

import static android.content.Context.MODE_PRIVATE;

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
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginPresenter implements Response.ErrorListener{

    private TelaLogin tela;
    public LoginPresenter(TelaLogin act) {
        this.tela = act;
    }

    public void autenticarUsuario(TextInputEditText emailLogin, TextInputEditText senhaLogin) {
        RequestQueue queue = Volley.newRequestQueue(tela.getApplicationContext());

        String EmailLoginP = emailLogin.getText().toString();
        String SenhaLoginP = senhaLogin.getText().toString();

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
                        SharedPreferences.Editor editor = prefs.edit();
                        try {
                            //procura na response que vem no onResponse os valores
                            //e coloca nas strings
                            JSONObject teste = response;
                            String userId = response.getString("id");
                            String username = response.getString("nome");
                            String age = response.getString("idade");

                            //coloca eles no editor de preferencias
                            editor.putString("id", userId);
                            editor.putString("nome", username);
                            editor.putString("idade", age);

                            //salva tudo
                            editor.apply();
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