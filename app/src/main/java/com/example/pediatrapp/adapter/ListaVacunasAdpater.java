package com.example.pediatrapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pediatrapp.R;

import java.util.List;

public class ListaVacunasAdpater extends BaseAdapter {

    private List<String> listaVacunas;
    private Context context;

    public ListaVacunasAdpater(List<String> listaVacunas, Context context) {
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


        String laVacuna = (String) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_listvacuna, null, false);

       /* ImageView image = convertView.findViewById(R.id.imagenCacion1);
        TextView nombreCancion = convertView.findViewById(R.id.nombreCacion);*/
        return convertView;
    }
}
