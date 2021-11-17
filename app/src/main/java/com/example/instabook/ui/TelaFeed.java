package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.instabook.R;
import com.example.instabook.adapters.FeedAdapter;
import com.example.instabook.model.Feed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TelaFeed extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {

    private List<Feed> feed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        mostraFeed();
    }

    private void mostraFeed() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest requisicao = new JsonArrayRequest(Request.Method.GET,
                "http://ec2-18-116-202-134.us-east-2.compute.amazonaws.com:7777/postagem/",null,
                this,this);
        queue.add(requisicao);
        /*JSONObject obj = new JSONObject();
        try {
            obj.getJSONObject("autorPostagem").getString("nome");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void onResponse(JSONArray response) {

        try {
            for (int x = 0; x <1; x++) {
                for (int i = 0; i < response.length(); i++) {
                    feed.add(new Feed(response.getJSONObject(i)));
                }
            }
            RecyclerView rv = findViewById(R.id.rvFeed);
            FeedAdapter adapter = new FeedAdapter(feed);
            LinearLayoutManager llm =  new LinearLayoutManager(getApplicationContext());
            rv.setLayoutManager(llm);
            rv.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {


    }
}