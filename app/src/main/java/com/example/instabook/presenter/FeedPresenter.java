package com.example.instabook.presenter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.adapters.FeedAdapter;
import com.example.instabook.model.Feed;
import com.example.instabook.ui.fragments.FragmentHome;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FeedPresenter implements Response.Listener<JSONArray>,
        Response.ErrorListener {

    private List<Feed> feed = new ArrayList<>();
    private FragmentHome tela;

    public FeedPresenter(FragmentHome act) {
        this.tela = act;
    }

    public void buscaFeed() {
        RequestQueue queue = Volley.newRequestQueue(tela.getActivity().getApplicationContext());

        JsonArrayRequest requisicao = new JsonArrayRequest(Request.Method.GET,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/postagem/",null,
                this,this);
        queue.add(requisicao);
    }

    @Override
    public void onResponse(JSONArray response) {
        feed.clear();
        try {
            for (int x = 0; x <1; x++) {
                for (int i = 0; i < response.length(); i++) {
                    feed.add(new Feed(response.getJSONObject(i)));
                }
            }
            FeedAdapter adapter = new FeedAdapter(feed);
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
