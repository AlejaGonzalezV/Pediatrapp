package com.example.pediatrapp.fragments;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.dialog.DatePickerFragment;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class DiagnosticoFragment extends Fragment implements View.OnClickListener{

    private View view;
    private String idHijo, fechaS, nombrePed, firma;
    private OnDataSubmitted listener;
    private EditText titulo, fecha, diagnostico;
    private Button addFormulaBt, addRegistroBt, back;
    private Boolean formula;


    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_diagnostico, container, false);
         idHijo = getArguments().getString("ids");

         titulo = view.findViewById(R.id.titulo);
         fecha = view.findViewById(R.id.fecha);
         diagnostico = view.findViewById(R.id.diagnostico);
         addFormulaBt = view.findViewById(R.id.addFormulaBt);
         addRegistroBt = view.findViewById(R.id.addRegistroBt);
         back = view.findViewById(R.id.back);

         fecha.setOnClickListener(this);
         findNombre();

         addFormulaBt.setOnClickListener(

                 (v)->{

                     listener.onData(this, "formula", null);

                 }

         );

         addRegistroBt.setOnClickListener(

                 (v)->{

                     boolean tit = TextUtils.isEmpty(titulo.getText().toString().trim());
                     boolean fec = TextUtils.isEmpty(fecha.getText().toString().trim());
                     boolean diag = TextUtils.isEmpty(diagnostico.getText().toString().trim());

                     if(tit == false && fec == false && diag == false){

                         listener.onData(this, "next", titulo.getText().toString(), fecha.getText().toString(), diagnostico.getText().toString(), nombrePed, FirebaseAuth.getInstance().getCurrentUser().getUid(),firma);

                     }else {

                         if(tit){

                             titulo.setError("Debe ingresar este dato para continuar");

                         }
                         if(fec){

                             fecha.setError("Debe ingresar este dato para continuar");

                         }
                         if(diag){

                             diagnostico.setError("Debe ingresar este dato para continuar");

                         }


                     }



                 }

         );




        return view;
    }

    public void findNombre(){

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Pediatra ped = dataSnapshot.getValue(Pediatra.class);
                nombrePed = ped.getNombre();
                firma = ped.getFirma();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fecha:
                showDatePickerDialog();
                break;
        }

    }

    public void showDatePickerDialog(){
        DatePickerFragment datePicker = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                if((month+1) >= 10 && (day>=10)){

                    fechaS = day + "/" + (month+1) + "/" + year;
                    fecha.setText(fechaS);

                } else if((month+1) >= 10 && (day<10)) {

                    fechaS = "0" + day + "/" + (month+1) + "/" + year;
                    fecha.setText(fechaS);

                } else if((month+1) < 10 && (day>=10)){

                    fechaS = day + "/" + "0" + (month+1) + "/" + year;
                    fecha.setText(fechaS);

                }else if((month+1) < 10 && (day<10)){

                    fechaS = "0" + day + "/" + "0" + (month+1) + "/" + year;
                    fecha.setText(fechaS);

                }


                Log.e("<<<<<<<<<<<", fechaS);


            }
        });

        datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");


    }
}
