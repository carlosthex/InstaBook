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
import com.example.instabook.presenter.CadastroPresenter;
import com.example.instabook.presenter.LoginPresenter;

import org.json.JSONException;
import org.json.JSONObject;

public class TelaCadastro extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        CadastroPresenter presenterCadastro = new CadastroPresenter(this);

        Button botaoCadastro = findViewById(R.id.buttonCadastrar);
        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText NS = findViewById(R.id.editTextNS);
                EditText Idade = findViewById(R.id.editTextIdade);
                EditText Email = findViewById(R.id.editTextEmail);
                EditText Senha = findViewById(R.id.editTextSenha);
                presenterCadastro.cadastrarUsuario(NS,Idade,Email,Senha);
            }
        });
    }
}