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
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class TelaPostagem extends AppCompatActivity implements Response.ErrorListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_postagem);

        Button botaoPublicar = findViewById(R.id.botaoPublicar);
        botaoPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publicaPostagem();
                SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS.X");
                Date data = new Date();
                String dataFormatada = formataData.format(data);
                Toast.makeText(getApplicationContext(), "Data formatada " + dataFormatada, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void publicaPostagem() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        EditText PP = findViewById(R.id.editTextPostagem);

        String PostagemP = PP.getText().toString();


        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date data = new Date();
        String dataFormatada = formataData.format(data);

        JSONObject postData = new JSONObject();
        try {
            postData.put("conteudo", PostagemP);
            postData.put("dataPostagem", dataFormatada);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest requisicao = new JsonObjectRequest(Request.Method.POST,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/postagem/post", postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Postagem realizada com sucesso"+ PostagemP, Toast.LENGTH_SHORT).show();
                        Intent feed = new Intent(getApplicationContext(), TelaFeed.class);
                        startActivity(feed);
                    }
                },this);
        queue.add(requisicao);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "Erro ao realizar a postagem", Toast.LENGTH_SHORT).show();
    }
}