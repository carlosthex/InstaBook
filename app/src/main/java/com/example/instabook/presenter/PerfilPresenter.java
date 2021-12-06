package com.example.instabook.presenter;;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.adapters.FeedAdapter;
import com.example.instabook.adapters.PerfilAdapter;
import com.example.instabook.model.Feed;
import com.example.instabook.model.Perfil;
import com.example.instabook.ui.TelaLogin;
import com.example.instabook.ui.fragments.FragmentHome;
import com.example.instabook.ui.fragments.FragmentPerfil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PerfilPresenter implements Response.Listener<JSONArray>,
        Response.ErrorListener {

    private List<Perfil> perfil = new ArrayList<>();
    private FragmentPerfil tela;
    private TextView tvPosts;

    public PerfilPresenter(FragmentPerfil act) {
        this.tela = act;
    }



    public void buscaPerfil() {
        RequestQueue queue = Volley.newRequestQueue(tela.getActivity().getApplicationContext());


        SharedPreferences pref1 = tela.getActivity().getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        String Email = pref1.getString("email","Email não existente");


        JsonArrayRequest requisicao = new JsonArrayRequest(Request.Method.GET,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/postagem/email/"+Email,null,
                this,this);
        queue.add(requisicao);
    }

    @Override
    public void onResponse(JSONArray response) {
        ProgressBar loader = tela.getActivity().findViewById(R.id.loadingProfile);
        perfil.clear();
        tvPosts = tela.getView().findViewById(R.id.tvCountProfile);
        tvPosts.setText(response.length() + "");
        try {
            for (int x = 0; x <1; x++) {
                for (int i = 0; i < response.length(); i++) {
                    perfil.add(new Perfil(response.getJSONObject(i)));
                }
            }
            PerfilAdapter adapter = new PerfilAdapter(perfil);
            tela.preparaRecyclerView(adapter);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
