package com.example.pediatrapp.model;

import com.example.pediatrapp.fragments.ParentRegisterFragment;

public class Mensaje {

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_IMAGE = 1;

    private int type;
    private String id;
    private String body;
    private String nombre_usuario;
    private String user_id;
    private long timestamp;

    public Mensaje(int type, String id, String body, String user_id, long timestamp) {
        this.type = type;
        this.id = id;
        this.body = body;
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    public Mensaje(int type, String id, String body, String nombre_usuario, String user_id, long timestamp) {
        this.type = type;
        this.id = id;
        this.body = body;
        this.nombre_usuario = nombre_usuario;
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    public Mensaje() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
