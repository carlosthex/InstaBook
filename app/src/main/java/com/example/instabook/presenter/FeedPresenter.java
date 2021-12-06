package com.example.instabook.presenter;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.adapters.FeedAdapter;
import com.example.instabook.model.Feed;
import com.example.instabook.ui.fragments.FragmentFeed;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FeedPresenter implements Response.Listener<JSONArray>,
        Response.ErrorListener {

    private List<Feed> feed = new ArrayList<>();
    private FragmentFeed tela;

    public FeedPresenter(FragmentFeed act) {
        this.tela = act;
    }

    public void buscaFeed() {
        ProgressBar loader = tela.getActivity().findViewById(R.id.loading);
        loader.setVisibility(View.VISIBLE);
        RequestQueue queue = Volley.newRequestQueue(tela.getActivity().getApplicationContext());

        JsonArrayRequest requisicao = new JsonArrayRequest(Request.Method.GET,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/postagem/",null,
                this,this);
        queue.add(requisicao);
    }

    /*
    public void compartilhar()
    {
        //O dado, por teste deixei editText
        //EditText info = tela.getActivity().findViewById(R.id.editTextCompart);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String CompartilharString = info.getText().toString();
        sendIntent.putExtra(Intent.EXTRA_TEXT, CompartilharString);
        sendIntent.setType("text/plain");

        //Gambiarra direcionada pelo próprio whatsapp
        sendIntent.setPackage("com.whatsapp");

        try{
            tela.getActivity().startActivity(sendIntent);
        }catch (ActivityNotFoundException ex){
            Toast.makeText(tela.getActivity(), "Whatsapp Não Instalado", Toast.LENGTH_SHORT).show();

        }
    }
    */


    @Override
    public void onResponse(JSONArray response) {
        ProgressBar loader = tela.getActivity().findViewById(R.id.loading);
        feed.clear();
        try {
            for (int x = 0; x <1; x++) {
                for (int i = response.length()-1; i > 0; i--) {
                    feed.add(new Feed(response.getJSONObject(i)));
                }
            }
            FeedAdapter adapter = new FeedAdapter(feed);
            tela.preparaRecyclerView(adapter);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(tela.getActivity().getApplicationContext(), "Erro ao carregar o feed", Toast.LENGTH_SHORT).show();
    }
}
