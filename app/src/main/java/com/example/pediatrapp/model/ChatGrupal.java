package com.example.pediatrapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatGrupal {

    //CAMBIAR DURACIOOOOOOON
    public static final int DURACION = 2;
    public static final int ALARMA = 1;


    private long fecha_creacion;
    private String id_padre;
    private HashMap<String, String> pediatras;
    private HashMap<String, Mensaje> mensajes;
    private String nombre_padre;
    private String nombre;
    private String id;

    public ChatGrupal(long fecha_creacion, String id_padre, HashMap<String, String> pediatras, String nombre_padre, String nombre, String id) {
        this.fecha_creacion = fecha_creacion;
        this.id_padre = id_padre;
        this.pediatras = pediatras;
        this.nombre_padre = nombre_padre;
        this.nombre = nombre;
        this.id = id;
    }

    public ChatGrupal(long fecha_creacion, String id_padre, HashMap<String, String> pediatras, HashMap<String, Mensaje> mensajes, String nombre_padre, String nombre, String id) {
        this.fecha_creacion = fecha_creacion;
        this.id_padre = id_padre;
        this.pediatras = pediatras;
        this.mensajes = mensajes;
        this.nombre_padre = nombre_padre;
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre_padre() {
        return nombre_padre;
    }

    public void setNombre_padre(String nombre_padre) {
        this.nombre_padre = nombre_padre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChatGrupal() {
    }

    public long getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(long fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getId_padre() {
        return id_padre;
    }

    public void setId_padre(String id_padre) {
        this.id_padre = id_padre;
    }

    public HashMap<String, String> getPediatras() {
        return pediatras;
    }

    public void setPediatras(HashMap<String, String> pediatras) {
        this.pediatras = pediatras;
    }

    public HashMap<String, Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(HashMap<String, Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
