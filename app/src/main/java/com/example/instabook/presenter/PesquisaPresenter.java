package com.example.instabook.presenter;;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.adapters.PesquisaAdapter;
import com.example.instabook.model.Pesquisa;
import com.example.instabook.ui.fragments.FragmentPesquisa;
import com.google.android.material.textfield.TextInputLayout;

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

        TextInputLayout emailPerfil = tela.getActivity().findViewById(R.id.editTextPesquisa);

        String email = emailPerfil.getEditText().getText().toString();

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
                for (int i = response.length()-1; i >= 0; i--) {
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
        Toast.makeText(tela.getActivity().getApplicationContext(), "Erro ao realizar a pesquisa", Toast.LENGTH_SHORT).show();

    }
}
