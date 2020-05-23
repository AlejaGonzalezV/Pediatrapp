package com.example.pediatrapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.model.Mensaje;
import com.example.pediatrapp.utilities.HTTPSWebUtilDomi;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.ArrayList;

public class MessageListAdapter extends BaseAdapter {

    private ArrayList<Mensaje> messages;
    private String userID = " ";

    public MessageListAdapter() {
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
            root = inflater.inflate(R.layout.message_item_others, null);
        }

        TextView message_row = root.findViewById(R.id.message_row);
        message_row.setText(messages.get(position).getBody());

        if(messages.get(position).getType() == Mensaje.TYPE_IMAGE){
            ImageView imageRow = root.findViewById(R.id.imageRow);
            imageRow.setVisibility(View.VISIBLE);

            String nameImage = messages.get(position).getId();

            File imageFile = new File(parent.getContext().getExternalFilesDir(null) + "/" + nameImage);

            if(imageFile.exists()){
                loadImage(imageRow, imageFile);

            }else {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                storage.getReference().child("chats").child(nameImage).getDownloadUrl().addOnSuccessListener(
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
