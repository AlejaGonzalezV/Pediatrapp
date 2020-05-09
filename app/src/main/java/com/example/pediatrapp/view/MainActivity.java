package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pediatrapp.R;
import com.example.pediatrapp.fragments.ParentFragment_ChatList;
import com.example.pediatrapp.fragments.ParentFragment_Historial;
import com.example.pediatrapp.fragments.ParentFragment_PediatraList;
import com.example.pediatrapp.fragments.ParentFragment_Perfil;
import com.example.pediatrapp.model.Padre;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Padre padre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNav2);
        bottomNavigationView.setSelectedItemId(R.id.chatPa);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer2, new ParentFragment_ChatList()).commit();
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.pediatras:
                        fragment = new ParentFragment_PediatraList();
                        break;
                    case R.id.historial:
                        fragment = new ParentFragment_Historial();
                        break;
                    case R.id.chatPa:
                        fragment = new ParentFragment_ChatList();
                        break;
                    case R.id.perfilPa:
                        fragment = new ParentFragment_Perfil();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer2, fragment).commit();
                return true;
            }
        });

    }
}
