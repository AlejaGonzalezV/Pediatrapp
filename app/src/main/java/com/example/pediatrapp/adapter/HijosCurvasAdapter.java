package com.example.pediatrapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Hijo;
import com.example.pediatrapp.view.CurvasGraficoActivity;
import com.example.pediatrapp.view.ListaVacunasActivity;

import java.util.List;

public class HijosCurvasAdapter extends  RecyclerView.Adapter<HijosCurvasAdapter.ViewHolderCurvasHijo>{


    private Context context;
    private View.OnClickListener listener;
    private List<Hijo> hijos;

    public HijosCurvasAdapter(Context context, List<Hijo> hijos) {
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
    public HijosCurvasAdapter.ViewHolderCurvasHijo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_hijos, null, false);

        return new HijosCurvasAdapter.ViewHolderCurvasHijo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HijosCurvasAdapter.ViewHolderCurvasHijo holder, int position) {

        holder.nombreHijoVa.setText(""+ hijos.get(position).getNombre());
        holder.edadHijoVa.setText("Edad: "+ "No hay edad hay que calcularla :v");

        if(hijos.get(position).getSexo().equals("Femenino")){

            holder.imagenHijava.setImageResource(R.drawable.girl);
        }else{

            holder.imagenHijava.setImageResource(R.drawable.boy);
        }
       // Glide.with(context).load(
             //   hijos.get(position).get
      //  ).centerCrop().into(holder.imagenHijava);

        holder.verVa.setOnClickListener(

                (v)->{

                    Intent intent = new Intent(context, CurvasGraficoActivity.class);
                    intent.putExtra("elnombre", holder.nombreHijoVa.getText().toString());
                    context.startActivity(intent);

                }
        );

    }

    @Override
    public int getItemCount() {
        return hijos.size();
    }




    public class  ViewHolderCurvasHijo extends RecyclerView.ViewHolder{

        TextView nombreHijoVa;
        TextView edadHijoVa;
        Button verVa;
        ImageView imagenHijava;


        public ViewHolderCurvasHijo(@NonNull View itemView) {
            super(itemView);

            nombreHijoVa = itemView.findViewById(R.id.nombreHijoTV);
            edadHijoVa = itemView.findViewById(R.id.edadHijoTV);
            verVa = itemView.findViewById(R.id.verHijoBTN);
            imagenHijava = itemView.findViewById(R.id.fotoHijo);
        }
    }
}
