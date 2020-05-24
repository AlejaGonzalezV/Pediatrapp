package com.example.pediatrapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pediatrapp.R;
import com.example.pediatrapp.fragments.ListaHijosFragment;
import com.example.pediatrapp.model.Hijo;
import java.util.ArrayList;

public class HijosAdapter extends BaseAdapter {

    private ArrayList<Hijo> hijos;
    private ListaHijosFragment view;

    public HijosAdapter(ListaHijosFragment view) {

        hijos = new ArrayList<>();
        this.view = view;

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
        TextView edad = row.findViewById(R.id.edadHijoTV);
        nombre.setText(hijos.get(position).getNombre());

        if(hijos.get(position).getEdad() == "1"){

            edad.setText(hijos.get(position).getEdad()+ " año");

        } else {

            edad.setText(hijos.get(position).getEdad()+ " años"); 

        }


        Button verHijoBTN = row.findViewById(R.id.verHijoBTN);

        verHijoBTN.setOnClickListener(

                (v) -> {

                        view.recibo(position);

                }

        );

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
