package com.example.pediatrapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.utilities.HTTPSWebUtilDomi;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PadreAdapter_ChatList extends BaseAdapter {

    private ArrayList<Pediatra> pediatras;

    public PadreAdapter_ChatList() {
        this.pediatras = new ArrayList<>();
    }

    public ArrayList<Pediatra> getPediatras() {
        return pediatras;
    }

    public void setPediatras(ArrayList<Pediatra> pediatras) {
        this.pediatras = pediatras;
        notifyDataSetChanged();
    }

    public void addPediatra(Pediatra pediatra){
        pediatras.add(pediatra);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pediatras.size();
    }

    @Override
    public Object getItem(int position) {
        return pediatras.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.parentfragment_chatlistitem, null);

        Pediatra pediatra = pediatras.get(position);


        TextView nombrePediatraTV = root.findViewById(R.id.nombrePediatraTV);
        nombrePediatraTV.setText(pediatra.getNombre());
        CircleImageView imagePediatraCIV = root.findViewById(R.id.imagePediatraCIV);


        String nameImage = pediatra.getFoto();

        File imageFile = new File(parent.getContext().getExternalFilesDir(null) + "/" + nameImage);

        if(imageFile.exists()){
            loadImage(imagePediatraCIV, imageFile);

        }else {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            storage.getReference().child("Doctor").child(nameImage).getDownloadUrl().addOnSuccessListener(
                    uri -> {
                        File f = new File(parent.getContext().getExternalFilesDir(null) + "/" + nameImage);

                        new Thread(
                                ()->{
                                    HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                                    utilDomi.saveURLImageOnFile(uri.toString(), f);

                                    root.post(
                                            ()->{
                                                loadImage(imagePediatraCIV, f);
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
