package com.example.pediatrapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Mensaje;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.utilities.HTTPSWebUtilDomi;
import com.example.pediatrapp.view.MessageActivity;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PediatraAdapter_ChatList extends BaseAdapter{

    private ArrayList<Padre> padres;

    public PediatraAdapter_ChatList() {
        this.padres = new ArrayList<>();
    }

    public ArrayList<Padre> getPadres() {
        return padres;
    }

    public void setPadres(ArrayList<Padre> padres) {
        this.padres = padres;
        notifyDataSetChanged();
    }

    public void addPadre(Padre padre){
        padres.add(padre);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return padres.size();
    }

    @Override
    public Object getItem(int position) {
        return padres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.pediatrafragment_chatlistitem, null);

        Padre padre = padres.get(position);

        TextView nombrePadreTV = root.findViewById(R.id.nombrePadreTV);
        nombrePadreTV.setText(padre.getNombre());
        CircleImageView imagePadreCIV = root.findViewById(R.id.imagePadreCIV);


            String nameImage = padre.getFoto();

            File imageFile = new File(parent.getContext().getExternalFilesDir(null) + "/" + nameImage);

            if(imageFile.exists()){
                loadImage(imagePadreCIV, imageFile);

            }else {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                storage.getReference().child("Padre").child(nameImage).getDownloadUrl().addOnSuccessListener(
                        uri -> {
                            File f = new File(parent.getContext().getExternalFilesDir(null) + "/" + nameImage);

                            new Thread(
                                    ()->{
                                        HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                                        utilDomi.saveURLImageOnFile(uri.toString(), f);

                                        root.post(
                                                ()->{
                                                    loadImage(imagePadreCIV, f);
                                                }
                                        );

                                    }
                            ).start();

                        }
                );
            }


        return root ;
    }

    public void loadImage(ImageView imageView, File file){
        Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
        imageView.setImageBitmap(bitmap);

    }


}
