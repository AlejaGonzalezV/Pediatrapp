package com.example.pediatrapp.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.MessageListAdapter;
import com.example.pediatrapp.model.Chat;
import com.example.pediatrapp.model.FCMMessage;
import com.example.pediatrapp.model.Mensaje;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.services.FCMService;
import com.example.pediatrapp.utilities.HTTPSWebUtilDomi;
import com.example.pediatrapp.utilities.UtilDomi;
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
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;


import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static android.app.Activity.RESULT_OK;

public class MessageController implements View.OnClickListener{

    public static final int GALLERY_CALLBACK = 1;

    private MessageActivity activity;
    private FirebaseUser fuser;
    private DatabaseReference reference;
    private DatabaseReference ChatReference;
    private String chatroom;
    private Padre padre;
    private Pediatra pediatra;
    private MessageListAdapter adapter;
    private Uri tempUri;
    private Pediatra CurrentPed;
    private Padre CurrentPad;


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

                    storage.getReference().child("Doctor").child(pediatra.getFoto()).getDownloadUrl().addOnSuccessListener(
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

            ChatReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.e(">>>", "BuscaChatroom");

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                        Chat chat = snapshot.getValue(Chat.class);
                        if(chat != null) {
                            if (activity.getUserid().equals(chat.getId_padre()) && fuser.getUid().equals(chat.getId_pediatra())) {
                                Log.e(">>>", "Encuentra chatroom");
                                chatroom = snapshot.getKey();
                                loadMessages();
                            }
                        }

                    }

                    if(chatroom == null){


                        //CREAR SALA DE CHAT Y SUBIR A LA FIREBASE

                        DatabaseReference referenceCurrent = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(fuser.getUid());

                        referenceCurrent.addValueEventListener(new ValueEventListener() {
                                                                   @Override
                                                                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                       CurrentPed = dataSnapshot.getValue(Pediatra.class);

                                                                       String idc = FirebaseDatabase.getInstance().getReference().child("chat").push().getKey();
                                                                       Chat chat = new Chat(padre.getFoto(), CurrentPed.getFoto(), padre.getNombre(), CurrentPed.getNombre(), padre.getId(), CurrentPed.getId(), idc);

                                                                       FirebaseDatabase.getInstance().getReference().child("Padres").child(padre.getId()).child("chats").child(idc).setValue(idc);
                                                                       FirebaseDatabase.getInstance().getReference().child("Pediatras").child(CurrentPed.getId()).child("chats").child(idc).setValue(idc);
                                                                       FirebaseDatabase.getInstance().getReference().child("chat").child(idc).setValue(chat);
                                                                       chatroom = idc;

                                                                   }

                                                                   @Override
                                                                   public void onCancelled(@NonNull DatabaseError databaseError) {

                                                                   }
                                                               });


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }else {

            ChatReference = FirebaseDatabase.getInstance().getReference().child("chat");
            ChatReference.addValueEventListener(new ValueEventListener() {
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

                        DatabaseReference referenceCurrent = FirebaseDatabase.getInstance().getReference().child("Padres").child(fuser.getUid());

                        referenceCurrent.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                CurrentPad = dataSnapshot.getValue(Padre.class);

                                String idc = FirebaseDatabase.getInstance().getReference().child("chat").push().getKey();
                                Chat chat = new Chat(CurrentPad.getFoto(), pediatra.getFoto(), padre.getNombre(), pediatra.getNombre(), CurrentPad.getId(), pediatra.getId(), idc);

                                FirebaseDatabase.getInstance().getReference().child("Padres").child(CurrentPad.getId()).child("chats").child(idc).setValue(idc);
                                FirebaseDatabase.getInstance().getReference().child("Pediatras").child(pediatra.getId()).child("chats").child(idc).setValue(idc);
                                FirebaseDatabase.getInstance().getReference().child("chat").child(idc).setValue(chat);
                                chatroom = idc;

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

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
        //LO PRIMERO ES EL ID

        String idMensaje = FirebaseDatabase.getInstance().getReference().child("chat").child(chatroom).child("mensajes").push().getKey();

        Mensaje message = new Mensaje(
                tempUri == null ? Mensaje.TYPE_TEXT : Mensaje.TYPE_IMAGE,
                idMensaje, body, idUser, time);


        FCMMessage fcmMessage = new FCMMessage();
        fcmMessage.setTo("/topics/"+roomChat);
        fcmMessage.setData(message);
        Gson gson = new Gson();
        String json = gson.toJson(fcmMessage);

        new Thread(
                ()->{
                    HTTPSWebUtilDomi utilDomi = new HTTPSWebUtilDomi();
                    utilDomi.POSTtoFCM(FCMService.API_KEY,json);
                }
        ).start();

        if(tempUri != null){
            FirebaseStorage storage = FirebaseStorage.getInstance();
            storage.getReference().child("chats").child(message.getId()).putFile(tempUri).addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()){

                            //ENVIAR MENSAJE PONER RESTO O LO QUE SEA XDXD
                            FirebaseDatabase.getInstance().getReference().child("chat").child(chatroom).child("mensajes").child(idMensaje).setValue(message);
                        }
                    }
            );
        }else{
            //enviar mensaje sin guardar nada en el storage
            FirebaseDatabase.getInstance().getReference().child("chat").child(chatroom).child("mensajes").child(idMensaje).setValue(message);

        }

        activity.hideImage();
        tempUri = null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_send:
                String body = activity.getText_send().getText().toString();
                if(!body.equals("")){
                    sendMessage(body, chatroom, fuser.getUid(), Calendar.getInstance().getTime().getTime());
                    activity.getText_send().setText("");
                }
                Toast.makeText(activity.getApplicationContext(), "Escribe un mensaje", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_media:
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                activity.startActivityForResult(gallery, GALLERY_CALLBACK);

                break;

        }
    }

    public void beforeResume() {
        //MESSAGE CLOUD
        FirebaseMessaging.getInstance().subscribeToTopic(chatroom).addOnCompleteListener(
                task -> {
                    if(task.isSuccessful()){
                        Log.e(">>>", "Suscritooooo");
                    }
                }

        );
    }

    public void beforePause() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(chatroom);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_CALLBACK && resultCode == RESULT_OK){
            tempUri = data.getData();
            File file = new File(UtilDomi.getPath(activity, tempUri));
            Bitmap image = BitmapFactory.decodeFile(file.toString());
            activity.getMessageIV().setImageBitmap(image);
            activity.showImage();
        }
    }
}
