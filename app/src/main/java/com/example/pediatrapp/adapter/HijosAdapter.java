package com.example.pediatrapp.adapter;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.pediatrapp.R;
import com.example.pediatrapp.fragments.ListaHijosFragment;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.pagercontroller.Hijos;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HijosAdapter extends BaseAdapter {

    private ArrayList<Hijo> hijos;
    private ListaHijosFragment view;
    private Hijos viewH;

    public HijosAdapter(ListaHijosFragment view) {

        hijos = new ArrayList<>();
        this.view = view;

    }

    public HijosAdapter(Hijos viewH) {

        hijos = new ArrayList<>();
        this.viewH = viewH;

    }

    @Override
    public int getCount() {
        return hijos.size();
    }

    @Override
    public Object getItem(int position) {
        return hijos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View row = inflater.inflate(R.layout.list_item_hijos, null, false);
        ImageView imagen = row.findViewById(R.id.fotoHijo);
        TextView nombre = row.findViewById(R.id.nombreHijoTV);
        TextView edad = row.findViewById(R.id.edadHijoTV);
        nombre.setText(hijos.get(position).getNombre());

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaN = LocalDate.parse(hijos.get(position).getNacimiento(), fmt);
        LocalDate hoy = LocalDate.now();

        Period periodo = Period.between(fechaN, hoy);
        String edad1 = String.valueOf(periodo.getYears());

        if(edad1.equals("0")){

            edad.setText(String.valueOf(periodo.getMonths()) + " Meses");


        }else {

            edad.setText(String.valueOf(periodo.getYears()) + " Años y " + String.valueOf(periodo.getMonths())+ " meses");

        }


        Button verHijoBTN = row.findViewById(R.id.verHijoBTN);
        verHijoBTN.setOnClickListener(

                (v) -> {

                    if(view != null){

                        view.recibo(position);

                    }else{

                        viewH.recibo(position);

                    }


                }

        );

        if(hijos.get(position).getSexo().equals("Femenino")){

            imagen.setImageResource(R.drawable.girl);

        } else {

            imagen.setImageResource(R.drawable.boy);

        }

        return row;
    }

    public void addHijos(Hijo hijo){

        hijos.add(hijo);
        notifyDataSetChanged();
        Log.e("<<<<<<<<<<<", hijo.getNombre());


    }
}
