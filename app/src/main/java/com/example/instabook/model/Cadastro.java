package com.example.instabook.model;

import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.ui.TelaCadastro;

import org.json.JSONException;
import org.json.JSONObject;

public class Cadastro extends TelaCadastro{
    private void cadastraUsuario() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        EditText NS = findViewById(R.id.editTextNS);
        EditText Idade = findViewById(R.id.editTextIdade);
        EditText Email = findViewById(R.id.editTextEmail);
        EditText Senha = findViewById(R.id.editTextSenha);

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
                        Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
                    }
                },this);
        queue.add(requisicao);
    }
}