package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HijosVacunasAdapter;
import com.example.pediatrapp.model.Hijo;

import java.util.ArrayList;
import java.util.List;

public class VacunasActivity extends AppCompatActivity {

    private String nombreHijo;
    private ListView ListaHijosVacuna;
    private HijosVacunasAdapter adapter;
 //   private ArrayList<Hijo> listaHijos;
    private Button backBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas);

        backBTN = findViewById(R.id.backVacunas);
        nombreHijo = "";

        ListaHijosVacuna = findViewById(R.id.ListaHijosVacuna);
//        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        listaHijos = new ArrayList<>();
        adapter = new HijosVacunasAdapter();
        ListaHijosVacuna.setAdapter(adapter);

        //Hijo de prueba
        adapter.addHijo(new Hijo("", "", "2001", "Masculino", "Melqui", "3"));



        backBTN.setOnClickListener(
                (v)->{
                    this.finish();
                }

        );


    }

    //Método para buscar Hijo en la lsita de hijos Agregados

    public void buscarHijoVacunas(String nombreHijo){


    }

    public String getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(String nombreHijo) {
        this.nombreHijo = nombreHijo;
    }
}
