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
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.ListaVacunasAdpater;
import com.example.pediatrapp.model.Vacuna;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaVacunasActivity extends AppCompatActivity implements Serializable {

    private Button agregarVaunaBTN;
    private TextView nombreHijo;
    private ListView listView;
    private ArrayList<Vacuna> listaVacunas;
    private ListaVacunasAdpater adapter;
    private Button backBTN;
    public static final int request_code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vacunas);

       agregarVaunaBTN = findViewById(R.id.agregarVacunaLista);
       nombreHijo = findViewById(R.id.nombreHijoVaList);
       listView = findViewById(R.id.listaVacunasAgregadas);
       backBTN = findViewById(R.id.backVacunasLista);
       listaVacunas = new ArrayList<>() ;

       listaVacunas.add(new Vacuna("res","12","Asmet","Jair","AlgoSerio",null));

       adapter = new ListaVacunasAdpater( listaVacunas,this);
       listView.setAdapter(adapter);


       agregarVaunaBTN.setOnClickListener(

               (v)->{

                  // AddVacunaActivity añadir = new AddVacunaActivity(this);

                   Intent intent = new Intent(this, AddVacunaActivity.class);
                   startActivity(intent);

               }

       );
        backBTN.setOnClickListener(
                (v)->{
                    this.finish();
                }

        );


    }

    //Agrega una vacuna a la lista
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if ((requestCode == request_code) && (resultCode == RESULT_OK)){

            agregarVacunaALista(data);

        }
    }

    public void agregarVacunaALista(Intent data){

        Vacuna vacuna =  (Vacuna) data.getExtras().getSerializable("nuevaVacuna");

        if(vacuna != null) {
            listaVacunas.add(vacuna);
            Toast.makeText(this,"Se añadió: "+ vacuna.getNombre_vacuna(), Toast.LENGTH_SHORT).show();
        }
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
