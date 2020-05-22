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
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.view.MessageActivity;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PediatraAdapter_PadreList extends RecyclerView.Adapter<PediatraAdapter_PadreList.ViewHol> {

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

    @Override
    public int getItemCount() {
        return padres.size();
    }

    public List<Padre> getPadres() {
        return padres;
    }

    public void addPadre(Padre padre){
        padres.add(padre);
        notifyDataSetChanged();
    }

    public void setPadres(List<Padre> padres) {
        this.padres = padres;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHol onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pediatrafragment_padrelistitem, parent, false);

        return new PediatraAdapter_PadreList.ViewHol(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PediatraAdapter_PadreList.ViewHol holder, int position) {

        Log.e(">>>", "entra y añade");
        Padre padre = padres.get(position);
        holder.nombrePadreTV.setText(padre.getNombre());

        FirebaseStorage storage = FirebaseStorage.getInstance();

        storage.getReference().child("Padre").child(padre.getFoto()).getDownloadUrl().addOnSuccessListener(
                uri -> {
                    Glide.with(holder.itemView).load(uri).centerCrop().into(holder.imagePadreIV);
                }
        );


//        if(padre.getFoto().equals("default")){
//            holder.imagePadreIV.setImageResource(R.mipmap.ic_launcher);
//        }else{

//        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userid", padre.getId());
                intent.putExtra("type", "padre");
                context.startActivity(intent);
            }
        });

    }


    public class ViewHol extends RecyclerView.ViewHolder{

        TextView nombrePadreTV;
        CircleImageView imagePadreIV;

        public ViewHol(@NonNull View itemView) {
            super(itemView);

            nombrePadreTV = itemView.findViewById(R.id.nombrePadreTV);
            imagePadreIV = itemView.findViewById(R.id.imagePadreIV);
        }
    }
}
