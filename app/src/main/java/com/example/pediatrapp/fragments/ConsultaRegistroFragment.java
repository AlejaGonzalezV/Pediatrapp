package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Diagnostico;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ConsultaRegistroFragment extends Fragment {


    private View view;
    private String idDiag;
    private String idHijo;
    private String ids;
    private TextView titulo, pediatra,fecha,diagnostico;
    private Button verFormulaBt, back;
    private boolean formula;

    private OnDataSubmitted listener;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_consulta_registro, container, false);
        ids = getArguments().getString("ids");
        String[] values = ids.split("/");
        idDiag = values[0];
        idHijo = values[1];

        titulo = view.findViewById(R.id.titulo);
        pediatra = view.findViewById(R.id.pediatra);
        fecha = view.findViewById(R.id.fecha);
        diagnostico = view.findViewById(R.id.diagnostico);
        verFormulaBt = view.findViewById(R.id.verFormulaBt);
        back = view.findViewById(R.id.back);

        llenarCampos();

        back.setOnClickListener(

                (v)->{

                    listener.onData(this, "back", null);

                }

        );

        verFormulaBt.setOnClickListener(

                (v)->{

                    if(formula){

                        Toast.makeText(getContext(), "No hay fórmula médica para mostrar", Toast.LENGTH_SHORT).show();

                    }else {

                        listener.onData(this, "next", null);

                    }



                }

        );


        return view;

    }

    public void llenarCampos(){

        Query query = FirebaseDatabase.getInstance().getReference().child("Historia_clinica").child(idHijo).child(idDiag);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Diagnostico diag = dataSnapshot.getValue(Diagnostico.class);
                titulo.setText(diag.getTitulo());
                pediatra.setText(diag.getNombre_pediatra());
                fecha.setText(diag.getFecha());
                diagnostico.setText(diag.getDiagnostico());
                formula = diag.getFormula_medica() == null;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
