package com.example.instabook.presenter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.model.Cadastro;
import com.example.instabook.ui.BottomNavigation;
import com.example.instabook.ui.fragments.FragmentHome;
import com.example.instabook.ui.fragments.FragmentPost;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class PostPresenter implements Response.ErrorListener {

    private FragmentPost tela;

    public PostPresenter(FragmentPost act) {
        this.tela = act;
    }


    public void publicaPostagem() {
        RequestQueue queue = Volley.newRequestQueue(tela.getActivity().getApplicationContext());
        EditText PP = tela.getActivity().findViewById(R.id.editTextPostagem);

        String PostagemP = PP.getText().toString();
        SharedPreferences pref = tela.getActivity().getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        String emailPreferencia = pref.getString("email","n");

        SimpleDateFormat formataData = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        formataData.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date data = new Date();
        String dataFormatada = formataData.format(data);

        JSONObject emailAutor = new JSONObject();
        try {
            emailAutor.put("email", emailPreferencia);
                    }catch (JSONException e){
            e.printStackTrace();
        }
        JSONObject postData = new JSONObject();
        try {
            postData.put("conteudo", PostagemP);
            postData.put("dataPostagem", dataFormatada);
            postData.put("autorPostagem",emailAutor);
                    } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("tag",postData.toString());
        JsonObjectRequest requisicao = new JsonObjectRequest(Request.Method.POST,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/postagem/post", postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(tela.getActivity().getApplicationContext(), "Postagem realizada com sucesso"+ PostagemP, Toast.LENGTH_SHORT).show();
                        Intent bottomNav = new Intent(tela.getActivity().getApplicationContext(), BottomNavigation.class);
                        tela.startActivity(bottomNav);
                    }
                },this);
        queue.add(requisicao);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(tela.getActivity().getApplicationContext(), "Erro ao realizar a postagem", Toast.LENGTH_SHORT).show();
    }
}