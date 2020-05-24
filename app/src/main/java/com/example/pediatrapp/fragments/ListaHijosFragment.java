package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HijosAdapter;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Hijo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaHijosFragment extends Fragment {

    private View view;
    private ListView lista;
    private Button back;
    private HijosAdapter adapter;
    private String ident;
    private boolean selec;
    private ArrayList<Hijo> hijos;

    private OnDataSubmitted listener;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lista_hijos, container, false);
        back = view.findViewById(R.id.back);
        lista = view.findViewById(R.id.ListaHijos);
        adapter = new HijosAdapter();
        lista.setAdapter(adapter);
        hijos = new ArrayList<>();
        cargarHijos();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Hijo hijo = (Hijo) lista.getOnItemClickListener();
                ident = hijo.getId();
                enviar();
            }
        });

        back.setOnClickListener(


                (v)-> {

                    listener.onData(this, "back", null);


                }

        );


        return view;
    }

    public void enviar(){

        listener.onData(this, "next", ident);

    }

    public void cargarHijosLista(){

        Log.e("OTRO SIZE", hijos.size()+"");

        for(int i=0; i<hijos.size(); i++){

            adapter.addHijos(hijos.get(i));

        }



    }

    public void cargarHijos(){

        String uid = FirebaseAuth.getInstance().getUid();
        Query query = FirebaseDatabase.getInstance().getReference().child("Padres").child(uid).child("hijos");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Hijo hijo = child.getValue(Hijo.class);
                    adapter.addHijos(hijo);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}