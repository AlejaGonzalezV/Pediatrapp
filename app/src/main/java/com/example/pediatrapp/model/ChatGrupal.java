package com.example.pediatrapp.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ChatGrupal {

    private LocalDate fecha_creacion;
    private String id_padre;
    private ArrayList<String> pediatras;
    private ArrayList<Mensaje> mensajes;
    private String id;

    public ChatGrupal(LocalDate fecha_creacion, String id_padre, ArrayList<String> pediatras, ArrayList<Mensaje> mensajes, String id) {
        this.fecha_creacion = fecha_creacion;
        this.id_padre = id_padre;
        this.pediatras = pediatras;
        this.mensajes = mensajes;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChatGrupal() {
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getId_padre() {
        return id_padre;
    }

    public void setId_padre(String id_padre) {
        this.id_padre = id_padre;
    }

    public ArrayList<String> getPediatras() {
        return pediatras;
    }

    public void setPediatras(ArrayList<String> pediatras) {
        this.pediatras = pediatras;
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
