package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class TelaCadastro extends AppCompatActivity implements Response.ErrorListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        getSupportActionBar().hide();;
        getWindow() .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button botaoCadastro = findViewById(R.id.buttonCadastrar);
        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            cadastraUsuario();
            }
        });
    }

    private void cadastraUsuario() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        TextInputLayout NS = findViewById(R.id.editTextNS);
        TextInputLayout Idade = findViewById(R.id.editTextIdade);
        TextInputLayout Email = findViewById(R.id.editTextEmail);
        TextInputLayout Senha = findViewById(R.id.editTextSenha);

        String NomeP = NS.getEditText().getText().toString();
        String IdadeP = Idade.getEditText().getText().toString();
        String EmailP = Email.getEditText().getText().toString();
        String SenhaP = Senha.getEditText().getText().toString();

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
                        Intent feed = new Intent(getApplicationContext(), TelaFeed.class);
                        startActivity(feed);
                    }
                },this);
        queue.add(requisicao);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Erro ao realizar o cadastro", Toast.LENGTH_SHORT).show();
    }
}

