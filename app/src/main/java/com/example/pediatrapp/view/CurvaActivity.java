package com.example.pediatrapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.DatosCurva;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CurvaActivity extends AppCompatActivity {

    private List<DatosCurva> listaCurvas;
    private LineChart lineaPrueba;
    private String idhijo;
    private String sexo;
    private String type;
    private TextView Titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curva);

        idhijo = getIntent().getStringExtra("idhijo");
        type = getIntent().getStringExtra("type");
        sexo = getIntent().getStringExtra("sexo");

        loadDatos();

        listaCurvas = new ArrayList<>();
        lineaPrueba = (LineChart) findViewById(R.id.lineprueba);
        Titulo = findViewById(R.id.tituloTV);

//        lineaPrueba.setDragEnabled(true);
//        lineaPrueba.setScaleEnabled(false);
//        lineaPrueba.setDrawGridBackground(false);
//        lineaPrueba.setBorderColor(Color.BLUE);
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//
//        entries.add(new Entry(50, 4f));
//        entries.add(new Entry(70,11f ));
//        entries.add(new Entry(90, 16f));
//        entries.add(new Entry(110, 24f));
//        entries.add(new Entry(80, 14f));
//        entries.add(new Entry(60, 8f));
//        entries.add(new Entry(100, 20f));
//
//        Description ds = new Description();
//        ds.setText("Peso/Talla");
//        ds.setTextSize(10);
//        lineaPrueba.setDescription(ds);
//
//        LineDataSet lineDataSet2 = new LineDataSet(entries, "Estandar");
//        lineDataSet2.setColor(Color.GREEN);
//        lineDataSet2.setFillAlpha(110);
//        lineDataSet2.setLineWidth(3f);
//        lineDataSet2.setValueTextSize(10f);
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet2);
//        LineData data = new LineData(dataSets);
//        lineaPrueba.setData(data);




        Log.e(">>> type", type);
        Log.e("hijo", idhijo);

    }


    public void cargarLineas(String tipoGrafica){



        Description ds = new Description();
        ds.setTextSize(20);
        lineaPrueba.setDrawGridBackground(false);
       // lineaPrueba.setBorderColor(Color.BLUE);

        switch (tipoGrafica) {
            case "PesoTalla":
                lineaPrueba.setData(dataSetsPesoTalla());
                ds.setText("Peso/Talla");
                Titulo.setText("Peso - Talla");
                lineaPrueba.setDescription(ds);
                break;
            case "TallaEdad":
                lineaPrueba.setData(dataSetsEdadTalla());
                ds.setText("Talla/Edad");
                Titulo.setText("Talla - Edad");
                lineaPrueba.setDescription(ds);

                break;

//            case 3:
//                lineaPrueba.setData(dataSetsCabezaEdad());
//                ds.setText("Medida Cabeza/Edad");
//                lineaPrueba.setDescription(ds);
//
//                break;
//            case 4:
//                lineaPrueba.setData(dataSetsPesoEdad());
//                ds.setText("Peso/Edad");
//                lineaPrueba.setDescription(ds);
//                break;
        }
        lineaPrueba.invalidate();

    }



    public LineData dataSetsPesoTalla(){
        LineDataSet lineDataSet2;
        LineDataSet lineDataSet4;
        LineDataSet lineDataSet5;
        LineDataSet lineDataSet6;
        LineDataSet lineDataSet7;
        LineDataSet lineDataSet8;
        LineDataSet lineDataSet9;

        if(sexo.equals("Masculino")){
            lineDataSet2 = new LineDataSet(pesoTalla(), "Estand");
            lineDataSet2.setColor(Color.RED);
            lineDataSet4 = new LineDataSet(pesoTalla2(), "Estand");
            lineDataSet4.setColor(Color.rgb(255,155, 0));
            lineDataSet5 = new LineDataSet(pesoTalla3(), "Estand");
            lineDataSet5.setColor(Color.rgb(241, 242, 38));
            lineDataSet6 = new LineDataSet(pesoTalla4(), "Estand");
            lineDataSet6.setColor(Color.rgb(93, 221, 88));
            lineDataSet7 = new LineDataSet(pesoTalla5(), "");
            lineDataSet7.setColor(Color.rgb(241, 242, 38));
            lineDataSet8 = new LineDataSet(pesoTalla6(), "");
            lineDataSet8.setColor(Color.rgb(255,155, 0));
            lineDataSet9 = new LineDataSet(pesoTalla7(),"");
            lineDataSet9.setColor(Color.RED);
        }else{
            lineDataSet2 = new LineDataSet(pesoTallaNiñas(), "Estand");
            lineDataSet2.setColor(Color.RED);
            lineDataSet4 = new LineDataSet(pesoTallaNiñas2(), "Estand");
            lineDataSet4.setColor(Color.rgb(255,155, 0));
            lineDataSet5 = new LineDataSet(pesoTallaNiñas3(), "Estand");
            lineDataSet5.setColor(Color.rgb(241, 242, 38));
            lineDataSet6 = new LineDataSet(pesoTallaNiñas4(), "Estand");
            lineDataSet6.setColor(Color.rgb(93, 221, 88));
            lineDataSet7 = new LineDataSet(pesoTallaNiñas5(), "Estand");
            lineDataSet7.setColor(Color.rgb(241, 242, 38));
            lineDataSet8 = new LineDataSet(pesoTallaNiñas6(), "Estand");
            lineDataSet8.setColor(Color.rgb(255,155, 0));
            lineDataSet9 = new LineDataSet(pesoTallaNiñas7(), "Estand");
            lineDataSet9.setColor(Color.RED);
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet4);
        dataSets.add(lineDataSet5);
        dataSets.add(lineDataSet6);
        dataSets.add(lineDataSet7);
        dataSets.add(lineDataSet8);
        dataSets.add(lineDataSet9);

        if(listaCurvas.size() != 0){

            LineDataSet lineDataSet3 = new LineDataSet(PuntosPesoTalla(),"Hijo");
            lineDataSet3.setCircleColor(Color.RED);
            lineDataSet3.setColor(Color.parseColor("#1976D2"));
            lineDataSet3.setCircleRadius(5);
            dataSets.add(lineDataSet3);
        }

        LineData data = new LineData(dataSets);

        return data;
    }


    public LineData  dataSetsEdadTalla(){
        LineDataSet lineDataSet2;
        LineDataSet lineDataSet4;
        LineDataSet lineDataSet5;
        LineDataSet lineDataSet6;
        LineDataSet lineDataSet7;

        if(sexo.equals("Masculino")){
            lineDataSet2 = new LineDataSet(edadTalla(), "Estand");
            lineDataSet2.setColor(Color.RED);
            lineDataSet4 = new LineDataSet(edadTalla2(), "Estand");
            lineDataSet4.setColor(Color.rgb(241, 242, 38));
            lineDataSet5 = new LineDataSet(edadTalla3(), "Estand");
            lineDataSet5.setColor(Color.rgb(93, 221, 88));
            lineDataSet6 = new LineDataSet(edadTalla4(), "Estand");
            lineDataSet6.setColor(Color.rgb(59, 164, 126));
            lineDataSet7 = new LineDataSet(edadTalla5(), "Estand");
            lineDataSet7.setColor(Color.rgb(16, 139, 94));
        }else{
            lineDataSet2 = new LineDataSet(edadTallaNiñas(), "Estand");
            lineDataSet2.setColor(Color.RED);
            lineDataSet4 = new LineDataSet(edadTalla2Niñas(), "Estand");
            lineDataSet4.setColor(Color.rgb(241, 242, 38));
            lineDataSet5 = new LineDataSet(edadTalla3Niñas(), "Estand");
            lineDataSet5.setColor(Color.rgb(93, 221, 88));
            lineDataSet6 = new LineDataSet(edadTalla4Niñas(), "Estand");
            lineDataSet6.setColor(Color.rgb(59, 164, 126));
            lineDataSet7 = new LineDataSet(edadTalla5Niñas(), "Estand");
            lineDataSet7.setColor(Color.rgb(16, 139, 94));
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet2);
        dataSets.add(lineDataSet4);
        dataSets.add(lineDataSet5);
        dataSets.add(lineDataSet6);
        dataSets.add(lineDataSet7);


        if(listaCurvas.size() != 0){

            LineDataSet lineDataSet3 = new LineDataSet(PuntosEdadesTalla(),"Hijo");
            lineDataSet3.setCircleColor(Color.RED);
            lineDataSet3.setColor(Color.parseColor("#1976D2"));
            lineDataSet3.setCircleRadius(5);
            dataSets.add(lineDataSet3);
        }
        LineData data = new LineData(dataSets);

        return data;
    }



//    public LineData  dataSetsCabezaEdad(){
//        LineDataSet lineDataSet2 = new LineDataSet(cabezaEdad(), "Referencia");
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet2);
//        LineData data = new LineData(dataSets);
//
//        return data;
//    }
//
//    public LineData  dataSetsPesoEdad(){
//        LineDataSet lineDataSet2 = new LineDataSet(pesoEdad(), "Referencia");
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        lineDataSet2.setColor(Color.GREEN);
//        dataSets.add(lineDataSet2);
//
//        if(listaCurvas.size() != 0){
//
//            LineDataSet lineDataSet3 = new LineDataSet(puntosPesosEdades(),"Medida Actual");
//            lineDataSet3.setCircleColor(Color.RED);
//            lineDataSet3.setCircleRadius(5);
//            dataSets.add(lineDataSet3);
//        }
//
//        LineData data = new LineData(dataSets);
//
//        return data;
//    }

//    private ArrayList<Entry>  puntosPesosEdades(){
//        //Relacion Edad en x,  peso en y
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//        for(int i = 0; i<listaCurvas.size();i++){
//
//            entries.add(new Entry(listaCurvas.get(i).getEdad(), listaCurvas.get(i).getPeso()));
//        }
//        return entries;
//
//    }

    private ArrayList<Entry> pesoTalla() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(45, 2));
        entries.add(new Entry(50, 2));
        entries.add(new Entry(55, 4));
        entries.add(new Entry(60, 5));
        entries.add(new Entry(65, 6));
        entries.add(new Entry(70, 7));
        entries.add(new Entry(75, 8));
        entries.add(new Entry(80, 8));
        entries.add(new Entry(85, 9));
        entries.add(new Entry(90, 10));
        entries.add(new Entry(95, 11));
        entries.add(new Entry(100, 12));
        entries.add(new Entry(105, 13));
        entries.add(new Entry(110, 14));
        entries.add(new Entry(115, 16));
        entries.add(new Entry(120, 17));


        return entries;
    }

    private ArrayList<Entry> pesoTalla2() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 2));
        entries.add(new Entry(50, 3));
        entries.add(new Entry(55, 4));
        entries.add(new Entry(60, 5));
        entries.add(new Entry(65, 6));
        entries.add(new Entry(70, 7));
        entries.add(new Entry(75, 8));
        entries.add(new Entry(80, 9));
        entries.add(new Entry(85, 10));
        entries.add(new Entry(90, 11));
        entries.add(new Entry(95, 12));
        entries.add(new Entry(100, 13));
        entries.add(new Entry(105, 14));
        entries.add(new Entry(110, 16));
        entries.add(new Entry(115, 17));
        entries.add(new Entry(120, 19));


        return entries;
    }

    private ArrayList<Entry> pesoTalla3() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 2));
        entries.add(new Entry(50, 3));
        entries.add(new Entry(55, 4));
        entries.add(new Entry(60, 5));
        entries.add(new Entry(65, 7));
        entries.add(new Entry(70, 8));
        entries.add(new Entry(75, 9));
        entries.add(new Entry(80, 10));
        entries.add(new Entry(85, 11));
        entries.add(new Entry(90, 12));
        entries.add(new Entry(95, 13));
        entries.add(new Entry(100, 14));
        entries.add(new Entry(105, 16));
        entries.add(new Entry(110, 17));
        entries.add(new Entry(115, 19));
        entries.add(new Entry(120, 20));


        return entries;
    }

    private ArrayList<Entry> pesoTalla4() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 2));
        entries.add(new Entry(50, 3));
        entries.add(new Entry(55, 4));
        entries.add(new Entry(60, 6));
        entries.add(new Entry(65, 7));
        entries.add(new Entry(70, 9));
        entries.add(new Entry(75, 10));
        entries.add(new Entry(80, 11));
        entries.add(new Entry(85, 12));
        entries.add(new Entry(90, 13));
        entries.add(new Entry(95, 14));
        entries.add(new Entry(100, 15));
        entries.add(new Entry(105, 17));
        entries.add(new Entry(110, 18));
        entries.add(new Entry(115, 20));
        entries.add(new Entry(120, 22));


        return entries;
    }

    private ArrayList<Entry> pesoTalla5() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 3));
        entries.add(new Entry(50, 4));
        entries.add(new Entry(55, 5));
        entries.add(new Entry(60, 6));
        entries.add(new Entry(65, 8));
        entries.add(new Entry(70, 9));
        entries.add(new Entry(75, 10));
        entries.add(new Entry(80, 12));
        entries.add(new Entry(85, 13));
        entries.add(new Entry(90, 14));
        entries.add(new Entry(95, 15));
        entries.add(new Entry(100, 17));
        entries.add(new Entry(105, 18));
        entries.add(new Entry(110, 20));
        entries.add(new Entry(115, 22));
        entries.add(new Entry(120, 24));


        return entries;
    }
    private ArrayList<Entry> pesoTalla6() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 3));
        entries.add(new Entry(50, 4));
        entries.add(new Entry(55, 6));
        entries.add(new Entry(60, 7));
        entries.add(new Entry(65, 9));
        entries.add(new Entry(70, 10));
        entries.add(new Entry(75, 12));
        entries.add(new Entry(80, 13));
        entries.add(new Entry(85, 14));
        entries.add(new Entry(90, 15));
        entries.add(new Entry(95, 17));
        entries.add(new Entry(100, 18));
        entries.add(new Entry(105, 20));
        entries.add(new Entry(110, 22));
        entries.add(new Entry(115, 25));
        entries.add(new Entry(120, 27));


        return entries;
    }

    private ArrayList<Entry> pesoTalla7() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 3));
        entries.add(new Entry(50, 4));
        entries.add(new Entry(55, 6));
        entries.add(new Entry(60, 8));
        entries.add(new Entry(65, 10));
        entries.add(new Entry(70, 11));
        entries.add(new Entry(75, 13));
        entries.add(new Entry(80, 14));
        entries.add(new Entry(85, 15));
        entries.add(new Entry(90, 17));
        entries.add(new Entry(95, 18));
        entries.add(new Entry(100, 20));
        entries.add(new Entry(105, 22));
        entries.add(new Entry(110, 24));
        entries.add(new Entry(115, 27));
        entries.add(new Entry(120, 30));

        return entries;
    }


    private ArrayList<Entry> pesoTallaNiñas() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 2));
        entries.add(new Entry(50, 3));
        entries.add(new Entry(55, 4));
        entries.add(new Entry(60, 5));
        entries.add(new Entry(65, 5));
        entries.add(new Entry(70, 6));
        entries.add(new Entry(75, 7));
        entries.add(new Entry(80, 8));
        entries.add(new Entry(85, 9));
        entries.add(new Entry(90, 10));
        entries.add(new Entry(95, 11));
        entries.add(new Entry(100, 12));
        entries.add(new Entry(105, 13));
        entries.add(new Entry(110, 14));
        entries.add(new Entry(115, 16));
        entries.add(new Entry(120, 17));


        return entries;
    }

    private ArrayList<Entry> pesoTallaNiñas2() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 2));
        entries.add(new Entry(50, 3));
        entries.add(new Entry(55, 4));
        entries.add(new Entry(60, 5));
        entries.add(new Entry(65, 6));
        entries.add(new Entry(70, 7));
        entries.add(new Entry(75, 8));
        entries.add(new Entry(80, 9));
        entries.add(new Entry(85, 10));
        entries.add(new Entry(90, 11));
        entries.add(new Entry(95, 12));
        entries.add(new Entry(100, 13));
        entries.add(new Entry(105, 14));
        entries.add(new Entry(110, 16));
        entries.add(new Entry(115, 17));
        entries.add(new Entry(120, 19));


        return entries;
    }

    private ArrayList<Entry> pesoTallaNiñas3() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 2));
        entries.add(new Entry(50, 3));
        entries.add(new Entry(55, 4));
        entries.add(new Entry(60, 5));
        entries.add(new Entry(65, 6));
        entries.add(new Entry(70, 8));
        entries.add(new Entry(75, 8));
        entries.add(new Entry(80, 9));
        entries.add(new Entry(85, 10));
        entries.add(new Entry(90, 12));
        entries.add(new Entry(95, 13));
        entries.add(new Entry(100, 14));
        entries.add(new Entry(105, 15));
        entries.add(new Entry(110, 17));
        entries.add(new Entry(115, 19));
        entries.add(new Entry(120, 21));


        return entries;
    }
    private ArrayList<Entry> pesoTallaNiñas4() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 2));
        entries.add(new Entry(50, 3));
        entries.add(new Entry(55, 5));
        entries.add(new Entry(60, 6));
        entries.add(new Entry(65, 7));
        entries.add(new Entry(70, 8));
        entries.add(new Entry(75, 9));
        entries.add(new Entry(80, 10));
        entries.add(new Entry(85, 11));
        entries.add(new Entry(90, 13));
        entries.add(new Entry(95, 14));
        entries.add(new Entry(100, 15));
        entries.add(new Entry(105, 17));
        entries.add(new Entry(110, 19));
        entries.add(new Entry(115, 21));
        entries.add(new Entry(120, 23));


        return entries;
    }
    private ArrayList<Entry> pesoTallaNiñas5() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 3));
        entries.add(new Entry(50, 4));
        entries.add(new Entry(55, 5));
        entries.add(new Entry(60, 6));
        entries.add(new Entry(65, 8));
        entries.add(new Entry(70, 9));
        entries.add(new Entry(75, 10));
        entries.add(new Entry(80, 11));
        entries.add(new Entry(85, 12));
        entries.add(new Entry(90, 14));
        entries.add(new Entry(95, 15));
        entries.add(new Entry(100, 17));
        entries.add(new Entry(105, 18));
        entries.add(new Entry(110, 21));
        entries.add(new Entry(115, 23));
        entries.add(new Entry(120, 25));


        return entries;
    }
    private ArrayList<Entry> pesoTallaNiñas6() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 3));
        entries.add(new Entry(50, 4));
        entries.add(new Entry(55, 6));
        entries.add(new Entry(60, 7));
        entries.add(new Entry(65, 9));
        entries.add(new Entry(70, 10));
        entries.add(new Entry(75, 11));
        entries.add(new Entry(80, 12));
        entries.add(new Entry(85, 14));
        entries.add(new Entry(90, 15));
        entries.add(new Entry(95, 17));
        entries.add(new Entry(100, 18));
        entries.add(new Entry(105, 20));
        entries.add(new Entry(110, 23));
        entries.add(new Entry(115, 26));
        entries.add(new Entry(120, 28));


        return entries;
    }
    private ArrayList<Entry> pesoTallaNiñas7() {
        ArrayList<Entry> entries = new ArrayList<>();

        entries.add(new Entry(45, 3));
        entries.add(new Entry(50, 4));
        entries.add(new Entry(55, 6));
        entries.add(new Entry(60, 8));
        entries.add(new Entry(65, 10));
        entries.add(new Entry(70, 11));
        entries.add(new Entry(75, 12));
        entries.add(new Entry(80, 14));
        entries.add(new Entry(85, 15));
        entries.add(new Entry(90, 17));
        entries.add(new Entry(95, 18));
        entries.add(new Entry(100, 20));
        entries.add(new Entry(105, 23));
        entries.add(new Entry(110, 25));
        entries.add(new Entry(115, 28));
        entries.add(new Entry(120, 31));


        return entries;
    }







    private ArrayList<Entry> edadTalla() {

        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 46));
        entries.add(new Entry(2, 55));
        entries.add(new Entry(4, 60));
        entries.add(new Entry(6, 63));
        entries.add(new Entry(8, 66));
        entries.add(new Entry(10, 69));
        entries.add(new Entry(12, 71));
        entries.add(new Entry(14, 73));
        entries.add(new Entry(16, 75));
        entries.add(new Entry(18, 77));
        entries.add(new Entry(20, 79));
        entries.add(new Entry(22, 80));
        entries.add(new Entry(24, 81));
        entries.add(new Entry(26, 83));
        entries.add(new Entry(28, 84));
        entries.add(new Entry(30, 85));
        entries.add(new Entry(32, 86));
        entries.add(new Entry(34, 88));
        entries.add(new Entry(36, 89));
        entries.add(new Entry(38, 90));
        entries.add(new Entry(40, 91));
        entries.add(new Entry(42, 92));
        entries.add(new Entry(44, 93));
        entries.add(new Entry(46, 94));
        entries.add(new Entry(48, 95));
        entries.add(new Entry(50, 96));
        entries.add(new Entry(52, 97));
        entries.add(new Entry(54, 98));
        entries.add(new Entry(56, 99));
        entries.add(new Entry(58, 100));
        entries.add(new Entry(60, 101));



        return entries;
    }

    private ArrayList<Entry> edadTalla2() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 48));
        entries.add(new Entry(2, 57));
        entries.add(new Entry(4, 62));
        entries.add(new Entry(6, 66));
        entries.add(new Entry(8, 68));
        entries.add(new Entry(10, 71));
        entries.add(new Entry(12, 73));
        entries.add(new Entry(14, 76));
        entries.add(new Entry(16, 78));
        entries.add(new Entry(18, 80));
        entries.add(new Entry(20, 81));
        entries.add(new Entry(22, 83));
        entries.add(new Entry(24, 84));
        entries.add(new Entry(26, 86));
        entries.add(new Entry(28, 87));
        entries.add(new Entry(30, 89));
        entries.add(new Entry(32, 90));
        entries.add(new Entry(34, 91));
        entries.add(new Entry(36, 92));
        entries.add(new Entry(38, 94));
        entries.add(new Entry(40, 95));
        entries.add(new Entry(42, 96));
        entries.add(new Entry(44, 97));
        entries.add(new Entry(46, 98));
        entries.add(new Entry(48, 99));
        entries.add(new Entry(50, 100));
        entries.add(new Entry(52, 101));
        entries.add(new Entry(54, 102));
        entries.add(new Entry(56, 103));
        entries.add(new Entry(58, 104));
        entries.add(new Entry(60, 105));



        return entries;
    }

    private ArrayList<Entry> edadTalla3() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 50));
        entries.add(new Entry(2, 58));
        entries.add(new Entry(4, 64));
        entries.add(new Entry(6, 68));
        entries.add(new Entry(8, 71));
        entries.add(new Entry(10, 73));
        entries.add(new Entry(12, 76));
        entries.add(new Entry(14, 78));
        entries.add(new Entry(16, 80));
        entries.add(new Entry(18, 82));
        entries.add(new Entry(20, 84));
        entries.add(new Entry(22, 86));
        entries.add(new Entry(24, 87));
        entries.add(new Entry(26, 89));
        entries.add(new Entry(28, 90));
        entries.add(new Entry(30, 92));
        entries.add(new Entry(32, 93));
        entries.add(new Entry(34, 95));
        entries.add(new Entry(36, 96));
        entries.add(new Entry(38, 97));
        entries.add(new Entry(40, 99));
        entries.add(new Entry(42, 100));
        entries.add(new Entry(44, 101));
        entries.add(new Entry(46, 102));
        entries.add(new Entry(48, 103));
        entries.add(new Entry(50, 105));
        entries.add(new Entry(52, 106));
        entries.add(new Entry(54, 107));
        entries.add(new Entry(56, 108));
        entries.add(new Entry(58, 109));
        entries.add(new Entry(60, 110));



        return entries;
    }

    private ArrayList<Entry> edadTalla4() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 52));
        entries.add(new Entry(2, 60));
        entries.add(new Entry(4, 66));
        entries.add(new Entry(6, 70));
        entries.add(new Entry(8, 73));
        entries.add(new Entry(10, 76));
        entries.add(new Entry(12, 78));
        entries.add(new Entry(14, 81));
        entries.add(new Entry(16, 83));
        entries.add(new Entry(18, 85));
        entries.add(new Entry(20, 87));
        entries.add(new Entry(22, 89));
        entries.add(new Entry(24, 90));
        entries.add(new Entry(26, 92));
        entries.add(new Entry(28, 94));
        entries.add(new Entry(30, 95));
        entries.add(new Entry(32, 97));
        entries.add(new Entry(34, 98));
        entries.add(new Entry(36, 100));
        entries.add(new Entry(38, 101));
        entries.add(new Entry(40, 103));
        entries.add(new Entry(42, 104));
        entries.add(new Entry(44, 105));
        entries.add(new Entry(46, 106));
        entries.add(new Entry(48, 108));
        entries.add(new Entry(50, 109));
        entries.add(new Entry(52, 110));
        entries.add(new Entry(54, 111));
        entries.add(new Entry(56, 112));
        entries.add(new Entry(58, 113));
        entries.add(new Entry(60, 115));



        return entries;
    }

    private ArrayList<Entry> edadTalla5() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 53));
        entries.add(new Entry(2, 62));
        entries.add(new Entry(4, 64));
        entries.add(new Entry(6, 68));
        entries.add(new Entry(8, 72));
        entries.add(new Entry(10,78));
        entries.add(new Entry(12, 81));
        entries.add(new Entry(14, 83));
        entries.add(new Entry(16, 85));
        entries.add(new Entry(18, 88));
        entries.add(new Entry(20, 90));
        entries.add(new Entry(22, 92));
        entries.add(new Entry(24, 93));
        entries.add(new Entry(26, 95));
        entries.add(new Entry(28, 97));
        entries.add(new Entry(30, 99));
        entries.add(new Entry(32, 100));
        entries.add(new Entry(34, 102));
        entries.add(new Entry(36, 103));
        entries.add(new Entry(38, 105));
        entries.add(new Entry(40, 106));
        entries.add(new Entry(42, 108));
        entries.add(new Entry(44, 109));
        entries.add(new Entry(46, 111));
        entries.add(new Entry(48, 112));
        entries.add(new Entry(50, 113));
        entries.add(new Entry(52, 114));
        entries.add(new Entry(54, 116));
        entries.add(new Entry(56, 117));
        entries.add(new Entry(58, 118));
        entries.add(new Entry(60, 119));



        return entries;
    }



    private ArrayList<Entry> edadTallaNiñas() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 45));
        entries.add(new Entry(2, 53));
        entries.add(new Entry(4, 58));
        entries.add(new Entry(6, 61));
        entries.add(new Entry(8, 64));
        entries.add(new Entry(10, 67));
        entries.add(new Entry(12, 69));
        entries.add(new Entry(14, 71));
        entries.add(new Entry(16, 73));
        entries.add(new Entry(18, 75));
        entries.add(new Entry(20, 77));
        entries.add(new Entry(22, 78));
        entries.add(new Entry(24, 79));
        entries.add(new Entry(26, 81));
        entries.add(new Entry(28, 82));
        entries.add(new Entry(30, 84));
        entries.add(new Entry(32, 85));
        entries.add(new Entry(34, 86));
        entries.add(new Entry(36, 87));
        entries.add(new Entry(38, 89));
        entries.add(new Entry(40, 90));
        entries.add(new Entry(42, 91));
        entries.add(new Entry(44, 92));
        entries.add(new Entry(46, 93));
        entries.add(new Entry(48, 94));
        entries.add(new Entry(50, 95));
        entries.add(new Entry(52, 96));
        entries.add(new Entry(54, 97));
        entries.add(new Entry(56, 98));
        entries.add(new Entry(58, 99));
        entries.add(new Entry(60, 100));



        return entries;
    }

    private ArrayList<Entry> edadTalla2Niñas() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 47));
        entries.add(new Entry(2, 55));
        entries.add(new Entry(4, 60));
        entries.add(new Entry(6, 64));
        entries.add(new Entry(8, 66));
        entries.add(new Entry(10, 69));
        entries.add(new Entry(12, 71));
        entries.add(new Entry(14, 74));
        entries.add(new Entry(16, 76));
        entries.add(new Entry(18, 78));
        entries.add(new Entry(20, 80));
        entries.add(new Entry(22, 81));
        entries.add(new Entry(24, 83));
        entries.add(new Entry(26, 84));
        entries.add(new Entry(28, 86));
        entries.add(new Entry(30, 87));
        entries.add(new Entry(32, 89));
        entries.add(new Entry(34, 90));
        entries.add(new Entry(36, 91));
        entries.add(new Entry(38, 92));
        entries.add(new Entry(40, 94));
        entries.add(new Entry(42, 95));
        entries.add(new Entry(44, 96));
        entries.add(new Entry(46, 97));
        entries.add(new Entry(48, 98));
        entries.add(new Entry(50, 100));
        entries.add(new Entry(52, 101));
        entries.add(new Entry(54, 102));
        entries.add(new Entry(56, 103));
        entries.add(new Entry(58, 104));
        entries.add(new Entry(60, 105));



        return entries;
    }

    private ArrayList<Entry> edadTalla3Niñas() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 49));
        entries.add(new Entry(2, 57));
        entries.add(new Entry(4, 62));
        entries.add(new Entry(6, 66));
        entries.add(new Entry(8, 69));
        entries.add(new Entry(10, 72));
        entries.add(new Entry(12, 74));
        entries.add(new Entry(14, 76));
        entries.add(new Entry(16, 78));
        entries.add(new Entry(18, 81));
        entries.add(new Entry(20, 83));
        entries.add(new Entry(22, 85));
        entries.add(new Entry(24, 86));
        entries.add(new Entry(26, 87));
        entries.add(new Entry(28, 89));
        entries.add(new Entry(30, 91));
        entries.add(new Entry(32, 92));
        entries.add(new Entry(34, 94));
        entries.add(new Entry(36, 95));
        entries.add(new Entry(38, 96));
        entries.add(new Entry(40, 98));
        entries.add(new Entry(42, 99));
        entries.add(new Entry(44, 100));
        entries.add(new Entry(46, 102));
        entries.add(new Entry(48, 103));
        entries.add(new Entry(50, 104));
        entries.add(new Entry(52, 105));
        entries.add(new Entry(54, 106));
        entries.add(new Entry(56, 107));
        entries.add(new Entry(58, 108));
        entries.add(new Entry(60, 109));


        return entries;
    }

    private ArrayList<Entry> edadTalla4Niñas() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 51));
        entries.add(new Entry(2, 59));
        entries.add(new Entry(4, 64));
        entries.add(new Entry(6, 68));
        entries.add(new Entry(8, 71));
        entries.add(new Entry(10, 74));
        entries.add(new Entry(12, 77));
        entries.add(new Entry(14, 79));
        entries.add(new Entry(16, 81));
        entries.add(new Entry(18, 84));
        entries.add(new Entry(20, 86));
        entries.add(new Entry(22, 88));
        entries.add(new Entry(24, 89));
        entries.add(new Entry(26, 91));
        entries.add(new Entry(28, 93));
        entries.add(new Entry(30, 94));
        entries.add(new Entry(32, 96));
        entries.add(new Entry(34, 97));
        entries.add(new Entry(36, 99));
        entries.add(new Entry(38, 100));
        entries.add(new Entry(40, 102));
        entries.add(new Entry(42, 103));
        entries.add(new Entry(44, 105));
        entries.add(new Entry(46, 106));
        entries.add(new Entry(48, 107));
        entries.add(new Entry(50, 108));
        entries.add(new Entry(52, 110));
        entries.add(new Entry(54, 111));
        entries.add(new Entry(56, 112));
        entries.add(new Entry(58, 113));
        entries.add(new Entry(60, 114));



        return entries;
    }

    private ArrayList<Entry> edadTalla5Niñas() {
        ArrayList<Entry> entries = new ArrayList<>();


        entries.add(new Entry(0, 53));
        entries.add(new Entry(2, 61));
        entries.add(new Entry(4, 66));
        entries.add(new Entry(6, 70));
        entries.add(new Entry(8, 74));
        entries.add(new Entry(10, 76));
        entries.add(new Entry(12, 79));
        entries.add(new Entry(14, 82));
        entries.add(new Entry(16, 84));
        entries.add(new Entry(18, 86));
        entries.add(new Entry(20, 89));
        entries.add(new Entry(22, 91));
        entries.add(new Entry(24, 92));
        entries.add(new Entry(26, 94));
        entries.add(new Entry(28, 96));
        entries.add(new Entry(30, 98));
        entries.add(new Entry(32, 100));
        entries.add(new Entry(34, 101));
        entries.add(new Entry(36, 103));
        entries.add(new Entry(38, 104));
        entries.add(new Entry(40, 106));
        entries.add(new Entry(42, 107));
        entries.add(new Entry(44, 109));
        entries.add(new Entry(46, 110));
        entries.add(new Entry(48, 111));
        entries.add(new Entry(50, 113));
        entries.add(new Entry(52, 114));
        entries.add(new Entry(54, 115));
        entries.add(new Entry(56, 116));
        entries.add(new Entry(58, 118));
        entries.add(new Entry(60, 119));



        return entries;
    }


    private ArrayList<Entry>  PuntosEdadesTalla(){
        //Relacion Talla en y , edad en x

        ArrayList<Entry> entries = new ArrayList<>();

        for(int i = 0; i<listaCurvas.size();i++){

            String[] edad = listaCurvas.get(i).getEdad().split(" ");
            int años = 0;
            int meses = 0;
            if(edad.length == 2){
                meses = Integer.parseInt(edad[0]);
            }else{
                años = Integer.parseInt(edad[0]) * 12;
                meses = Integer.parseInt(edad[3]);
            }
            if((años + meses) <= 60){
                entries.add(new Entry(años + meses, listaCurvas.get(i).getTalla()));
            }
        }
        return entries;

    }

    private ArrayList<Entry>  PuntosPesoTalla() {
        //Relacion Peso en y , talla en x

        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < listaCurvas.size(); i++) {

            entries.add(new Entry(listaCurvas.get(i).getTalla(), listaCurvas.get(i).getPeso()));
        }
        return entries;
    }

//    private ArrayList<Entry> cabezaEdad() {
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//
//        entries.add(new Entry(0, 37));
//        entries.add(new Entry(1,49));
//        entries.add(new Entry(2, 51));
//        entries.add(new Entry(3, 53));
//        entries.add(new Entry(4, 54));
//        entries.add(new Entry(5, 56));
//
//
//
//        return entries;
//    }
//
//    private ArrayList<Entry> pesoEdad() {
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//
//        entries.add(new Entry(0, 4));
//        entries.add(new Entry(1,12));
//        entries.add(new Entry(2, 16));
//        entries.add(new Entry(3, 18));
//        entries.add(new Entry(4, 21));
//        entries.add(new Entry(5, 24));
//
//
//
//        return entries;
//    }


//    public void cargarLineas(){
//
//
//
//        Description ds = new Description();
//        ds.setTextSize(20);
//        lineaPrueba.setDrawGridBackground(false);
//        lineaPrueba.setBorderColor(Color.BLUE);
//        lineaPrueba.setData(dataSetsPesoTalla());
//        ds.setText("Peso/Talla");
//        lineaPrueba.setDescription(ds);
//      //  lineaPrueba.invalidate();
//
//    }

//    public LineData dataSetsPesoTalla(){
//        LineDataSet lineDataSet2 = new LineDataSet(pesoTalla(), "Referencia");
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet2);
//        LineData data = new LineData(dataSets);
//
//        return data;
//    }

//    private ArrayList<Entry> pesoTalla() {
//
//
//
//
//        return entries;
//    }



    private void loadDatos() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Curvas_crecimiento").child(idhijo).child("Curvas_crecimiento");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //adapter.removeAll();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    DatosCurva dato = snapshot.getValue(DatosCurva.class);
                    if (dato != null) {

                        listaCurvas.add(dato);

                    }

                }
                cargarLineas(type);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

//    private ArrayList<Entry>  puntosPesosEdades(){
//        //Relacion Edad en x,  peso en y
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//        for(int i = 0; i<listaCurvas.size();i++){
//
//            entries.add(new Entry(listaCurvas.get(i).getEdad(), listaCurvas.get(i).getPeso()));
//        }
//        return entries;
//
//    }


//    private LineChart lineaPrueba;
//    private ArrayList<DatosCurva> listaCurvas;
//
//    public GraficoCurvasFragment (){
//
//
//    }

//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view =  inflater.inflate(R.layout.fragment_grafico_curvas, container, false);
//
//        lineaPrueba = (LineChart) view.findViewById(R.id.lineprueba);
//
//
//        cargarLineas(4);
//
//
//        return view;
//    }
//
//
//
//    public void cargarLineas(int tipoGrafica){
//
//
//
//        Description ds = new Description();
//        ds.setTextSize(20);
//        lineaPrueba.setDrawGridBackground(false);
//        lineaPrueba.setBorderColor(Color.BLUE);
//
//        switch (tipoGrafica) {
//            case 1:
//                lineaPrueba.setData(dataSetsPesoTalla());
//                ds.setText("Peso/Talla");
//                lineaPrueba.setDescription(ds);
//                break;
//            case 2:
//                lineaPrueba.setData(dataSetsEdadTalla());
//                ds.setText("Talla/EDad");
//                lineaPrueba.setDescription(ds);
//
//                break;
//
//            case 3:
//                lineaPrueba.setData(dataSetsCabezaEdad());
//                ds.setText("Medida Cabeza/Edad");
//                lineaPrueba.setDescription(ds);
//
//                break;
//            case 4:
//                lineaPrueba.setData(dataSetsPesoEdad());
//                ds.setText("Peso/Edad");
//                lineaPrueba.setDescription(ds);
//                break;
//        }
//        lineaPrueba.invalidate();
//
//    }
//
//
//
//    public LineData dataSetsPesoTalla(){
//        LineDataSet lineDataSet2 = new LineDataSet(pesoTalla(), "Referencia");
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet2);
//        LineData data = new LineData(dataSets);
//
//        return data;
//    }
//    public LineData  dataSetsEdadTalla(){
//        LineDataSet lineDataSet2 = new LineDataSet(edadTalla(), "Referencia");
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet2);
//        LineData data = new LineData(dataSets);
//
//        return data;
//    }
//
//    public LineData  dataSetsCabezaEdad(){
//        LineDataSet lineDataSet2 = new LineDataSet(cabezaEdad(), "Referencia");
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet2);
//        LineData data = new LineData(dataSets);
//
//        return data;
//    }
//
//    public LineData  dataSetsPesoEdad(){
//        LineDataSet lineDataSet2 = new LineDataSet(pesoEdad(), "Referencia");
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet2);
//
//        if(listaCurvas.size() != 0){
//
//            LineDataSet lineDataSet3 = new LineDataSet(puntosPesosEdades(),"Medida Actual");
//            lineDataSet3.setCircleColor(Color.RED);
//            lineDataSet3.setCircleRadius(5);
//            dataSets.add(lineDataSet3);
//        }
//
//        LineData data = new LineData(dataSets);
//
//        return data;
//    }
//
//
//    private ArrayList<Entry> pesoTalla() {
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//
//        entries.add(new Entry(50, 4));
//        entries.add(new Entry(70,11 ));
//        entries.add(new Entry(90, 16));
//        entries.add(new Entry(110, 24));
//        entries.add(new Entry(80, 14));
//        entries.add(new Entry(60, 8));
//        entries.add(new Entry(100, 20));
//
//
//        return entries;
//    }
//
//    private ArrayList<Entry> edadTalla() {
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//
//        entries.add(new Entry(2, 63));
//        entries.add(new Entry(4,68 ));
//        entries.add(new Entry(6, 73));
//        entries.add(new Entry(8, 75));
//        entries.add(new Entry(10, 78));
//        entries.add(new Entry(12, 82));
//
//
//
//        return entries;
//    }
//
//    private ArrayList<Entry> cabezaEdad() {
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//
//        entries.add(new Entry(0, 37));
//        entries.add(new Entry(1,49));
//        entries.add(new Entry(2, 51));
//        entries.add(new Entry(3, 53));
//        entries.add(new Entry(4, 54));
//        entries.add(new Entry(5, 56));
//
//
//
//        return entries;
//    }
//
//    private ArrayList<Entry> pesoEdad() {
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//
//        entries.add(new Entry(0, 4));
//        entries.add(new Entry(1,12));
//        entries.add(new Entry(2, 16));
//        entries.add(new Entry(3, 18));
//        entries.add(new Entry(4, 21));
//        entries.add(new Entry(5, 24));
//
//
//
//        return entries;
//    }
//
//    private ArrayList<Entry>  puntosPesosEdades(){
//        //Relacion Edad en x,  peso en y
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//        for(int i = 0; i<listaCurvas.size();i++){
//
//            entries.add(new Entry(listaCurvas.get(i).getEdad(), listaCurvas.get(i).getPeso()));
//        }
//        return entries;
//
//    }
//
//    private ArrayList<Entry>  PuntosCabezaEdades(){
//        //Relacion cabeza en y , edad en x
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//        for(int i = 0; i<listaCurvas.size();i++){
//
//            entries.add(new Entry(listaCurvas.get(i).getEdad(), listaCurvas.get(i).getMedida_cabeza()));
//        }
//        return entries;
//
//    }
//
//    private ArrayList<Entry>  PuntosEdadesTalla(){
//        //Relacion Talla en y , edad en x
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//        for(int i = 0; i<listaCurvas.size();i++){
//
//            entries.add(new Entry(listaCurvas.get(i).getEdad(), listaCurvas.get(i).getTalla()));
//        }
//        return entries;
//
//    }
//
//    private ArrayList<Entry>  PuntosPesoTalla(){
//        //Relacion Peso en y , talla en x
//
//        ArrayList<Entry> entries = new ArrayList<>();
//
//        for(int i = 0; i<listaCurvas.size();i++){
//
//            entries.add(new Entry(listaCurvas.get(i).getTalla(), listaCurvas.get(i).getPeso()));
//        }
//        return entries;
//
//    }
//


}
