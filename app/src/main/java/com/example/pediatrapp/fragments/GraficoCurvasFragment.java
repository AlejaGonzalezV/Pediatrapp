package com.example.pediatrapp.fragments;

import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.DatosCurva;
import com.example.pediatrapp.view.CurvaActivity;
import com.example.pediatrapp.view.CurvasGraficoActivity;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraficoCurvasFragment extends Fragment {

    private String idhijo;
    private String sexo;
    private Button PesoTallaBT, TallaEdadBT, CabezaEdadBT, PesoEdadBT;

    public GraficoCurvasFragment (){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_grafico_curvas, container, false);

        PesoTallaBT = view.findViewById(R.id.PesoTallaBT);
        TallaEdadBT = view.findViewById(R.id.TallaEdadBT);
        CabezaEdadBT = view.findViewById(R.id.CabezaEdadBT);
        PesoEdadBT = view.findViewById(R.id.PesoEdadBT);
        idhijo = getArguments().getString("idhijo");
        sexo = getArguments().getString("sexo");


        PesoTallaBT.setOnClickListener(
                (v) -> {

                    Intent intent = new Intent(getContext(), CurvaActivity.class);
                    intent.putExtra("type", "PesoTalla");
                    intent.putExtra("idhijo", idhijo);
                    intent.putExtra("sexo", sexo);
                    getContext().startActivity(intent);


        });

        TallaEdadBT.setOnClickListener(
                (v) ->{
                    Intent intent = new Intent(getContext(), CurvaActivity.class);
                    intent.putExtra("type", "TallaEdad");
                    intent.putExtra("idhijo", idhijo);
                    intent.putExtra("sexo", sexo);
                    getContext().startActivity(intent);

                }
        );

//        CabezaEdadBT.setOnClickListener(
//                (v) ->{
//                    Intent intent = new Intent(getContext(), CurvaActivity.class);
//                    intent.putExtra("type", "CabezaEdad");
//                    intent.putExtra("idhijo", idhijo);
//                    intent.putExtra("sexo", sexo);
//                    getContext().startActivity(intent);
//
//                }
//        );
//
//        PesoEdadBT.setOnClickListener(
//                (v) ->{
//                    Intent intent = new Intent(getContext(), CurvaActivity.class);
//                    intent.putExtra("type", "PesoEdad");
//                    intent.putExtra("idhijo", idhijo);
//                    intent.putExtra("sexo", sexo);
//                    getContext().startActivity(intent);
//
//                }
//        );

        return view;
    }


}
