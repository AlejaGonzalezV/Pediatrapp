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
import com.example.pediatrapp.fragments.HistoriaPadreFragment;
import com.example.pediatrapp.fragments.HistoriaPediatraFragment;
import com.example.pediatrapp.model.Diagnostico;
import com.example.pediatrapp.model.Vacuna;
import com.example.pediatrapp.view.ListaVacunasActivity;
import com.example.pediatrapp.view.VerVacunaActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaVacunasAdpater extends BaseAdapter {

    private ArrayList<Vacuna> listaVacunas;
    private ListaVacunasActivity activity;

    public ListaVacunasAdpater(ListaVacunasActivity activity) {
        listaVacunas = new ArrayList<>();
        this.activity = activity;
    }

    public ArrayList<Vacuna> getListaVacunas() {
        return listaVacunas;
    }

    @Override
    public int getCount() {
        return listaVacunas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVacunas.get(position);
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
        titulo.setText(listaVacunas.get(position).getNombre_vacuna());
        fecha.setText(listaVacunas.get(position).getFecha_aplicacion());

        Button ver = row.findViewById(R.id.ver);

            ver.setOnClickListener(

                    (v) -> {

                        Vacuna laVacuna = listaVacunas.get(position);
                        Intent intent = new Intent(activity, VerVacunaActivity.class);
                        intent.putExtra("verVacuna", laVacuna);
                        activity.startActivityForResult(intent, 12);
                    }
            );

        return row;
    }

    public void addVacuna(Vacuna vac){
        listaVacunas.add(vac);
        notifyDataSetChanged();
    }

    public void remover(Vacuna vac){

        int pos = 0;
        boolean encontrado = false;
        for(int i = 0; i< listaVacunas.size() && !encontrado; i++){

            if(listaVacunas.get(i).getId().equals(vac.getId())){
                encontrado = true;
                pos = i;
            }
        }

        if(encontrado){
            listaVacunas.remove(pos);
            notifyDataSetChanged();
        }

    }

    public Vacuna getVacuna(int pos){
        return listaVacunas.get(pos);
    }

    public void setListaVacunas(ArrayList<Vacuna> listaVacunas) {
        this.listaVacunas = listaVacunas;
        notifyDataSetChanged();
    }

    /*
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Vacuna laVacuna = (Vacuna) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_listvacuna, null, false);

        TextView nombreVacuna = convertView.findViewById(R.id.tv_nameVaccine);
        TextView fecha = convertView.findViewById(R.id.fechaItemVacuna);

        nombreVacuna.setText(laVacuna.getNombre_vacuna());
        fecha.setText(laVacuna.getIps());
        return convertView;
    }*/
}
