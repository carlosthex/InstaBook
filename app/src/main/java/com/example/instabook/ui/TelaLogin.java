package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.ui.TelaCadastro;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class TelaLogin extends AppCompatActivity implements Response.ErrorListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        Button botaoCadastrar = findViewById(R.id.botaoCadastra);
        Button botaoLogin = findViewById(R.id.botaoLogin);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastro = new Intent(getApplicationContext(), TelaCadastro.class);
                startActivity(cadastro);
            }
        });

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autenticarUsuario();
            }
        });

    }

    private void autenticarUsuario() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        TextInputLayout EmailLogin = findViewById(R.id.editTextEmailLogin);
        TextInputLayout SenhaLogin = findViewById(R.id.editTextSenhaLogin);

        String EmailLoginP = EmailLogin.getEditText().getText().toString();
        String SenhaLoginP = SenhaLogin.getEditText().getText().toString();

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
                        Toast.makeText(getApplicationContext(), "Autenticação realizada com sucesso", Toast.LENGTH_SHORT).show();
                        Intent feed = new Intent(getApplicationContext(), TelaFeed.class);
                        startActivity(feed);
                    }
                },this);
        queue.add(requisicao);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Erro ao realizar a autenticação", Toast.LENGTH_SHORT).show();
    }
}
