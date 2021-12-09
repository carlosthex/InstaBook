package com.example.instabook.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.instabook.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView bnv = findViewById(R.id.bottomNavigationView);
        NavHostFragment nhf = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController nc = nhf.getNavController();

        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.fragmentFeed) {
                    nc.navigate(R.id.fragmentFeed);
                    return true;
                }
                if (item.getItemId() == R.id.fragmentPesquisa) {
                    //nhc.navigate(R.id.fragmentTwo);
                    nc.navigate(R.id.fragmentPesquisa);
                    return true;
                }
                if (item.getItemId() == R.id.fragmentPost) {
                    nc.navigate(R.id.fragmentPost);
                    return true;
                }
                if (item.getItemId() == R.id.fragmentPerfil) {
                    nc.navigate(R.id.fragmentPerfil);
                    return true;
                }

                return false;
            }
        });
    }
}