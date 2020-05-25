package com.example.pediatrapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Mensaje;
import com.example.pediatrapp.utilities.HTTPSWebUtilDomi;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.ArrayList;

public class MessageGListAdapter extends BaseAdapter {

    private ArrayList<Mensaje> messages;
    private String userID = " ";

    public MessageGListAdapter() {
        messages = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root;


        if(userID.equals(messages.get(position).getUser_id())){
            root = inflater.inflate(R.layout.message_item_mine, null);
        }else{
            root = inflater.inflate(R.layout.message_item_othersg, null);
            TextView nombre_send = root.findViewById(R.id.nombre_send);
        //    Log.e(">>>Nombre otri", messages.get(position).getNombre_usuario());
            nombre_send.setText(messages.get(position).getNombre_usuario());
        }

        TextView message_row = root.findViewById(R.id.message_row);
        TextView timeTV = root.findViewById(R.id.timeTV);

        message_row.setText(messages.get(position).getBody());
        timeTV.setText(messages.get(position).getTimestamp());



        if(messages.get(position).getType() == Mensaje.TYPE_IMAGE){
            ImageView imageRow = root.findViewById(R.id.imageRow);
            imageRow.setVisibility(View.VISIBLE);

            String nameImage = messages.get(position).getId();

            File imageFile = new File(parent.getContext().getExternalFilesDir(null) + "/" + nameImage);

            if(imageFile.exists()){
                loadImage(imageRow, imageFile);

            }else {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                storage.getReference().child("Chat_grupal").child(nameImage).getDownloadUrl().addOnSuccessListener(
                        uri -> {
                            File f = new File(parent.getContext().getExternalFilesDir(null) + "/" + nameImage);

                            new Thread(
                                    ()->{
                                        HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                                        utilDomi.saveURLImageOnFile(uri.toString(), f);

                                        root.post(
                                                ()->{
                                                    loadImage(imageRow, f);
                                                }
                                        );

                                    }
                            ).start();


                            //  Glide.with(parent).load(uri).centerCrop().into(imageRow);
                        }
                );
            }
        }

        return root ;
    }

    public void loadImage(ImageView imageView, File file){
        Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
        imageView.setImageBitmap(bitmap);

    }

    public void addMessage(Mensaje mensaje){
        messages.add(mensaje);
        notifyDataSetChanged();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
        notifyDataSetChanged();
    }




}
