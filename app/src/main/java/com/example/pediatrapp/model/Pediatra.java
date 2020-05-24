package com.example.pediatrapp.model;

import java.util.HashMap;

public class Pediatra {

    private String id;
    private String nombre;
    private String cedula;
    private String correo;
    private String contrasena;
    private String idV;
    private String firma;
    private String foto;
    private String estado;
    private HashMap<String, String> chats;
    private HashMap<String, String> chats_grupales;
    private HashMap<String, String> padres_asignados;

    public Pediatra(String id, String nombre, String cedula, String correo, String contrasena,
                    String idV, String firma, String foto, HashMap<String, String> chats,
                    HashMap<String, String> chats_grupales, HashMap<String, String> padres_asignados, String estado) {

        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idV = idV;
        this.firma = firma;
        this.foto = foto;
        this.chats = chats;
        this.chats_grupales = chats_grupales;
        this.padres_asignados = padres_asignados;
        this.estado = estado;
    }

    public Pediatra(String id, String nombre, String cedula, String correo, String contrasena, String idV, String firma, String foto, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idV = idV;
        this.firma = firma;
        this.foto = foto;
        this.estado = estado;
    }

    public Pediatra() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIdV() {
        return idV;
    }

    public void setIdV(String idV) {
        this.idV = idV;
    }

}
