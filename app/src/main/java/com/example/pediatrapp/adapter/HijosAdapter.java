package com.example.pediatrapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Hijo;
import java.util.ArrayList;

public class HijosAdapter extends BaseAdapter {

    private ArrayList<Hijo> hijos;

    public HijosAdapter() {

        hijos = new ArrayList<>();

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.list_item_hijos, null, false);
        ImageView imagen = row.findViewById(R.id.fotoHijo);
        TextView nombre = row.findViewById(R.id.nombreHijoTV);

        nombre.setText(hijos.get(position).getNombre());
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

    }
}
