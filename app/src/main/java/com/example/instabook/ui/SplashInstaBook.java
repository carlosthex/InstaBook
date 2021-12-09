package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.instabook.R;
import com.example.instabook.presenter.SplashPresenter;


public class SplashInstaBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_insta_book);
        SplashPresenter splashPresenter = new SplashPresenter(this);
        SharedPreferences pref = getSharedPreferences("preferencia",MODE_PRIVATE);
        String prefEmail = pref.getString("email","");
        String prefSenha = pref.getString("senha","");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                splashPresenter.autenticarUsuarioStrig(prefEmail, prefSenha);
            }
        }, 2000);
    }
}