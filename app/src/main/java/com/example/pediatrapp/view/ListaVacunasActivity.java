package com.example.pediatrapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.ListaVacunasAdpater;
import com.example.pediatrapp.model.Vacuna;

import java.util.ArrayList;
import java.util.List;

public class ListaVacunasActivity extends AppCompatActivity {

    private Button agregarVaunaBTN;
    private TextView nombreHijo;
    private ListView listView;
    private ArrayList<Vacuna> listaVacunas;
    private ListaVacunasAdpater adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vacunas);

       agregarVaunaBTN = findViewById(R.id.agregarVacunaLista);
       nombreHijo = findViewById(R.id.nombreHijoVaList);
       listView = findViewById(R.id.listaVacunasAgregadas);
       listaVacunas = new ArrayList<>() ;

       adapter = new ListaVacunasAdpater( listaVacunas,this);
       listView.setAdapter(adapter);


       agregarVaunaBTN.setOnClickListener(

               (v)->{

                   Intent intent = new Intent(this, AddVacunaActivity.class);
                   startActivity(intent);

               }

       );


    }

    //Agrega una vacuna a la lista

    public void AgregarVacunaALista(Vacuna vacuna){

        listaVacunas.add(vacuna);
    }

    public Button getAgregarVaunaBTN() {
        return agregarVaunaBTN;
    }

    public void setAgregarVaunaBTN(Button agregarVaunaBTN) {
        this.agregarVaunaBTN = agregarVaunaBTN;
    }


    public TextView getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(TextView nombreHijo) {
        this.nombreHijo = nombreHijo;
    }
}
