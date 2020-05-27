package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.fragments.GraficoCurvasFragment;
import com.example.pediatrapp.fragments.TablaCurvasFragment;
import com.example.pediatrapp.model.DatosCurva;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CurvasGraficoActivity extends AppCompatActivity {
    private TextView nombreHijo;
    private String idhijo;
    private String nacimiento;
    private Button backBTN, agregarCurvaBtn;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curvas_grafico);

        nombreHijo = findViewById(R.id.nombreHijoCurvasGrafico);
        backBTN = findViewById(R.id.backcurvaGra);
        bottomNavigationView = findViewById(R.id.bottomNavCurvas);
        bottomNavigationView.setSelectedItemId(R.id.tabla);
        agregarCurvaBtn = findViewById(R.id.agregarCurva);


        idhijo = getIntent().getStringExtra("idhijo");
        nombreHijo.setText(getIntent().getStringExtra("elnombre"));
        nacimiento = getIntent().getStringExtra("nacimiento");

        if(savedInstanceState == null){
            Fragment fragment = new TablaCurvasFragment();
            Bundle args = new Bundle();
            args.putString("idhijo",idhijo);
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerCurvas, fragment).commit();
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            Fragment fragment = null;

            switch (menuItem.getItemId()) {
                case R.id.tabla:
                    fragment = new TablaCurvasFragment();
                    Bundle args = new Bundle();
                    args.putString("idhijo",idhijo);
                    fragment.setArguments(args);
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
        agregarCurvaBtn.setOnClickListener(

                (v)->{

                    Intent intent = new Intent(this, AddCurvaActivity.class);
                    intent.putExtra("nacimiento", nacimiento);
                    startActivityForResult(intent, 11);

                }

        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 11 && resultCode == RESULT_OK) {

            if (data != null) {

                agregarCurvaLista(data);
            }
        }

    }

    private void agregarCurvaLista(Intent data) {

        DatosCurva curva  =   (DatosCurva) data.getExtras().getSerializable("laCurva");

        if(curva != null) {

           String id =  FirebaseDatabase.getInstance().getReference().child("Curvas_crecimiento").child(idhijo).child("Curvas_crecimiento").push().getKey();
            curva.setId(id);
            FirebaseDatabase.getInstance().getReference().child("Curvas_crecimiento").child(idhijo).child("Curvas_crecimiento").child(id).setValue(curva);

            Fragment fragment = new TablaCurvasFragment();
            Bundle args = new Bundle();
            args.putString("idhijo",idhijo);
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerCurvas, fragment).commit();

            Toast.makeText(this,"Se añadió Curva: "+ curva.getPeso(), Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this," Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }

    }

    public TextView getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(TextView nombreHijo) {
        this.nombreHijo = nombreHijo;
    }
}
