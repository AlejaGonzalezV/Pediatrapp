package com.example.pediatrapp.model;

import java.time.LocalDate;
import java.util.HashMap;

public class Diagnostico {

    private String id;
    private String diagnostico;
    private String fecha;
    private String id_pediatra;
    private String nombre_pediatra;
    private String titulo;
    private HashMap<String, FormulaMedica> formula_medica;

    public Diagnostico(String id, String diagnostico, String fecha, String id_pediatra, String nombre_pediatra, String titulo, HashMap<String, FormulaMedica> formula_medica) {
        this.diagnostico = diagnostico;
        this.fecha = fecha;
        this.id_pediatra = id_pediatra;
        this.nombre_pediatra = nombre_pediatra;
        this.titulo = titulo;
        this.formula_medica = formula_medica;
        this.id = id;
    }

    public Diagnostico(String id, String diagnostico, String fecha, String id_pediatra, String nombre_pediatra, String titulo) {
        this.diagnostico = diagnostico;
        this.fecha = fecha;
        this.id_pediatra = id_pediatra;
        this.nombre_pediatra = nombre_pediatra;
        this.titulo = titulo;
        this.id = id;
    }

    public Diagnostico() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public HashMap<String, FormulaMedica> getFormula_medica() {
        return formula_medica;
    }

    public void setFormula_medica(HashMap<String, FormulaMedica> formula_medica) {
        this.formula_medica = formula_medica;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
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

}
