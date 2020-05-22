package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.fragments.GraficoCurvasFragment;
import com.example.pediatrapp.fragments.TablaCurvasFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CurvasGraficoActivity extends AppCompatActivity {
    private TextView nombreHijo;
    private Button backBTN;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curvas_grafico);

        nombreHijo = findViewById(R.id.nombreHijoCurvasGrafico);
        backBTN = findViewById(R.id.backcurvaGra);
        bottomNavigationView = findViewById(R.id.bottomNavCurvas);
        bottomNavigationView.setSelectedItemId(R.id.tabla);

        nombreHijo.setText(getIntent().getStringExtra("elnombre"));

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerCurvas, new TablaCurvasFragment()).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment fragment = null;

            switch (menuItem.getItemId()) {
                case R.id.tabla:
                    fragment = new TablaCurvasFragment();
                    break;
                case R.id.grafica:
                    fragment = new GraficoCurvasFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerCurvas, fragment).commit();
            return true;
        });
        backBTN.setOnClickListener(
                (v)->{
                    this.finish();
                }

        );
    }

    public TextView getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(TextView nombreHijo) {
        this.nombreHijo = nombreHijo;
    }
}
