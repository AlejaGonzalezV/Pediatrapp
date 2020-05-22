package com.example.pediatrapp.controller;

import android.util.Log;
import android.view.View;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.MessageListAdapter;
import com.example.pediatrapp.model.Chat;
import com.example.pediatrapp.model.Mensaje;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.view.MessageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Calendar;

public class MessageController implements View.OnClickListener{

    private MessageActivity activity;
    private FirebaseUser fuser;
    private DatabaseReference reference;
    private DatabaseReference ChatReference;
    private String chatroom;
    private Padre padre;
    private Pediatra pediatra;
    private MessageListAdapter adapter;

    public MessageController(MessageActivity activity) {
        this.activity = activity;
        activity.getBtn_send().setOnClickListener(this);
        activity.getBtn_media().setOnClickListener(this);

        adapter = new MessageListAdapter();
        activity.getMessage_list().setAdapter(adapter);

        if(activity.getType().equals("padre")){
            reference = FirebaseDatabase.getInstance().getReference().child("Padres").child(activity.getUserid());
        }else {
            reference = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(activity.getUserid());
        }

        FirebaseStorage storage = FirebaseStorage.getInstance();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(activity.getType().equals("padre")){
                    padre = dataSnapshot.getValue(Padre.class);
                    activity.getUsername().setText(padre.getNombre());

                    storage.getReference().child("Padre").child(padre.getFoto()).getDownloadUrl().addOnSuccessListener(
                            uri -> {
                                Glide.with(activity).load(uri).centerCrop().into(activity.getProfile_image());
                            }
                    );
                }else{
                    pediatra = dataSnapshot.getValue(Pediatra.class);
                    activity.getUsername().setText(pediatra.getNombre());

                    storage.getReference().child("Padre").child(pediatra.getFoto()).getDownloadUrl().addOnSuccessListener(
                            uri -> {
                                Glide.with(activity).load(uri).centerCrop().into(activity.getProfile_image());
                            }
                    );

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        adapter.setUserID(fuser.getUid());

        if(activity.getType().equals("padre")){

            ChatReference = FirebaseDatabase.getInstance().getReference().child("chat");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.e(">>>", "BuscaChatroom");

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        Chat chat = snapshot.getValue(Chat.class);
                        if(chat != null) {
                            if (activity.getUserid().equals(chat.getId_padre()) && fuser.getUid().equals(chat.getId_pediatra())) {
                                Log.e(">>>", "Encuentra chatroom");
                                chatroom = snapshot.getKey();
                            }
                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else {

            ChatReference = FirebaseDatabase.getInstance().getReference().child("chat");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.e(">>>", "BuscaChatroom");

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        Chat chat = snapshot.getValue(Chat.class);
                        if(chat != null) {
                            if (activity.getUserid().equals(chat.getId_pediatra()) && fuser.getUid().equals(chat.getId_padre())) {
                                Log.e(">>>", "Encuentra chatroom");
                                chatroom = snapshot.getKey();
                                loadMessages();
                            }
                        }
                    }

                    if(chatroom == null){

                        //CREAR SALA DE CHAT Y SUBIR A LA FIREBASE

                    }

                }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }



    }

    private void loadMessages() {
        // cambiar si se demora el limite
        Query query = FirebaseDatabase.getInstance().getReference().child("chat").child(chatroom).child("mensajes").limitToLast(150);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Mensaje menssage = dataSnapshot.getValue(Mensaje.class);
                if(menssage != null){
                    adapter.addMessage(menssage);
                    Log.e(">>>", menssage.getUser_id()+": "+ menssage.getBody());

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void sendMessage(String body, String roomChat, String idUser, long time){
        //video 11.5 min 15:18 ejemplo
        //Generar ID
        //MIRAR SESION QUE NO SE CIERRA

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_send:
                String body = activity.getText_send().getText().toString();
                if(!body.equals("")){
                    sendMessage(body, chatroom, fuser.getUid(), Calendar.getInstance().getTime().getTime());
                }
                activity.getText_send().setText("");
                break;
            case R.id.btn_media:
                Log.e(">>>", "boton mediaaaa");
                break;

        }
    }
}
