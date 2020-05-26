package com.example.pediatrapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.DatosCurva;
import com.example.pediatrapp.view.VerCurvaActivity;

import java.util.List;

public class ItemTablaCurvasAdpater extends RecyclerView.Adapter<ItemTablaCurvasAdpater.ViewHolderTablaCurva> {


    private List<DatosCurva> listaCurvas;
    private Context context;

    public ItemTablaCurvasAdpater(List<DatosCurva> listaVacunas, Context context) {
        this.listaCurvas = listaVacunas;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemTablaCurvasAdpater.ViewHolderTablaCurva onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tabla_curvas, null, false);

        return new ItemTablaCurvasAdpater.ViewHolderTablaCurva(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemTablaCurvasAdpater.ViewHolderTablaCurva holder, int position) {

        holder.fecha.setText(""+listaCurvas.get(position).getPeso());
        holder.numeral.setText("(" + (position+1) +")");

        holder.verCurva.setOnClickListener(

                (v)->{

                    DatosCurva curva = listaCurvas.get(position);
                    Intent intent = new Intent(context, VerCurvaActivity.class);
                    intent.putExtra("verCurva", curva);
                    context.startActivity(intent);

                }

        );


    }

    @Override
    public int getItemCount() {
        return listaCurvas.size();
    }

    public class ViewHolderTablaCurva extends RecyclerView.ViewHolder {


        TextView numeral;
        TextView fecha;
        Button verCurva;

        public ViewHolderTablaCurva(@NonNull View itemView) {
            super(itemView);

            fecha = itemView.findViewById(R.id.pesoTablaTv);
            verCurva = itemView.findViewById(R.id.verListaCur);
            numeral =  itemView.findViewById(R.id.numeralTv);

        }
    }

}
