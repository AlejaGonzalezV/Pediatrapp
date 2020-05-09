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
import com.example.pediatrapp.model.Pediatra;

import java.util.List;

public class PadreAdapter_PediatraList extends RecyclerView.Adapter<PadreAdapter_PediatraList.ViewHolder> {

    private Context context;
    private List<Pediatra> pediatras;

    public PadreAdapter_PediatraList(Context context, List<Pediatra> pediatras) {
        this.context = context;
        this.pediatras = pediatras;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Pediatra> getPediatras() {
        return pediatras;
    }

    public void setPediatras(List<Pediatra> pediatras) {
        this.pediatras = pediatras;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.parentfragment_pediatraslistitem, parent, false);
        return new PadreAdapter_PediatraList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PadreAdapter_PediatraList.ViewHolder holder, int position) {

        Pediatra ped = pediatras.get(position);
        holder.nombrePediatraTV.setText(ped.getNombre());
        if(ped.getFoto().equals("default")){
            holder.imagePediatraIV.setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(context).load(ped.getFoto()).into(holder.imagePediatraIV);
        }
    }

    @Override
    public int getItemCount() {
        return pediatras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombrePediatraTV;
        ImageView imagePediatraIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombrePediatraTV = itemView.findViewById(R.id.nombrePediatraTV);
            imagePediatraIV = itemView.findViewById(R.id.imagePediatraIV);

         }
    }
}
