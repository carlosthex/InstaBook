package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.instabook.R;
import com.example.instabook.presenter.LoginPresenter;
import com.google.android.material.textfield.TextInputEditText;

public class TelaLogin extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        LoginPresenter presenterLogin = new LoginPresenter(this);

        TextInputEditText tvEmail = findViewById(R.id.tvEmail);
        TextInputEditText tvSenha = findViewById(R.id.tvSenha);

        presenterLogin.testarPrefComp(tvEmail, tvSenha);

        Button botao = findViewById(R.id.buttonCadastra);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastro = new Intent(getApplicationContext(), TelaCadastro.class);
                startActivity(cadastro);
            }
        });

        botao = findViewById(R.id.buttonLogin);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenterLogin.autenticarUsuario(tvEmail, tvSenha);
            }
        });
    }
}

