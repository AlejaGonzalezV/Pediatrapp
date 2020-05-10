package com.example.pediatrapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Hijo;

import java.util.List;

public class HijosVacunasAdapter extends  RecyclerView.Adapter<HijosVacunasAdapter.ViewHolderVacunasHijo>  {


    private Context context;
    private View.OnClickListener listener;
    private List<Hijo> hijos;

    public HijosVacunasAdapter(Context context, List<Hijo> hijos) {
        this.context = context;
        this.hijos = hijos;

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Hijo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Hijo> hijos) {
        this.hijos = hijos;
    }

    @NonNull
    @Override
    public HijosVacunasAdapter.ViewHolderVacunasHijo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hijos_vacunas, null, false);

        return new ViewHolderVacunasHijo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HijosVacunasAdapter.ViewHolderVacunasHijo holder, int position) {

        holder.nombreHijoVa.setText(""+ hijos.get(position).getNombre());
        holder.edadHijoVa.setText(""+ hijos.get(position).getNacimiento());
        /*
        Glide.with(context).load(
             //   hijos.get(position).get
        ).centerCrop().into(holder.imagenHijava); */

        holder.verVa.setOnClickListener(

                (v)->{



                }
        );

    }

    @Override
    public int getItemCount() {
        return hijos.size();
    }




    public class  ViewHolderVacunasHijo extends RecyclerView.ViewHolder{

        TextView nombreHijoVa;
        TextView edadHijoVa;
        Button verVa;
        ImageView imagenHijava;


        public ViewHolderVacunasHijo(@NonNull View itemView) {
            super(itemView);

            nombreHijoVa = itemView.findViewById(R.id.nombreHijoVa);
            edadHijoVa = itemView.findViewById(R.id.edadHijoVa);
             verVa = itemView.findViewById(R.id.verVa);
            imagenHijava = itemView.findViewById(R.id.imageHijoVa);
        }
    }
}
