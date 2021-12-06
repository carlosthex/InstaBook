package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

        TextInputEditText tvEmail = findViewById(R.id.preferenciaEmail);
        TextInputEditText tvSenha = findViewById(R.id.preferenciaSenha);

        // chama "preferencias"
        SharedPreferences pref = getSharedPreferences("preferencia",MODE_PRIVATE);

        // Pega os valores em "email" e "senha"
        String preferenciaEmail = pref.getString("email","");
        String preferenciaSenha = pref.getString("senha","");

        tvEmail.setText(preferenciaEmail);
        tvSenha.setText(preferenciaSenha);

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

                //cria uma variavel de preferencia que fica salva no app ate que ela seja alterada
                SharedPreferences prefs = getSharedPreferences("preferencia",MODE_PRIVATE);
                // chama o editor de preferencias
                SharedPreferences.Editor editor = prefs.edit();

                //cria o campo expecifico
                editor.putString("email", tvEmail.getText().toString());
                editor.putString("senha", tvSenha.getText().toString());

                //aplica a edição
                editor.apply();

                //chama as variaveis para serem utilizadas
                SharedPreferences pref1 = getSharedPreferences("preferencia",MODE_PRIVATE);

                // Teste para ver se o email estava sendo salvo e esta ok
                String teste = pref1.getString("email","nops");

                //poe a variavel salva nas preferencias na String
                Log.d("TAG", "autenticarUsuario: "+teste);
            }
        });
    }
}