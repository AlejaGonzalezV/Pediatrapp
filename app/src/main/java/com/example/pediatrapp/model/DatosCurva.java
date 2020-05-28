package com.example.pediatrapp.model;

import java.io.Serializable;



public class DatosCurva implements Serializable {

    public static final String MESES = "Meses";
    public static final String AÑOS = "Años";

    private String id;
    private String fecha;
    private int medida_cabeza;
    private int peso;
    private int talla;
    private int edad;
    private String edadComplemento;

    public DatosCurva(String id, String fecha, int medida_cabeza, int peso, int talla, int edad, String edadComplemento) {
        this.id = id;
        this.fecha = fecha;
        this.medida_cabeza = medida_cabeza;
        this.peso = peso;
        this.talla = talla;
        this.edad = edad;
        this.edadComplemento = edadComplemento;
    }

    public DatosCurva() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMedida_cabeza() {
        return medida_cabeza;
    }

    public void setMedida_cabeza(int medida_cabeza) {
        this.medida_cabeza = medida_cabeza;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public String getEdadComplemento() {
        return edadComplemento;
    }

    public void setEdadComplemento(String edadComplemento) {
        this.edadComplemento = edadComplemento;
    }
}
