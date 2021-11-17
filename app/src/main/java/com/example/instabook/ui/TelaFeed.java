package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.instabook.R;
import com.example.instabook.presenter.FeedPresenter;

public class TelaFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        FeedPresenter presenterFeed = new FeedPresenter(this);

        presenterFeed.buscaFeed();
    }

    //O prepara é acessado no presenter
    public void preparaRecyclerView(RecyclerView.Adapter adapter){
        RecyclerView rv = findViewById(R.id.rvFeed);
        LinearLayoutManager llm =  new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);
    }
}