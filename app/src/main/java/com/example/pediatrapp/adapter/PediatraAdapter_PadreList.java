package com.example.pediatrapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Padre;

import java.util.List;

public class PediatraAdapter_PadreList extends RecyclerView.Adapter<PediatraAdapter_PadreList.ViewHolder> {

    private Context context;
    private List<Padre> padres;

    public PediatraAdapter_PadreList(Context context, List<Padre> padres) {
        this.context = context;
        this.padres = padres;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Padre> getPadres() {
        return padres;
    }

    public void setPadres(List<Padre> padres) {
        this.padres = padres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.pediatrafragment_padrelistitem, parent, false);

        return new PediatraAdapter_PadreList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Padre padre = padres.get(position);
        holder.nombrePadreTV.setText(padre.getNombre());
        if(padre.getFoto().equals("default")){
            holder.imagePadreIV.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(context).load(padre.getFoto()).into(holder.imagePadreIV);
        }


    }

    @Override
    public int getItemCount() {
        return padres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombrePadreTV;
        ImageView imagePadreIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombrePadreTV = itemView.findViewById(R.id.nombrePadreTV);
            imagePadreIV = itemView.findViewById(R.id.imagePadreIV);
        }
    }
}
