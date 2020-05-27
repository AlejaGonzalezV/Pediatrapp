package com.example.pediatrapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.OnDataSubmitted;


public class HistorialesPediatraFragment extends Fragment {

    private Button vacunasGeneralBTN;
    private Button curvasGeneralBTN;
    private Button historiaClinica;
    private Button back;
    private View view;
    private OnDataSubmitted listener;

    public void setListener(OnDataSubmitted listener){

        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_historiales_pediatra, container, false);

        vacunasGeneralBTN = view.findViewById(R.id.vacunasGeneralBTN);
        curvasGeneralBTN = view.findViewById(R.id.curvasGeneralBTN);
        historiaClinica = view.findViewById(R.id.historiaClinica);
        back = view.findViewById(R.id.back);

        historiaClinica.setOnClickListener(

                (v) ->{

                    listener.onData(this,"historia", null);

                }

        );

        vacunasGeneralBTN.setOnClickListener(

                (v)->{

                    listener.onData(this, "vacunas", null);

                }

        );

        curvasGeneralBTN.setOnClickListener(

                (v) -> {

                    listener.onData(this, "curvas", null);

                }

        );

        back.setOnClickListener(

                (v)->{

                    listener.onData(this,"back", null);

                }

        );




        return view;
    }

}
