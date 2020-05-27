package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HistoriaAdapter;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Diagnostico;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.view.HistoriaClinicaActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private HistoriaAdapter adapter;
    private String idDiag;

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
        adapter = new HistoriaAdapter(this);
        lista.setAdapter(adapter);
        obtenerHijo();
        obtenerRegistros();

        back.setOnClickListener(


                (v)-> {

                    listener.onData(this, "back", null);

                }

        );

        return view;
    }

    public void recibo(int pos){

        Diagnostico diagn = (Diagnostico) lista.getOnItemClickListener();
        idDiag = diagn.getId();
        enviar();

    }

    public void enviar(){

        listener.onData(this, "next", idDiag);

    }

    public void obtenerRegistros(){

        Query query = FirebaseDatabase.getInstance().getReference().child("Historia_clinica").child(hijoId);
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

    public void obtenerHijo(){

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Query query = FirebaseDatabase.getInstance().getReference().child("Padres").child(uid).child("hijos");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    hijo = child.getValue(Hijo.class);
                    nombre.setText(hijo.getNombre());

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
