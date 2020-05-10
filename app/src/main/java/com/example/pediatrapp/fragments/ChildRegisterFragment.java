package com.example.pediatrapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Pediatra;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChildRegisterFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnDataSubmitted listener;
    private View view;
    private Button next, back;
    private EditText name, id, date;
    private Spinner doctor, gender;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> ids;
    private ArrayList<String> names;

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
        next = view.findViewById(R.id.consultar_btn);
        back = view.findViewById(R.id.back);
        gender = view.findViewById(R.id.gender);
        ids = new ArrayList<String>();
        names = new ArrayList<String>();

        fillDoctors();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, names);
        doctor.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doctor.setOnItemSelectedListener(this);


        next.setOnClickListener(

                (v) -> {

                    boolean correcto = name.getText().toString()!="" && id.getText().toString()!="" && date.getText().toString()!="" && doctor.getSelectedItem().toString()!="" && gender.getSelectedItem().toString()!="";
                    if(listener != null && correcto){

                        listener.onData(this,"next", name.getText().toString(), id.getText().toString(), date.getText().toString(), doctor.getSelectedItem().toString(), gender.getSelectedItem().toString());

                    }else {

                        Toast.makeText(getContext(), "Debe completar todos los campos para continuar", Toast.LENGTH_LONG).show();

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
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Pediatra pediatra = child.getValue(Pediatra.class);
                    names.add(pediatra.getNombre());
                    ids.add(child.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Log.e("SELECTED" ,position +"");

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
