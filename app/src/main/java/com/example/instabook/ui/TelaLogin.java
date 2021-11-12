package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.instabook.R;
import com.example.instabook.ui.TelaCadastro;

public class TelaLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);

        Button botaoCadastrar = findViewById(R.id.botaoCadastra);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastro = new Intent(getApplicationContext(), TelaCadastro.class);
                startActivity(cadastro);
            }
        });
    }
}