package com.example.pediatrapp.model;

import java.util.HashMap;

public class Padre {

    private String id;
    private String cedula;
    private String nombre;
    private String correo;
    private String contrasena;
    private String direccion;
    private String telefono;
    private String foto;
    private String chat_grupal_id;
    private String pediatraTemp;
    private HashMap<String, String> pediatras_asig;
    private HashMap<String, Hijo> hijos;
    private HashMap<String, String> chats;

    public Padre(String id, String cedula, String nombre, String correo, String contrasena, String direccion,
                 String telefono, String foto, HashMap<String, String> pediatras_asig, HashMap<String, Hijo> hijos) {

        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.telefono = telefono;
        this.foto = foto;
        this.pediatras_asig = pediatras_asig;
        this.hijos = hijos;
    }

    public Padre() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getChat_grupal_id() {
        return chat_grupal_id;
    }

    public void setChat_grupal_id(String chat_grupal_id) {
        this.chat_grupal_id = chat_grupal_id;
    }

    public String getPediatraTemp() {
        return pediatraTemp;
    }

    public void setPediatraTemp(String pediatraTemp) {
        this.pediatraTemp = pediatraTemp;
    }

    public HashMap<String, String> getPediatras_asig() {
        return pediatras_asig;
    }

    public void setPediatras_asig(HashMap<String, String> pediatras_asig) {
        this.pediatras_asig = pediatras_asig;
    }

    public HashMap<String, Hijo> getHijos() {
        return hijos;
    }

    public void setHijos(HashMap<String, Hijo> hijos) {
        this.hijos = hijos;
    }

    public HashMap<String, String> getChats() {
        return chats;
    }

    public void setChats(HashMap<String, String> chats) {
        this.chats = chats;
    }
}
