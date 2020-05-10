package com.example.pediatrapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.pediatrapp.view.MessageActivity;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class PadreAdapter_PediatraList extends RecyclerView.Adapter<PadreAdapter_PediatraList.ViewHol> {

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

    @Override
    public int getItemCount() {
        return pediatras.size();
    }

    public List<Pediatra> getPediatras() {
        return pediatras;
    }

    public void setPediatras(List<Pediatra> pediatras) {
        this.pediatras = pediatras;
    }

    @NonNull
    @Override
    public ViewHol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parentfragment_pediatraslistitem, parent, false);
        return new PadreAdapter_PediatraList.ViewHol(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHol holder, int position) {

        Log.e(">>>", "entra y añade");
        Pediatra ped = pediatras.get(position);
        holder.nombrePediatraTV.setText(ped.getNombre());

        FirebaseStorage storage = FirebaseStorage.getInstance();

        storage.getReference().child("Doctor").child(ped.getFoto()).getDownloadUrl().addOnSuccessListener(
                uri -> {
                    Glide.with(holder.itemView).load(uri).centerCrop().into(holder.imagePediatraIV);
                }
        );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userid", ped.getId());
                intent.putExtra("type", "pediatra");
                context.startActivity(intent);
            }
        });

    }


    public void addPediatra(Pediatra pediatra) {
        pediatras.add(pediatra);
        notifyDataSetChanged();
    }



    public class ViewHol extends RecyclerView.ViewHolder{

        TextView nombrePediatraTV;
        ImageView imagePediatraIV;

        public ViewHol(@NonNull View itemView) {
            super(itemView);
            nombrePediatraTV = itemView.findViewById(R.id.nombrePediatraTV);
            imagePediatraIV = itemView.findViewById(R.id.imagePediatraIV);

        }
    }
}
