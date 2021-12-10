package com.example.instabook.presenter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroPresenter implements Response.ErrorListener{
    private TelaCadastro tela;

    public CadastroPresenter(TelaCadastro act) {
        this.tela = act;
    }

    //Futuramente retirar nome e idade da praferencia compartilhada
    public void salvarPrefComp(String email, String senha, String nome, String idade)
    {
        //cria uma variavel de preferencia que fica salva no app ate que ela seja alterada
        SharedPreferences prefs = tela.getSharedPreferences("preferencia",MODE_PRIVATE);
        // chama o editor de preferencias
        SharedPreferences.Editor editor = prefs.edit();

        //cria o campo expecifico
        editor.putString("email", email);
        editor.putString("senha", senha);
        editor.putString("nome", nome);
        editor.putString("idade", idade);

        //aplica a edição
        editor.apply();
    }

    public void cadastrarUsuario(EditText NS, EditText Idade,EditText Email,EditText Senha) {
        RequestQueue queue = Volley.newRequestQueue(tela.getApplicationContext());

        String nomeString = NS.getText().toString();
        String idadeString = Idade.getText().toString();
        String emailString = Email.getText().toString();
        String senhaString = Senha.getText().toString();

        JSONObject postData = new JSONObject();
        try {
            postData.put("nome", nomeString);
            postData.put("idade", idadeString);
            postData.put("email", emailString);
            postData.put("senha", senhaString);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest requisicao = new JsonObjectRequest(Request.Method.POST,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/usuario", postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        salvarPrefComp(emailString,senhaString,nomeString,idadeString);

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


