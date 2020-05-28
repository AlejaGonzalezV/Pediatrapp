package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HijosVacunasAdapter;
import com.example.pediatrapp.model.Hijo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        adapter = new HijosVacunasAdapter();
        ListaHijosVacuna.setAdapter(adapter);
        buscarHijoVacunas();


        backBTN.setOnClickListener(
                (v)->{
                    this.finish();
                }

        );


    }

    //Método para buscar Hijo en la lsita de hijos Agregados

    public void buscarHijoVacunas(){

            String uid = FirebaseAuth.getInstance().getUid();
            Query query = FirebaseDatabase.getInstance().getReference().child("Padres").child(uid).child("hijos");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot child: dataSnapshot.getChildren()){

                        Hijo hijo = child.getValue(Hijo.class);
                        adapter.addHijo(hijo);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }

    public String getNombreHijo() {
        return nombreHijo;
    }

    public void setNombreHijo(String nombreHijo) {
        this.nombreHijo = nombreHijo;
    }
}
