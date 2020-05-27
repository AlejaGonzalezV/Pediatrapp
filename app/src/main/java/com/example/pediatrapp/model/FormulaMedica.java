package com.example.pediatrapp.model;

import java.time.LocalDate;

public class FormulaMedica {

    private String posologia;
    private String fecha;
    private String id_pediatra;
    private String nombre_pediatra;
    private String firma_pediatra;

    public FormulaMedica(String posologia, String fecha, String id_pediatra, String nombre_pediatra, String firma_pediatra) {
        this.posologia = posologia;
        this.fecha = fecha;
        this.id_pediatra = id_pediatra;
        this.nombre_pediatra = nombre_pediatra;
        this.firma_pediatra = firma_pediatra;
    }

    public FormulaMedica() {
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public String getFirma_pediatra() {
        return firma_pediatra;
    }

    public void setFirma_pediatra(String firma_pediatra) {
        this.firma_pediatra = firma_pediatra;
    }
}
