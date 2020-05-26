package com.example.pediatrapp.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Vacuna implements Serializable {

    private String dosis;
    private String edad_aplicacion;
    private String ips;
    private String nombre_aplicador;
    private String nombre_vacuna;
    private String fecha_aplicacion;
    private String labortorio;
    private String lote;
    private String id;

    public Vacuna(String id, String dosis, String edad_aplicacion, String ips, String nombre_aplicador, String nombre_vacuna, String fecha_aplicacion, String laboratrio, String lote) {
        this.id = id;
        this.dosis = dosis;
        this.edad_aplicacion = edad_aplicacion;
        this.ips = ips;
        this.nombre_aplicador = nombre_aplicador;
        this.nombre_vacuna = nombre_vacuna;
        this.fecha_aplicacion = fecha_aplicacion;
        this.labortorio = laboratrio;
        this.lote = lote;
    }

    public Vacuna() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getEdad_aplicacion() {
        return edad_aplicacion;
    }

    public void setEdad_aplicacion(String edad_aplicacion) {
        this.edad_aplicacion = edad_aplicacion;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public String getNombre_aplicador() {
        return nombre_aplicador;
    }

    public void setNombre_aplicador(String nombre_aplicador) {
        this.nombre_aplicador = nombre_aplicador;
    }

    public String getNombre_vacuna() {
        return nombre_vacuna;
    }

    public void setNombre_vacuna(String nombre_vacuna) {
        this.nombre_vacuna = nombre_vacuna;
    }

    public String getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(String fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getLabortorio() {
        return labortorio;
    }

    public void setLabortorio(String labortorio) {
        this.labortorio = labortorio;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
}
