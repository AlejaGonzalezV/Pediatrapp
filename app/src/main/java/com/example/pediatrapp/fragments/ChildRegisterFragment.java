package com.example.pediatrapp.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.dialog.DatePickerFragment;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChildRegisterFragment extends Fragment implements View.OnClickListener {

    private ArrayAdapter<String> doctorsAdapter;
    private OnDataSubmitted listener;
    private View view;
    private Button next, back;
    private EditText name, id, date;
    private Spinner doctor, gender;
    private ArrayList<String> ids;
    private ArrayList<String> names;
    private String fechaNac;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_child_register,container,false);
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


                    if(listener != null && nombre == false && ident == false && fecha == false && doc == false && gen == false){

                        listener.onData(this,"next", name.getText().toString(), id.getText().toString(), fechaNac, gender.getSelectedItem().toString(),ids.get(doctor.getSelectedItemPosition()-1));
                        Log.e("<<<<<<<", String.valueOf(doctor.getSelectedItemPosition()-1));

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

                    if(listener != null){

                        listener.onData(this, "back", null);

                    }

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

                //configureSpinner();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.date:
                showDatePickerDialog();
                break;
        }
    }

    public void showDatePickerDialog(){

        DatePickerFragment datePicker = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {

                fechaNac = day + "/" + (month+1) + "/" + year;
                date.setText(fechaNac);

            }
        });

        datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

    /*
    public void configureSpinner(){


        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, names);
        doctor.setAdapter(adapter);
        doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selected = doctor.getSelectedItem().toString();
                indice = doctor.getSelectedItemPosition();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

     */


}
