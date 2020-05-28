package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.ItemTablaCurvasAdpater;
import com.example.pediatrapp.model.DatosCurva;
import com.example.pediatrapp.model.Vacuna;
import com.example.pediatrapp.view.AddCurvaActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TablaCurvasFragment extends Fragment {

    private ListView listaCurvasAgregadas;
    private ItemTablaCurvasAdpater adapter;
    private String idhijo;


    public TablaCurvasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabla_curvas, container, false);

        listaCurvasAgregadas = view.findViewById(R.id.listaCurvasAgregadas);
        adapter = new ItemTablaCurvasAdpater();
        listaCurvasAgregadas.setAdapter(adapter);

        idhijo = getArguments().getString("idhijo");
        adapter.setIdhijo(idhijo);


        cargarDatosCurvas();

        // Inflate the layout for this fragment
        return view;
    }

    //Este método cargará todas las curvas que estén en base de datos

    private void cargarDatosCurvas() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Curvas_crecimiento").child(idhijo).child("Curvas_crecimiento");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.removeAll();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    DatosCurva dato = snapshot.getValue(DatosCurva.class);
                    if (dato != null) {

                        adapter.addDatoCurva(dato);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }




}
