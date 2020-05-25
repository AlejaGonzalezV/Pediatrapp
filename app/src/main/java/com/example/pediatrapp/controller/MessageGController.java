package com.example.pediatrapp.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pediatrapp.R;
import com.example.pediatrapp.adapter.MessageGListAdapter;
import com.example.pediatrapp.model.FCMMessage;
import com.example.pediatrapp.model.Mensaje;
import com.example.pediatrapp.model.Padre;
import com.example.pediatrapp.model.Pediatra;
import com.example.pediatrapp.services.FCMService;
import com.example.pediatrapp.utilities.HTTPSWebUtilDomi;
import com.example.pediatrapp.utilities.UtilDomi;
import com.example.pediatrapp.view.MessageGroupActivity;
import com.example.pediatrapp.view.ParentPerfil;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class MessageGController implements View.OnClickListener{

    public static final int GALLERY_CALLBACK = 1;

    private MessageGroupActivity activity;
    private FirebaseUser fuser;
    private DatabaseReference reference;
    private DatabaseReference ChatReference;
    private Uri tempUri;
    private Pediatra CurrentPed;
    private Padre CurrentPad;
    private MessageGListAdapter adapter;


    public MessageGController(MessageGroupActivity activity) {
        this.activity = activity;

        Log.e(">>>>", "CONTROLLERGGGG");
        activity.getBtn_send().setOnClickListener(this);
        activity.getBtn_media().setOnClickListener(this);
        if(activity.getType().equals("pediatra")){
            activity.getProfile_image().setOnClickListener(this);
        }

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        loadCurrentUser();
        adapter = new MessageGListAdapter();
        activity.getMessage_list().setAdapter(adapter);
        adapter.setUserID(fuser.getUid());

        activity.getUsername().setText(activity.getNombre_Chat());
        activity.getProfile_image().setImageResource(R.drawable.chatgrupal);

        loadMessages();

    }

    private void loadCurrentUser() {

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference  reference;
        Log.e(">>>", firebaseUser.getUid());
        if(activity.getType().equals("pediatra")){
            reference = FirebaseDatabase.getInstance().getReference().child("Pediatras").child(firebaseUser.getUid());
        }else{
            reference = FirebaseDatabase.getInstance().getReference().child("Padres").child(firebaseUser.getUid());
        }


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(activity.getType().equals("pediatra")){

                    CurrentPed = dataSnapshot.getValue(Pediatra.class);

                }else{
                    CurrentPad = dataSnapshot.getValue(Padre.class);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void loadMessages() {
        // cambiar si se demora el limite
        Query query = FirebaseDatabase.getInstance().getReference().child("Chat_grupal").child(activity.getChatGrup()).child("mensajes").limitToLast(150);

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

    public void sendMessage(String body, String roomChat, String idUser, String time){

        String idMensaje = FirebaseDatabase.getInstance().getReference().child("Chat_grupal").child(roomChat).child("mensajes").push().getKey();

        String nombre = "";
        if(activity.getType().equals("pediatra")){
            nombre = CurrentPed.getNombre();
        }else{
            nombre = CurrentPad.getNombre();
        }


        Mensaje message = new Mensaje(tempUri == null ? Mensaje.TYPE_TEXT : Mensaje.TYPE_IMAGE,
                idMensaje, body,nombre, idUser, time);


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
            storage.getReference().child("Chat_grupal").child(message.getId()).putFile(tempUri).addOnCompleteListener(
                    task -> {
                        if(task.isSuccessful()){

                            //ENVIAR MENSAJE PONER RESTO O LO QUE SEA XDXD
                            Log.e(">>>", "Envio imagen");
                            FirebaseDatabase.getInstance().getReference().child("Chat_grupal").child(activity.getChatGrup()).child("mensajes").child(idMensaje).setValue(message);
                        }
                    }
            );
        }else{
            //enviar mensaje sin guardar nada en el storage
            FirebaseDatabase.getInstance().getReference().child("Chat_grupal").child(activity.getChatGrup()).child("mensajes").child(idMensaje).setValue(message);

        }

        activity.hideImage();
        tempUri = null;
    }



    public void beforePause() {
        FirebaseMessaging.getInstance().subscribeToTopic(activity.getChatGrup()).addOnCompleteListener(
                task -> {
                    if(task.isSuccessful()){
                        Log.e(">>>", "Suscritooooo");
                    }
                }

        );
    }

    public void beforeResume() {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(activity.getChatGrup());
        Log.e(">>>", "no suscrito");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == GALLERY_CALLBACK && resultCode == RESULT_OK){
            tempUri = data.getData();
            Log.e(">>>", "Carga imagen");
            File file = new File(UtilDomi.getPath(activity, tempUri));
            Bitmap image = BitmapFactory.decodeFile(file.toString());
            activity.getMessageIV().setImageBitmap(image);
            activity.showImage();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_send:
                String body = activity.getText_send().getText().toString();
                if(!body.equals("")){

                    Date da = new Date(System.currentTimeMillis());

                    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

                    String[] split = hourFormat.format(da).split(":");

                    String data = split[0]+":"+split[1];

                    sendMessage(body, activity.getChatGrup(), fuser.getUid(), data);
                    activity.getText_send().setText("");
                }else {
                    Toast.makeText(activity.getApplicationContext(), "Escribe un mensaje", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_media:
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                activity.startActivityForResult(gallery, GALLERY_CALLBACK);
                break;
            case R.id.profile_image:
                    Intent intent = new Intent(activity, ParentPerfil.class);
                    intent.putExtra("userid", activity.getIdPadre());
                    Log.e(">>>", "inicioIntent");
                    activity.startActivity(intent);
                break;

        }
    }
}
