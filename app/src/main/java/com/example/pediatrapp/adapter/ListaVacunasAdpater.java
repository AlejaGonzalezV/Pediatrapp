package com.example.pediatrapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Vacuna;

import java.util.List;

public class ListaVacunasAdpater extends BaseAdapter {

    private List<Vacuna> listaVacunas;
    private Context context;

    public ListaVacunasAdpater(List<Vacuna> listaVacunas, Context context) {
        this.listaVacunas = listaVacunas;
        this.context = context;
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
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Vacuna laVacuna = (Vacuna) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_listvacuna, null, false);

        TextView nombreVacuna = convertView.findViewById(R.id.tv_nameVaccine);
        TextView fecha = convertView.findViewById(R.id.fechaItemVacuna);

        nombreVacuna.setText(laVacuna.getNombre_vacuna());
        fecha.setText(laVacuna.getIps());
        return convertView;
    }
}
