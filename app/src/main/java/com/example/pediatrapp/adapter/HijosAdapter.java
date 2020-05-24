package com.example.pediatrapp.adapter;

import android.util.Log;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View row = inflater.inflate(R.layout.list_item_hijos, null, false);
        ImageView imagen = row.findViewById(R.id.fotoHijo);
        TextView nombre = row.findViewById(R.id.nombreHijoTV);
        nombre.setText(hijos.get(position).getNombre());

        Log.e("NOMBRE", hijos.get(position).getNombre());
        Log.e("GENERO", hijos.get(position).getSexo());

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
