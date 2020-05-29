package com.example.pediatrapp.pagercontroller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HijosAdapter;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.view.ActivityMainPediatra;
import com.example.pediatrapp.view.HistoriaClinicaActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Hijos extends Fragment {

    private ListView listaHijos;
    private HijosAdapter adapter;
    private String id;
    private String identHijo, nameHijo;

    public Hijos(String id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hijos, container, false);

        listaHijos = view.findViewById(R.id.listViewHijos);
        adapter = new HijosAdapter(this);
        listaHijos.setAdapter(adapter);
        cargarHijos();

        return view;
    }

    public void recibo(int ident){

        Hijo hijo = (Hijo) listaHijos.getItemAtPosition(ident);
        identHijo = hijo.getId();
        nameHijo = hijo.getNombre();
        Intent intent = new Intent(getContext(), HistoriaClinicaActivity.class);
        intent.putExtra("idUser", id);
        intent.putExtra("idHijo", identHijo);
        intent.putExtra("nameHijo", nameHijo);
        intent.putExtra("nacimiento", hijo.getNacimiento());
        intent.putExtra("sexo", hijo.getSexo());

        startActivity(intent);
        Log.e("ESTE ES EL ID REAAAAAL", id);

        //Mandar al activity de hc

    }

    public void cargarHijos(){

        Query query = FirebaseDatabase.getInstance().getReference().child("Padres").child(id).child("hijos");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Hijo hijo = child.getValue(Hijo.class);
                    adapter.addHijos(hijo);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

