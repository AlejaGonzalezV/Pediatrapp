package com.example.pediatrapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.pagercontroller.Hijos;

import java.util.List;

public class HijosListaPerfilAdapater extends BaseAdapter {

    Context context;
    List<Hijos> listaHijos;

    public HijosListaPerfilAdapater(Context context, List<Hijos> listaHijos) {
        this.context = context;
        this.listaHijos = listaHijos;
    }

    @Override
    public int getCount() {
        return listaHijos.size();  //retorna la cantidad de hijos de la lista
    }

    @Override
    public Object getItem(int position) {
        return listaHijos.get(position); //retorna el bjeto de la posición
    }

    @Override
    public long getItemId(int position) {
        return listaHijos.get(position).getId(); //retorna el id de la posición indicada
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflate = LayoutInflater.from(context);
        view =  inflate.inflate(R.layout.list_item_hijos,null);

        TextView nombreHijo =  view.findViewById(R.id.nombreHijoTV);
        Button verHijo =  view.findViewById(R.id.verHijoBTN);
        CardView cardViewHijos = view.findViewById(R.id.cardViewHijos);

       nombreHijo.setText(listaHijos.get(position).getNombreHijo().toString());

        return view;

    }
}
