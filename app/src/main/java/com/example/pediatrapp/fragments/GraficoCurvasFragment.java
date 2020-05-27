package com.example.pediatrapp.fragments;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.DatosCurva;
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

import java.util.ArrayList;
import java.util.Map;

public class GraficoCurvasFragment extends Fragment {

    private LineChart lineaPrueba;
    private ArrayList<DatosCurva>  listaCurvas;

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

        lineaPrueba = (LineChart) view.findViewById(R.id.lineprueba);


      cargarLineas(4);


        return view;
    }



    public void cargarLineas(int tipoGrafica){



        Description ds = new Description();
        ds.setTextSize(20);
        lineaPrueba.setDrawGridBackground(false);
        lineaPrueba.setBorderColor(Color.BLUE);

        switch (tipoGrafica) {
            case 1:
            lineaPrueba.setData(dataSetsPesoTalla());
            ds.setText("Peso/Talla");
            lineaPrueba.setDescription(ds);
            break;
            case 2:
                lineaPrueba.setData(dataSetsEdadTalla());
                ds.setText("Talla/EDad");
                lineaPrueba.setDescription(ds);

                break;

            case 3:
                lineaPrueba.setData(dataSetsCabezaEdad());
                ds.setText("Medida Cabeza/Edad");
                lineaPrueba.setDescription(ds);

                break;
            case 4:
                lineaPrueba.setData(dataSetsPesoEdad());
                ds.setText("Peso/Edad");
                lineaPrueba.setDescription(ds);
                break;
        }
        lineaPrueba.invalidate();

    }



    public LineData  dataSetsPesoTalla(){
        LineDataSet lineDataSet2 = new LineDataSet(pesoTalla(), "Referencia");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet2);
        LineData data = new LineData(dataSets);

        return data;
    }
    public LineData  dataSetsEdadTalla(){
        LineDataSet lineDataSet2 = new LineDataSet(edadTalla(), "Referencia");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet2);
        LineData data = new LineData(dataSets);

        return data;
    }

    public LineData  dataSetsCabezaEdad(){
        LineDataSet lineDataSet2 = new LineDataSet(cabezaEdad(), "Referencia");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet2);
        LineData data = new LineData(dataSets);

        return data;
    }

    public LineData  dataSetsPesoEdad(){
        LineDataSet lineDataSet2 = new LineDataSet(pesoEdad(), "Referencia");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet2);

        if(listaCurvas.size() != 0){

            LineDataSet lineDataSet3 = new LineDataSet(puntosPesosEdades(),"Medida Actual");
            lineDataSet3.setCircleColor(Color.RED);
            lineDataSet3.setCircleRadius(5);
            dataSets.add(lineDataSet3);
        }

        LineData data = new LineData(dataSets);

        return data;
    }


    private ArrayList<Entry> pesoTalla() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(50, 4));
        entries.add(new Entry(70,11 ));
        entries.add(new Entry(90, 16));
        entries.add(new Entry(110, 24));
        entries.add(new Entry(80, 14));
        entries.add(new Entry(60, 8));
        entries.add(new Entry(100, 20));


        return entries;
    }

    private ArrayList<Entry> edadTalla() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(2, 63));
        entries.add(new Entry(4,68 ));
        entries.add(new Entry(6, 73));
        entries.add(new Entry(8, 75));
        entries.add(new Entry(10, 78));
        entries.add(new Entry(12, 82));



        return entries;
    }

    private ArrayList<Entry> cabezaEdad() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 37));
        entries.add(new Entry(1,49));
        entries.add(new Entry(2, 51));
        entries.add(new Entry(3, 53));
        entries.add(new Entry(4, 54));
        entries.add(new Entry(5, 56));



        return entries;
    }

    private ArrayList<Entry> pesoEdad() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 4));
        entries.add(new Entry(1,12));
        entries.add(new Entry(2, 16));
        entries.add(new Entry(3, 18));
        entries.add(new Entry(4, 21));
        entries.add(new Entry(5, 24));



        return entries;
    }

    private ArrayList<Entry>  puntosPesosEdades(){
        //Relacion Edad en x,  peso en y

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i = 0; i<listaCurvas.size();i++){

            entries.add(new Entry(listaCurvas.get(i).getEdad(), listaCurvas.get(i).getPeso()));
        }
        return entries;

    }

    private ArrayList<Entry>  PuntosCabezaEdades(){
        //Relacion cabeza en y , edad en x

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i = 0; i<listaCurvas.size();i++){

            entries.add(new Entry(listaCurvas.get(i).getEdad(), listaCurvas.get(i).getMedida_cabeza()));
        }
        return entries;

    }

    private ArrayList<Entry>  PuntosEdadesTalla(){
        //Relacion Talla en y , edad en x

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i = 0; i<listaCurvas.size();i++){

            entries.add(new Entry(listaCurvas.get(i).getEdad(), listaCurvas.get(i).getTalla()));
        }
        return entries;

    }

    private ArrayList<Entry>  PuntosPesoTalla(){
        //Relacion Peso en y , talla en x

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i = 0; i<listaCurvas.size();i++){

            entries.add(new Entry(listaCurvas.get(i).getTalla(), listaCurvas.get(i).getPeso()));
        }
        return entries;

    }




}
