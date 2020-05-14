package com.example.pediatrapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Vacuna;

import java.util.List;

public class ListaVacunasAdpater extends  RecyclerView.Adapter<ListaVacunasAdpater.ViewHolderListaVacunas>  {

    private List<Vacuna> listaVacunas;
    private Context context;

    public ListaVacunasAdpater(List<Vacuna> listaVacunas, Context context) {
        this.listaVacunas = listaVacunas;
        this.context = context;
    }

    public List<Vacuna> getListaVacunas() {
        return listaVacunas;
    }

    public void setListaVacunas(List<Vacuna> listaVacunas) {
        this.listaVacunas = listaVacunas;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ListaVacunasAdpater.ViewHolderListaVacunas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listvacuna, null, false);


        return new ViewHolderListaVacunas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaVacunasAdpater.ViewHolderListaVacunas holder, int position) {

        holder.nombreVacuna.setText(listaVacunas.get(position).getNombre_vacuna());
        holder.fecha.setText(listaVacunas.get(position).getIps());

    }

    @Override
    public int getItemCount() {
        return listaVacunas.size();
    }


    public class ViewHolderListaVacunas extends RecyclerView.ViewHolder {

        TextView nombreVacuna;
        TextView fecha;
        public ViewHolderListaVacunas(@NonNull View itemView) {
            super(itemView);

             nombreVacuna = itemView.findViewById(R.id.tv_nameVaccine);
             fecha = itemView.findViewById(R.id.fechaItemVacuna);
        }
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
