package com.example.pediatrapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.utilities.HTTPSWebUtilDomi;
import com.example.pediatrapp.view.ListaVacunasActivity;
import com.example.pediatrapp.view.VacunasActivity;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HijosVacunasAdapter extends BaseAdapter {

    private ArrayList<Hijo> hijos;

    public HijosVacunasAdapter() {
        this.hijos = new ArrayList<>();
    }

    public ArrayList<Hijo> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<Hijo> hijos) {
        this.hijos = hijos;
        notifyDataSetChanged();
    }

    public void addHijo(Hijo hijo){
        hijos.add(hijo);
        notifyDataSetChanged();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.list_item_hijos, null);

        TextView nombreHijoVa = root.findViewById(R.id.nombreHijoTV);
        TextView edadHijoVa = root.findViewById(R.id.edadHijoTV);
        Button verVa = root.findViewById(R.id.verHijoBTN);
        ImageView imagenHijava = root.findViewById(R.id.fotoHijo);

        if(hijos.get(position).getSexo().equals("Femenino")){

            imagenHijava.setImageResource(R.drawable.girl);
        }else{

            imagenHijava.setImageResource(R.drawable.boy);
        }

        nombreHijoVa.setText(hijos.get(position).getNombre());
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaN = LocalDate.parse(hijos.get(position).getNacimiento(), fmt);
        LocalDate hoy = LocalDate.now();

        Period periodo = Period.between(fechaN, hoy);
        String edad1 = String.valueOf(periodo.getYears());

        if(edad1.equals("0")){

            edadHijoVa.setText(String.valueOf(periodo.getMonths()) + " Meses");

        }else {

            edadHijoVa.setText(String.valueOf(periodo.getYears()) + " Años y " + String.valueOf(periodo.getMonths())+ " meses");

        }



        verVa.setOnClickListener(

                (v)->{
                    Intent intent = new Intent(parent.getContext(), ListaVacunasActivity.class);
                    intent.putExtra("idhijo", hijos.get(position).getId());
                    intent.putExtra("elnombre", hijos.get(position).getNombre());
                    parent.getContext().startActivity(intent);


                }
        );

//        ListaHijosVacuna.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Hijo h = (Hijo) adapter.getItem(i);
//                new Thread(
//                        () ->{
//
//                            Intent intent = new Intent(VacunasActivity.this, ListaVacunasActivity.class);
//                            intent.putExtra("idhijo", h.getId());
//                            intent.putExtra("elnombre", h.getNombre());
//                            Log.e(">>>", "inicioIntent");
//
//                            VacunasActivity.this.startActivity(intent);
//                        }
//                ).start();
//
//            }
//        });



        return root ;
    }

}
