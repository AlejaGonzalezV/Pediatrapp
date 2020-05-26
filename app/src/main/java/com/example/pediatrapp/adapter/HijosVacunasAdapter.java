package com.example.pediatrapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.list_item_hijos, null);

        Hijo hijo = hijos.get(position);

        TextView nombreHijoVa = root.findViewById(R.id.nombreHijoTV);
        TextView edadHijoVa = root.findViewById(R.id.edadHijoTV);
        Button verVa = root.findViewById(R.id.verHijoBTN);
        ImageView imagenHijava = root.findViewById(R.id.fotoHijo);

        if(hijos.get(position).getSexo().equals("Femenino")){

            imagenHijava.setImageResource(R.drawable.girl);
        }else{

            imagenHijava.setImageResource(R.drawable.boy);
        }

        nombreHijoVa.setText(""+ hijos.get(position).getNombre());
        edadHijoVa.setText("Edad: "+ hijos.get(position).getEdad());



//        holder.verVa.setOnClickListener(
//
//                (v)->{
//                    Intent intent = new Intent(context, ListaVacunasActivity.class);
//                    intent.putExtra("elnombre", holder.nombreHijoVa.getText().toString());
//                    context.startActivity(intent);
//
//
//                }
//        );

        return root ;
    }

}
