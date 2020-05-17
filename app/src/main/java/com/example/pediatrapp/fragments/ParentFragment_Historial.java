package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pediatrapp.R;
import com.example.pediatrapp.view.CurvasCrecimientoActivity;
import com.example.pediatrapp.view.VacunasActivity;

public class ParentFragment_Historial extends Fragment {

    private Button vacunasGeneralBTN;
    private Button curvasGeneralBTN;
    private View view;

    public ParentFragment_Historial() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.parentfragment_historial, container, false);

        //Estas lineas dan error
        //vacunasGeneralBTN = view.findViewById(R.id.vacunasGeneralBTN);
        //curvasGeneralBTN = view.findViewById(R.id.curvasGeneralBTN);


        
        
        vacunasGeneralBTN.setOnClickListener(

                (v)->{

                    Intent intent = new Intent(view.getContext(), VacunasActivity.class);
                    startActivity(intent);
                }

        );

        curvasGeneralBTN.setOnClickListener(
                (v)->{

                    Intent intent = new Intent(view.getContext(), CurvasCrecimientoActivity.class);
                    startActivity(intent);
                }

        );



        return view;
    }
}
