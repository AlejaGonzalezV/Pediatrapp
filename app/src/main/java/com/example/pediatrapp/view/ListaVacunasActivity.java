package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.ListaVacunasAdpater;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.model.Vacuna;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaVacunasActivity extends AppCompatActivity implements Serializable {

    public static final int AGREGAR = 11;
    public static final int ELIMINAR = 12;

    private Button agregarVaunaBTN,  backBTN;
    private TextView nombreHijo;

    private ListView listaVacunasAgregadas;
    private List<Vacuna> listaVacunas;

    private ListaVacunasAdpater adapter;
    private String idhijo;

    public static final int request_code = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vacunas);

        agregarVaunaBTN = findViewById(R.id.agregarVacunaLista);
        nombreHijo = findViewById(R.id.nombreHijoVaList);
        listaVacunasAgregadas = findViewById(R.id.listaVacunasAgregadas);
        backBTN = findViewById(R.id.backVacunasLista);
        listaVacunas = new ArrayList<>();

        //Recibe nombre del Hijo
        nombreHijo.setText(getIntent().getStringExtra("elnombre"));
        idhijo = getIntent().getStringExtra("idhijo");

        listaVacunas.add(new Vacuna("id", "res","12","Asmet Salud ","PeditraA","Covid-19","24/05/2020", "", ""));

        adapter = new ListaVacunasAdpater(this);
        listaVacunasAgregadas.setAdapter(adapter);

        // Con este método se cargan todas las vacunas de la base de datos
        cargarVacunas();

       agregarVaunaBTN.setOnClickListener(

               (v)->{

                   Intent i= new Intent(this, AddVacunaActivity.class);
                   startActivityForResult(i, 11);

               }

       );
        backBTN.setOnClickListener(
                (v)->{
                    this.finish();
                }
        );

    }

    //Aquí se recibe la nueva vacuna que va a ser agragada a la lista
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AGREGAR && resultCode == RESULT_OK) {

            if (data != null) {

                agregarVacunaALista(data);
            }
        }else if(requestCode == ELIMINAR && resultCode == RESULT_OK){

            eliminarVacuna(data);
        }
    }

    private void eliminarVacuna(Intent data) {

        Vacuna vacuna  =   (Vacuna) data.getExtras().getSerializable("marcador");

        if(vacuna != null) {

            adapter.remover(vacuna);
            FirebaseDatabase.getInstance().getReference().child("Vacunas").child(idhijo).child("Vacunas").child(vacuna.getId()).removeValue();
            Toast.makeText(this,"Se elimino: "+ vacuna.getNombre_vacuna(), Toast.LENGTH_SHORT).show();
        }
    }

    // Método para Cargar Vacunas de base de datos

    public void cargarVacunas(){

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Vacunas").child(idhijo).child("Vacunas");

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        Vacuna vac = snapshot.getValue(Vacuna.class);
                        if (vac != null) {

                            adapter.addVacuna(vac);

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }

    //Este método agraga una nueva vacuna a la lista.

    public void agregarVacunaALista(Intent data){

        Vacuna vacuna  =   (Vacuna) data.getExtras().getSerializable("marcador");

        if(vacuna != null) {

            String idc = FirebaseDatabase.getInstance().getReference().child("Vacunas").child(idhijo).child("Vacunas").push().getKey();
            vacuna.setId(idc);
            adapter.addVacuna(vacuna);
            FirebaseDatabase.getInstance().getReference().child("Vacunas").child(idhijo).child("Vacunas").child(idc).setValue(vacuna);

            Toast.makeText(this,"Se añadióZZZZZZ: "+ vacuna.getNombre_vacuna(), Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(this," Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void setListaVacunas(ArrayList<Vacuna> listaVacunas) {
        this.listaVacunas = listaVacunas;
    }

    public void setAdapter(ListaVacunasAdpater adapter) {
        this.adapter = adapter;
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

    public String getIdhijo() {
        return idhijo;
    }

    public void setIdhijo(String idhijo) {
        this.idhijo = idhijo;
    }
}
