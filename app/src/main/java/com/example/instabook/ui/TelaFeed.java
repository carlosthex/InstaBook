package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.instabook.R;
import com.example.instabook.presenter.FeedPresenter;

public class TelaFeed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        FeedPresenter presenterFeed = new FeedPresenter(this);

        presenterFeed.buscaFeed();

        //botaoProvisório(vai ser pelo botton navigation depois
        Button botaoGambiarra = findViewById(R.id.buttonProvisório);
        botaoGambiarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postagem = new Intent(getApplicationContext(), TelaPostagem.class);
                startActivity(postagem);
            }
        });
        //termina a gambiarra aqui

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