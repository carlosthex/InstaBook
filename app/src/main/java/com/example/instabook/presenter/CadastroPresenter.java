package com.example.instabook.presenter;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.ui.BottomNavigation;
import com.example.instabook.ui.TelaCadastro;
import com.example.instabook.ui.fragments.FragmentHome;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroPresenter implements Response.ErrorListener{
    private TelaCadastro tela;

    public CadastroPresenter(TelaCadastro act) {
        this.tela = act;
    }

    public void cadastrarUsuario(EditText NS, EditText Idade,EditText Email,EditText Senha) {
        RequestQueue queue = Volley.newRequestQueue(tela.getApplicationContext());

        String NomeP = NS.getText().toString();
        String IdadeP = Idade.getText().toString();
        String EmailP = Email.getText().toString();
        String SenhaP = Senha.getText().toString();

        JSONObject postData = new JSONObject();
        try {
            postData.put("nome", NomeP);
            postData.put("idade", IdadeP);
            postData.put("email", EmailP);
            postData.put("senha", SenhaP);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest requisicao = new JsonObjectRequest(Request.Method.POST,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/usuario", postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(tela.getApplicationContext(), "Cadastro realizado com sucesso",
                                Toast.LENGTH_SHORT).show();
                        Intent bottomNav = new Intent(tela.getApplicationContext(), BottomNavigation.class);
                        tela.startActivity(bottomNav);
                    }
                },this);
        queue.add(requisicao);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(tela.getApplicationContext(), "Erro ao realizar o cadastro",
                Toast.LENGTH_SHORT).show();
    }
}
