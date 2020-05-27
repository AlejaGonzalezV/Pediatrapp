package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Diagnostico;
import com.example.pediatrapp.model.FormulaMedica;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ConsultaFormulaFragment extends Fragment {


    private View view;
    private TextView fecha,posologia;
    private Button back;
    private OnDataSubmitted listener;
    private String idHijo, idDiag, ids;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_consulta_formula, container, false);
        fecha = view.findViewById(R.id.fecha);
        posologia = view.findViewById(R.id.posologia);
        back = view.findViewById(R.id.back);

        ids = getArguments().getString("ids");
        String[] val = ids.split("/");
        idDiag = val[0];
        idHijo = val[1];

        cargarDatos();

        back.setOnClickListener(

                (v)->{

                    listener.onData(this, "back", null);

                }

        );

        return view;
    }

    public void volver(){

        listener.onData(this, "back", null);
        Toast.makeText(getContext(), "No hay una fórmula médica", Toast.LENGTH_SHORT).show();

    }

    public void cargarDatos(){

        Query query = FirebaseDatabase.getInstance().getReference().child("Historia_clinica").child(idHijo).child(idDiag).child("formula_medica");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                FormulaMedica formula = dataSnapshot.getValue(FormulaMedica.class);

                if(formula != null){

                    fecha.setText(formula.getFecha());
                    posologia.setText(formula.getPosologia());

                }else {

                    volver();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
