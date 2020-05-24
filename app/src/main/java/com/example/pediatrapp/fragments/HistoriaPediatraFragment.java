package com.example.pediatrapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HistoriaAdapter;
import com.example.pediatrapp.adapter.OnDataSubmitted;
import com.example.pediatrapp.model.Diagnostico;
import com.example.pediatrapp.view.ActivityMainPediatra;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class HistoriaPediatraFragment extends Fragment {

    private View view;
    private Button back,addRegistro;
    private ListView lista;
    private HistoriaAdapter adapter;
    private OnDataSubmitted listener;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_historia_pediatra, container, false);
        back = view.findViewById(R.id.back);
        addRegistro = view.findViewById(R.id.addRegistro);
        lista = view.findViewById(R.id.lista);
        adapter = new HistoriaAdapter(this);
        lista.setAdapter(adapter);

        return view;

    }

    public void showRegistro(String id){

        listener.onData(this, "ver", id);

    }

}
