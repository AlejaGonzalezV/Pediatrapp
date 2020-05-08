package com.example.pediatrapp.model;

import java.util.ArrayList;

public class Pediatra {

    private String id;
    private String nombre;
    private String cedula;
    private String correo;
    private String contrasena;
    private String idV;
    private String firma;
    private String foto;
    private ArrayList<String> chats;
    private ArrayList<String> chats_grupales;
    private ArrayList<String> padres_asignados;

    public Pediatra(String id, String nombre, String cedula, String correo, String contrasena, String idV, String firma, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasena = contrasena;
        this.idV = idV;
        this.firma = firma;
        this.foto = foto;
        chats = new ArrayList<String>();
        chats_grupales = new ArrayList<String>();
        padres_asignados = new ArrayList<String>();
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

    public ArrayList<String> getChats() {
        return chats;
    }

    public void setChats(ArrayList<String> chats) {
        this.chats = chats;
    }

    public ArrayList<String> getChats_grupales() {
        return chats_grupales;
    }

    public void setChats_grupales(ArrayList<String> chats_grupales) {
        this.chats_grupales = chats_grupales;
    }

    public ArrayList<String> getPadres_asignados() {
        return padres_asignados;
    }

    public void setPadres_asignados(ArrayList<String> padres_asignados) {
        this.padres_asignados = padres_asignados;
    }

    public void addChat(String id){

        chats.add(id);

    }

    public void addChatGrupal(String id){

        chats_grupales.add(id);

    }

    public void addPadres(String id){

        padres_asignados.add(id);

    }
}
