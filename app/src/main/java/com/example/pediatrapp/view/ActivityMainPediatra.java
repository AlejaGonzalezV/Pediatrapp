package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pediatrapp.R;
import com.example.pediatrapp.fragments.PediatraFragment_ChatList;
import com.example.pediatrapp.fragments.PediatraFragment_PadreLista;
import com.example.pediatrapp.fragments.PediatraFragment_Perfil;
import com.example.pediatrapp.model.Pediatra;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityMainPediatra extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Pediatra pediatra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pediatra);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.chat);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new PediatraFragment_ChatList(pediatra)).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()){
                    case R.id.padres:
                        fragment = new PediatraFragment_PadreLista(pediatra);
                    break;
                    case R.id.chat:
                        fragment = new PediatraFragment_ChatList(pediatra);
                        break;
                    case R.id.perfil:
                        fragment = new PediatraFragment_Perfil();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });

    }
}
