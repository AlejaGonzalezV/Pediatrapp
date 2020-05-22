package com.example.pediatrapp.model;

import java.io.Serializable;



public class DatosCurva implements Serializable {

    private String fecha;
    private String edad;
    private int medida_cabeza;
    private int peso;
    private int talla;


    public DatosCurva(String fecha, int medida_cabeza, int peso, int talla, String edad) {
        this.fecha = fecha;
        this.medida_cabeza = medida_cabeza;
        this.peso = peso;
        this.talla = talla;
        this.edad = edad;
    }

    public DatosCurva() {
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
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
}
