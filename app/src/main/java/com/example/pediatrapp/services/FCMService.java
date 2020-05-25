package com.example.pediatrapp.services;

import com.example.pediatrapp.model.Mensaje;
import com.example.pediatrapp.utilities.NotificationUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.json.JSONObject;

public class FCMService extends FirebaseMessagingService {

    public static final String API_KEY = "AAAAm-xVqAQ:APA91bHUAdMi7Jf0Ig1Ueu4_NK_IXzj0N07KQW6_jUJqeEUd0SxPC5OI7SuyOEeqbL-X0MEqUdhZ_PCipbJ1YuPkiEBJ-6MZp6CuZVzZ_BWsL5Fl5XTft8lZ_nWiNzRuYZXbmu3IuzjB";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        JSONObject object = new JSONObject(remoteMessage.getData());
        Gson gson = new Gson();
        Mensaje mensaje = gson.fromJson(object.toString(), Mensaje.class);
        if(mensaje.getNombre_usuario() != null){
            NotificationUtils.createNotificationG(this, mensaje.getNombre_usuario()+": "+mensaje.getBody());
        }else{
            NotificationUtils.createNotification(this, mensaje.getBody());
        }

    }
}
