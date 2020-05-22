package com.example.pediatrapp.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Chat {

    private String foto_padre;
    private String foto_pediatra;
    private String nombre_padre;
    private String nombre_pediatra;
    private String id_padre;
    private String id_pediatra;
    private HashMap<String, Mensaje> mensajes;
    private String id;

    public Chat(String foto_padre, String foto_pediatra, String nombre_padre, String nombre_pediatra, String id_padre, String id_pediatra, String id) {
        this.foto_padre = foto_padre;
        this.foto_pediatra = foto_pediatra;
        this.nombre_padre = nombre_padre;
        this.nombre_pediatra = nombre_pediatra;
        this.id_padre = id_padre;
        this.id_pediatra = id_pediatra;
        this.id = id;
    }

    public Chat() {
    }

    public String getFoto_padre() {
        return foto_padre;
    }

    public void setFoto_padre(String foto_padre) {
        this.foto_padre = foto_padre;
    }

    public String getFoto_pediatra() {
        return foto_pediatra;
    }

    public void setFoto_pediatra(String foto_pediatra) {
        this.foto_pediatra = foto_pediatra;
    }

    public String getNombre_padre() {
        return nombre_padre;
    }

    public void setNombre_padre(String nombre_padre) {
        this.nombre_padre = nombre_padre;
    }

    public String getNombre_pediatra() {
        return nombre_pediatra;
    }

    public void setNombre_pediatra(String nombre_pediatra) {
        this.nombre_pediatra = nombre_pediatra;
    }

    public String getId_padre() {
        return id_padre;
    }

    public void setId_padre(String id_padre) {
        this.id_padre = id_padre;
    }

    public String getId_pediatra() {
        return id_pediatra;
    }

    public void setId_pediatra(String id_pediatra) {
        this.id_pediatra = id_pediatra;
    }

    public HashMap<String, Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(HashMap<String, Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addMensaje(String key, Mensaje msj){

        mensajes.put(key,msj);

    }
}
