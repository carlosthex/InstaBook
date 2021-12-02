package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.instabook.presenter.FeedPresenter;
import com.example.instabook.presenter.LoginPresenter;
import com.example.instabook.ui.TelaCadastro;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class TelaLogin extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        getSupportActionBar().hide();
        getWindow() .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LoginPresenter presenterLogin = new LoginPresenter(this);

        TextInputEditText tvEmail = findViewById(R.id.preferenciaEmail);
        TextInputEditText tvSenha = findViewById(R.id.preferenciaSenha);
        // chama "preferencias"
        SharedPreferences pref = getSharedPreferences("preferencia",MODE_PRIVATE);
        // Pega o valor em "email"
        String preferenciaAEmail = pref.getString("email","");
        // Pega o valor em "senha"
        String preferenciaASenha = pref.getString("senha","");
        tvEmail.setText(preferenciaAEmail);
        tvSenha.setText(preferenciaASenha);

        Button botao = findViewById(R.id.botaoCadastra);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cadastro = new Intent(getApplicationContext(), TelaCadastro.class);
                startActivity(cadastro);
            }
        });

        botao = findViewById(R.id.botaoLogin);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputLayout EmailLogin = findViewById(R.id.editTextEmailLogin);
                TextInputLayout SenhaLogin = findViewById(R.id.editTextSenhaLogin);
                presenterLogin.autenticarUsuario(EmailLogin, SenhaLogin);
                //cria uma variavel de preferencia que fica salva no app ate que ela seja alterada
                SharedPreferences prefs = getSharedPreferences("preferencia",MODE_PRIVATE);
                // chama o editor de preferencias
                SharedPreferences.Editor ed = prefs.edit();
                //cria o campo expecifico
                ed.putString("email", EmailLogin.getEditText().getText().toString());
                ed.putString("senha", SenhaLogin.getEditText().getText().toString());
                //aplica a edição
                ed.apply();
                //chama aas variaveis para serem utilizadas
                SharedPreferences pref1 = getSharedPreferences("preferencia",MODE_PRIVATE);
                // Teste para ver se o email estava sendo salvo e esta ok
                String teste = pref1.getString("email","nops");
                //poe a variavel salva nas preferencias na String
                Log.d("TAG", "autenticarUsuario: "+teste);
            }
        });
    }
}