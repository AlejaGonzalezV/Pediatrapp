package com.example.pediatrapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HistoriaAdapter;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Diagnostico;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.view.ActivityMainPediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class HistoriaPediatraFragment extends Fragment {

    private View view;
    private Button back,addRegistro;
    private ListView lista;
    private TextView nombre;
    private HistoriaAdapter adapter;
    private OnDataSubmitted listener;
    private String idPadre;
    private String idHijo;
    private String idDiag;
    private String ids;

    /*
    public HistoriaPediatraFragment(String idPadre, String idHijo) {
        this.idPadre = idPadre;
        this.idHijo = idHijo;

    }
     */

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_historia_pediatra, container, false);
        back = view.findViewById(R.id.back);
        addRegistro = view.findViewById(R.id.addRegistro);
        lista = view.findViewById(R.id.lista);
        nombre = view.findViewById(R.id.nombre);
        adapter = new HistoriaAdapter(this);
        lista.setAdapter(adapter);

        ids = getArguments().getString("ids");
        String[] val = ids.split("/");
        idPadre = val[0];
        idHijo = val[1];

        Query query = FirebaseDatabase.getInstance().getReference().child("Padres").child(idPadre).child("hijos").child(idHijo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
                                                 @Override
                                                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                     Hijo hijo = dataSnapshot.getValue(Hijo.class);
                                                     nombre.setText(hijo.getNombre());

                                                 }

                                                 @Override
                                                 public void onCancelled(@NonNull DatabaseError databaseError) {

                                                 }
                                             });

                cargarRegistros();

        back.setOnClickListener(

                (v)->{

                    listener.onData(this, "back", null);

                }

        );

        addRegistro.setOnClickListener(

                (v)->{

                    listener.onData(this, "add", null);

                }

        );


        return view;

    }

    public void recibo(int pos){

        //Diagnostico diagn = (Diagnostico) lista.getOnItemClickListener();
        Diagnostico diagn = (Diagnostico) lista.getItemAtPosition(pos);
        idDiag = diagn.getId();
        enviar();

    }

    private void enviar() {

        listener.onData(this, "next", idDiag);

    }

    public void cargarRegistros(){

        Query query = FirebaseDatabase.getInstance().getReference().child("Historia_clinica").child(idHijo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Diagnostico diag = child.getValue(Diagnostico.class);
                    adapter.addDiagnostico(diag);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void showRegistro(String id){

        listener.onData(this, "ver", id);

    }

}
