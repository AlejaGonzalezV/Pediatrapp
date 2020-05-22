package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.ItemTablaCurvasAdpater;
import com.example.pediatrapp.model.DatosCurva;
import com.example.pediatrapp.view.AddCurvaActivity;

import java.util.ArrayList;
import java.util.List;

public class TablaCurvasFragment extends Fragment {

    private RecyclerView recyclerView;
    private ItemTablaCurvasAdpater adapter;
    private ArrayList<DatosCurva> listaCurvas;
    private DatosCurva nuevaCurva;
  //  private Button agregarCurvaBtn;

    public TablaCurvasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabla_curvas, container, false);
        View view2 = inflater.inflate(R.layout.activity_curvas_grafico, container, false);
        recyclerView = view.findViewById(R.id.listaCurvasAgregadas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false));

       nuevaCurva = (DatosCurva) this.getActivity().getIntent().getExtras().getSerializable("nuevaCurva");
       // agregarCurvaBtn = view2.findViewById(R.id.agregarCurva);
        listaCurvas = new ArrayList<>();

        listaCurvas.add(new DatosCurva("jejej", 12, 13, 14));

        if(nuevaCurva != null){

            listaCurvas.add(nuevaCurva);
        }

        cargarDatosCurvas();
        actualizarTablaCurvas();

        // Inflate the layout for this fragment
        return view;
    }

    //Este método cargará todas las curvas que estén en base de datos

    private void cargarDatosCurvas() {


    }



    //Este método actualizará las curvas que se agreguen

    private void actualizarTablaCurvas() {

        if(listaCurvas.size() != 0) {

            adapter = new ItemTablaCurvasAdpater(listaCurvas, this.getContext());
            recyclerView.setAdapter(adapter);
        }

    }
}
