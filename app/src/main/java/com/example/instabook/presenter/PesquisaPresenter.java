package com.example.instabook.presenter;;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.adapters.FeedAdapter;
import com.example.instabook.adapters.PerfilAdapter;
import com.example.instabook.adapters.PesquisaAdapter;
import com.example.instabook.model.Feed;
import com.example.instabook.model.Perfil;
import com.example.instabook.model.Pesquisa;
import com.example.instabook.ui.TelaCadastro;
import com.example.instabook.ui.TelaLogin;
import com.example.instabook.ui.fragments.FragmentHome;
import com.example.instabook.ui.fragments.FragmentPerfil;
import com.example.instabook.ui.fragments.FragmentPesquisa;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PesquisaPresenter implements Response.Listener<JSONArray>,
        Response.ErrorListener {

    private List<Pesquisa> pesquisa = new ArrayList<>();
    private FragmentPesquisa tela;

    public PesquisaPresenter(FragmentPesquisa act) {
        this.tela = act;
    }

    public void buscaPesquisa() {
        RequestQueue queue = Volley.newRequestQueue(tela.getActivity().getApplicationContext());

        EditText emailPerfil = tela.getActivity().findViewById(R.id.editTextPesquisa);

        String email = emailPerfil.getText().toString();

        JsonArrayRequest requisicao = new JsonArrayRequest(Request.Method.GET,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/postagem/email/"+email,null,
                this,this);
        queue.add(requisicao);
    }

    @Override
    public void onResponse(JSONArray response) {
        pesquisa.clear();
        try {
            for (int x = 0; x <1; x++) {
                for (int i = 0; i < response.length(); i++) {
                    pesquisa.add(new Pesquisa(response.getJSONObject(i)));
                }
            }
            PesquisaAdapter adapter = new PesquisaAdapter(pesquisa);
            tela.preparaRecyclerView(adapter);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
