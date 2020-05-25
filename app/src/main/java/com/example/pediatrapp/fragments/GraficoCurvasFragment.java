package com.example.pediatrapp.fragments;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pediatrapp.R;
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
    private ArrayList<String>  puntos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_grafico_curvas, container, false);

        lineaPrueba = (LineChart) view.findViewById(R.id.lineprueba);
        puntos = new ArrayList<>();

        puntos.add("1");
        puntos.add("2");
        puntos.add("3");
        puntos.add("4");

      cargarLineas();


        return view;
    }



    public void cargarLineas(){


        LineDataSet lineDataSet = new LineDataSet(dataValues(), "Sata set");
        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "Sata set 1");
        LineDataSet lineDataSet2 = new LineDataSet(dataValues2(), "Sata set  2");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);

        LineData data = new LineData(dataSets);
        lineaPrueba.setDrawGridBackground(false);
        lineaPrueba.setBorderColor(Color.BLUE);
        lineaPrueba.setData(data);
        Description ds = new Description();
        ds.setText("");
        lineaPrueba.setDescription(ds);
        lineaPrueba.invalidate();

    }



    private ArrayList<Entry> dataValues() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 5));
        entries.add(new Entry(1, 15));
        entries.add(new Entry(2, 25));
        entries.add(new Entry(3, 35));
        entries.add(new Entry(4, 25));


        return entries;
    }

    private ArrayList<Entry> dataValues1() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 8));
        entries.add(new Entry(1, 18));
        entries.add(new Entry(2, 28));
        entries.add(new Entry(3, 38));
        entries.add(new Entry(4, 28));


        return entries;
    }
    private ArrayList<Entry> dataValues2() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 10));
        entries.add(new Entry(1, 20));
        entries.add(new Entry(2, 30));
        entries.add(new Entry(3, 40));
        entries.add(new Entry(4, 45));


        return entries;
    }

/*
    public Chart gameCharts(Chart chart, String description, int background, int animaY ){

        chart.getDescription().setText(description);
        chart.setBackgroundColor(background);
        chart.getDescription().setTextSize(15);
        chart.animateX(animaY);

        return chart;
    }

    public void legend(Chart chart){

        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        ArrayList<LegendEntry> entires = new ArrayList<>();

        for (int i = 0; i <puntos.size(); i++){

            LegendEntry entry = new LegendEntry();
            entry.label = puntos.get(i);
            entires.add(entry);

        }
        legend.setCustom(entires);

    }

    public void asix(XAxis axis){


        axis.setGranularityEnabled(true);
        axis.setPosition(XAxis.XAxisPosition.BOTTOM);
        axis.setValueFormatter(new IndexAxisValueFormatter(puntos));
    }

    private void axisLeft(YAxis axis){

        axis.setSpaceTop(38);
        axis.setAxisMinimum(0);
    }
    private void axiRight(YAxis axis){

        axis.setEnabled(false);
    }



    public void creatChart(){

        lineaPrueba = (LineChart) gameCharts( lineaPrueba, "Prueba", Color.RED, 300);

        cargarLineas();
        asix(lineaPrueba.getXAxis());
        axisLeft(lineaPrueba.getAxisLeft());
        axiRight(lineaPrueba.getAxisRight());


    }

    private DataSet dataSet(DataSet dataSet){

        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextSize(Color.YELLOW);
        dataSet.setValueTextSize(10);

        return dataSet;
    }*/




}
