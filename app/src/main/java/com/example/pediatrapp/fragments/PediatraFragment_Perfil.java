package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.view.ActivityMainPediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PediatraFragment_Perfil extends Fragment {

    private ImageView pediatra_foto;
    private TextView nombre_pediatra;
    private TextView cedula_pediatra;
    private TextView email_pediatra;
    private TextView numeroUnico_pediatra;
    private Button cerrarSesion;

    public PediatraFragment_Perfil() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pediatrafragment_perfil, container, false);

        pediatra_foto = view.findViewById(R.id.pediatra_foto);
        nombre_pediatra = view.findViewById(R.id.nombre_pediatra);
        cedula_pediatra = view.findViewById(R.id.cedula_pediatraCV);
        email_pediatra = view.findViewById(R.id.email_pediatra);
        numeroUnico_pediatra = view.findViewById(R.id.numeroID);
        cerrarSesion = view.findViewById(R.id.next);



        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = FirebaseDatabase.getInstance().getReference().child("Pediatras");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    if(child.getKey().equals(id)){

                        Pediatra pediatra = child.getValue(Pediatra.class);
                        cedula_pediatra.setText(pediatra.getCedula());
                        email_pediatra.setText(pediatra.getCorreo());
                        numeroUnico_pediatra.setText(pediatra.getIdV());
                        nombre_pediatra.setText(pediatra.getNombre());

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;



    }


}
