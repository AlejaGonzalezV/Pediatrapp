package com.example.pediatrapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pediatrapp.R;

import java.util.List;

public class ItemTablaCurvasAdpater extends BaseAdapter {


    private List<String> listaVacunas;
    private Context context;

    public ItemTablaCurvasAdpater(List<String> listaVacunas, Context context) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String laVacuna = (String) getItem(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.item_tabla_curvas, null, false);

       /* ImageView image = convertView.findViewById(R.id.imagenCacion1);*/
        return null;
    }
}
