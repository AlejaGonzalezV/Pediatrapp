package com.example.pediatrapp.model;

import java.time.LocalDate;

public class Diagnostico {

    private String diagnostico;
    private LocalDate fecha;
    private String id_pediatra;
    private String nombre_pediatra;

    public Diagnostico(String diagnostico, LocalDate fecha, String id_pediatra, String nombre_pediatra) {
        this.diagnostico = diagnostico;
        this.fecha = fecha;
        this.id_pediatra = id_pediatra;
        this.nombre_pediatra = nombre_pediatra;
    }

    public Diagnostico() {
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getId_pediatra() {
        return id_pediatra;
    }

    public void setId_pediatra(String id_pediatra) {
        this.id_pediatra = id_pediatra;
    }

    public String getNombre_pediatra() {
        return nombre_pediatra;
    }

    public void setNombre_pediatra(String nombre_pediatra) {
        this.nombre_pediatra = nombre_pediatra;
    }

}
