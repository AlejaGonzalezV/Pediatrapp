package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.HijosAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class ListaHijosFragment extends Fragment {


    private View view;
    private ListView lista;
    private Button back;
    private HijosAdapter adapter;

    public ListaHijosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lista_hijos, container, false);
        lista = view.findViewById(R.id.lista);
        back = view.findViewById(R.id.back);
        adapter = new HijosAdapter();
        lista.setAdapter(adapter);

        return view;
    }

    public void cargarHijos(){

        String uid = FirebaseAuth.getInstance().getUid();

    }

}
