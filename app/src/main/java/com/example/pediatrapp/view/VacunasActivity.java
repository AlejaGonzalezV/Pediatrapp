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
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HijosVacunasAdapter;
import com.example.pediatrapp.model.Hijo;

import java.util.ArrayList;
import java.util.List;

public class VacunasActivity extends AppCompatActivity {

    private ImageButton buscarBtn;
    private EditText buscarET;
    private String nombreHijo;
    private RecyclerView recycler;
    private HijosVacunasAdapter adapter;
    private ArrayList<Hijo> listaHijos;
    private Button backBTN, verHijoBTn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacunas);

        buscarBtn = findViewById(R.id.searchHijoVacunaBT);
         buscarET = findViewById(R.id.searchHijoVacunaET);
         nombreHijo = "";
         backBTN = findViewById(R.id.backVacunas);
         verHijoBTn = findViewById(R.id.verVa);
        recycler = findViewById(R.id.recyclerListaHijosVacuna);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        listaHijos = new ArrayList<>();
        adapter = new HijosVacunasAdapter(this,listaHijos );
        recycler.setAdapter(adapter);

        //Hijo de prueba
        listaHijos.add(new Hijo("", "", "String nacimiento", "Masculino", "Melqui"));



         buscarBtn.setOnClickListener(
                 (v)->{


                         Toast.makeText(this, "Debe ingresar un nombre", Toast.LENGTH_LONG).show();

                 }

         );


        backBTN.setOnClickListener(
                (v)->{
                    this.finish();
                }

        );

/*
        adapter.setOnclickListener(


                (v)->{


                            String nombreHijo = listaHijos.get(recycler.getChildAdapterPosition(v)).getNombre();


                            Toast.makeText(this, nombreHijo, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(VacunasActivity.this, ListaVacunasActivity.class);
                            intent.putExtra("elnombre", nombreHijo);
                            VacunasActivity.this.startActivity(intent);

    }


        );*/


    }

    //Método para buscar Hijo en la lsita de hijos Agregados

    public void buscarHijoVacunas(String nombreHijo){


    }


    public ImageButton getBuscarBtn() {
        return buscarBtn;
    }

    public void setBuscarBtn(ImageButton buscarBtn) {
        this.buscarBtn = buscarBtn;
    }

    public EditText getBuscarET() {
        return buscarET;
    }

    public void setBuscarET(EditText buscarET) {
        this.buscarET = buscarET;
    }

    public String getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(String nombreHijo) {
        this.nombreHijo = nombreHijo;
    }
}
