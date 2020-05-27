package com.example.pediatrapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.DatosCurva;
import com.example.pediatrapp.model.Vacuna;
import com.example.pediatrapp.view.ListaVacunasActivity;
import com.example.pediatrapp.view.VerCurvaActivity;
import com.example.pediatrapp.view.VerVacunaActivity;

import java.util.ArrayList;
import java.util.List;

public class ItemTablaCurvasAdpater extends BaseAdapter {

    private ArrayList<DatosCurva> listaCurvas;
    private String idhijo;

    public ItemTablaCurvasAdpater() {
        listaCurvas = new ArrayList<>();
    }

    public ArrayList<DatosCurva> getListaCurvas() {
        return listaCurvas;
    }

    public void setListaCurvas(ArrayList<DatosCurva> listaCurvas) {
        this.listaCurvas = listaCurvas;
    }

    public String getIdhijo() {
        return idhijo;
    }

    public void setIdhijo(String idhijo) {
        this.idhijo = idhijo;
    }

    @Override
    public int getCount() {
        return listaCurvas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCurvas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.item_listvacuna, null, false);
        TextView titulo = row.findViewById(R.id.titulo);
        TextView fecha = row.findViewById(R.id.fecha);
        titulo.setText(listaCurvas.get(position).getFecha());
        fecha.setText(listaCurvas.get(position).getEdad() + " "+ listaCurvas.get(position).getEdadComplemento());

        Button ver = row.findViewById(R.id.ver);

        ver.setOnClickListener(

                (v) -> {

                    DatosCurva curva = listaCurvas.get(position);
                    Intent intent = new Intent(parent.getContext(), VerCurvaActivity.class);
                    intent.putExtra("verCurva", curva);
                    intent.putExtra("idhijo", idhijo);
                    parent.getContext().startActivity(intent);
                }
        );

        return row;
    }

    public void addDatoCurva(DatosCurva datosCurva){
        listaCurvas.add(datosCurva);
        notifyDataSetChanged();
    }

    public void removeAll(){
        listaCurvas.clear();
        notifyDataSetChanged();
    }

    public DatosCurva getDato(int pos){
        return listaCurvas.get(pos);
    }



}
