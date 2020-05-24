package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.view.HistoriaClinicaActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HistoriaPadreFragment extends Fragment {

    private View view;
    private String hijoId;
    private OnDataSubmitted listener;
    private TextView nombre;
    private Button back;
    private ListView lista;
    private Hijo hijo;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_historia_padre, container, false);
        HistoriaClinicaActivity activity = (HistoriaClinicaActivity) getActivity();
        hijoId = activity.getHijoId();
        nombre = view.findViewById(R.id.nombre);
        lista = view.findViewById(R.id.lista);
        back = view.findViewById(R.id.back);

        back.setOnClickListener(


                (v)-> {

                    listener.onData(this, "back", null);


                }

        );

        return view;
    }

    public void obtenerHijo(){

        String uid = FirebaseAuth.getInstance().getUid();
        Query query = FirebaseDatabase.getInstance().getReference().child("Padres").child(uid).child("hijos").child(hijoId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                hijo = dataSnapshot.getValue(Hijo.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
