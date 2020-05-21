package com.example.pediatrapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.DatosCurva;

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

    }

    @Override
    public int getItemCount() {
        return listaCurvas.size();
    }

    public class ViewHolderTablaCurva extends RecyclerView.ViewHolder {
        public ViewHolderTablaCurva(@NonNull View itemView) {
            super(itemView);
        }
    }


    //convertView = LayoutInflater.from(context).inflate(R.layout.item_tabla_curvas, null, false);
}
