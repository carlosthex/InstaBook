package com.example.instabook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.instabook.R;


public class SplashInstaBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_insta_book);

        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.pagina);
        mediaPlayer.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Login=new Intent(getApplicationContext(), TelaLogin.class);
                mediaPlayer.stop();
                startActivity(Login);
            }
        }, 2000);
    }
}