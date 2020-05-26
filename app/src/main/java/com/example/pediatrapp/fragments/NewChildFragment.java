package com.example.pediatrapp.fragments;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.dialog.DatePickerFragment;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//fragment_new_child

public class NewChildFragment extends Fragment implements View.OnClickListener{

    private ArrayAdapter<String> doctorsAdapter;
    private OnDataSubmitted listener;
    private View view;
    private Button next, back;
    private EditText name, id, date;
    private Spinner doctor, gender;
    private ArrayList<String> ids;
    private ArrayList<String> names;
    private String fechaNac;
    private String edad;
    private Fragment padrePerfil;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_child,container,false);
        name = view.findViewById(R.id.name);
        id = view.findViewById(R.id.id);
        date = view.findViewById(R.id.date);
        doctor = view.findViewById(R.id.doctor);
        next = view.findViewById(R.id.next);
        back = view.findViewById(R.id.back);
        gender = view.findViewById(R.id.gender);
        ids = new ArrayList<String>();
        names = new ArrayList<String>();
        names.add("Pediatra asignado");

        date.setOnClickListener(this);


        configureSpinner();
        fillDoctors();


        next.setOnClickListener(

                (v) -> {


                    boolean nombre = TextUtils.isEmpty(name.getText().toString().trim());
                    boolean ident = TextUtils.isEmpty(id.getText().toString().trim());
                    boolean fecha = TextUtils.isEmpty(date.getText().toString().trim());
                    boolean doc = doctor.getSelectedItem().toString().equals("Pediatra asignado");
                    boolean gen = gender.getSelectedItem().toString().equals("Género");


                    if(nombre == false && ident == false && fecha == false && doc == false && gen == false){


                        String idPadre = FirebaseAuth.getInstance().getUid();
                        String idH = FirebaseDatabase.getInstance().getReference().child("Padres").child(idPadre).child("hijos").push().getKey();
                        Hijo hijo = new Hijo(idH, id.getText().toString(), fechaNac, gender.getSelectedItem().toString(), name.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("Padres").child(idPadre).child("hijos").child(idH).setValue(hijo);
                        FirebaseDatabase.getInstance().getReference().child("Pediatras").child(ids.get(doctor.getSelectedItemPosition()-1)).child("Padres_asignados").child(idPadre).setValue(idPadre);
                        Toast.makeText(getContext(), "El nuevo hijo se ha añadido con éxito", Toast.LENGTH_SHORT).show();;
                        padrePerfil = new ParentFragment_Perfil();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentContainer2, padrePerfil);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();


                    }else {

                        if(nombre){
                            name.setError("Debe ingresar este campo para continuar");
                        }
                        if(ident){
                            id.setError("Debe ingresar este campo para continuar");
                        }
                        if(fecha){
                            date.setError("Debe ingresar este campo para continuar");
                        }
                        if(doc){
                            Toast.makeText(getContext(), "Debe seleccionar un doctor asignado", Toast.LENGTH_LONG).show();
                        }
                        if(gen){
                            Toast.makeText(getContext(), "Debe seleccionar un género", Toast.LENGTH_LONG).show();
                        }



                    }


                }

        );

        back.setOnClickListener(

                (v) -> {

                    padrePerfil = new ParentFragment_Perfil();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer2, padrePerfil);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }

        );

        return view;
    }


    public void fillDoctors(){

        Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Pediatra pediatra = child.getValue(Pediatra.class);
                    names.add(pediatra.getNombre());
                    ids.add(child.getKey());
                }

                doctorsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void configureSpinner(){

        doctorsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, names);
        doctor.setAdapter(doctorsAdapter);

    }

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date:
                showDatePickerDialog();
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("NewApi")
    public void showDatePickerDialog(){

        DatePickerFragment datePicker = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                if((month+1) >= 10){

                    fechaNac = day + "/" + (month+1) + "/" + year;
                    date.setText(fechaNac);

                } else {

                    fechaNac = day + "/" + "0" + (month+1) + "/" + year;
                    date.setText(fechaNac);

                }

                Log.e("<<<<<<<<<<<", fechaNac);
                calcularEdad();

            }
        });

        datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calcularEdad(){

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaN = LocalDate.parse(fechaNac, fmt);
        LocalDate hoy = LocalDate.now();

        Period periodo = Period.between(fechaN, hoy);
        edad = String.valueOf(periodo.getYears());

    }
}
