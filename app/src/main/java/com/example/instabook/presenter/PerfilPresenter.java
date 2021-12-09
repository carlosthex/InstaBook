package com.example.instabook.presenter;;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.adapters.PerfilAdapter;
import com.example.instabook.model.Perfil;
import com.example.instabook.ui.TelaLogin;
import com.example.instabook.ui.fragments.FragmentPerfil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PerfilPresenter implements Response.Listener<JSONArray>,
        Response.ErrorListener {

    private List<Perfil> perfil = new ArrayList<>();
    private FragmentPerfil tela;
    private TextView tv;

    public PerfilPresenter(FragmentPerfil act) {
        this.tela = act;
    }

    public void logOut()
    {
        SharedPreferences pref = tela.getActivity().getSharedPreferences("preferencia",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("senha","");
        editor.apply();

        Intent telaLogin = new Intent(tela.getActivity().getApplicationContext(), TelaLogin.class);
        tela.getActivity().startActivity(telaLogin);
        Toast.makeText(tela.getActivity().getApplicationContext(), "Desconectou-se", Toast.LENGTH_SHORT).show();
    }
    
    public void buscaPerfil(View view) {
        RequestQueue queue = Volley.newRequestQueue(tela.getActivity().getApplicationContext());

        SharedPreferences pref = tela.getActivity().getSharedPreferences("preferencia", Context.MODE_PRIVATE);
        String Email = pref.getString("email","Email não existente");

        tv = view.findViewById(R.id.tvNSPerfil);
        tv.setText(pref.getString("nome", "Sem nome definido"));

        tv = view.findViewById(R.id.tvIdadePerfil);
        tv.setText(pref.getString("idade", "18") + " anos");

        tv = view.findViewById(R.id.tvEmailPerfil);
        tv.setText(pref.getString("email", "Email não cadastrado"));

        JsonArrayRequest requisicao = new JsonArrayRequest(Request.Method.GET,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/postagem/email/"+Email,null,
                this,this);
        queue.add(requisicao);
    }

    @Override
    public void onResponse(JSONArray response) {
        ProgressBar loader = tela.getActivity().findViewById(R.id.loadingProfile);
        perfil.clear();
        tv = tela.getView().findViewById(R.id.tvCountProfile);
        tv.setText(response.length() + "");

        try {
            for (int x = 0; x <1; x++) {
                for (int i = response.length()-1; i >= 0; i--) {
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
        Toast.makeText(tela.getActivity().getApplicationContext(), "Erro ao carregar o perfil", Toast.LENGTH_SHORT).show();

    }
}
