package com.example.pediatrapp.model;

import java.time.LocalDate;


public class DatosCurva {

    private LocalDate fecha;
    private int medida_cabeza;
    private int peso;
    private int talla;

    public DatosCurva(LocalDate fecha, int medida_cabeza, int peso, int talla) {
        this.fecha = fecha;
        this.medida_cabeza = medida_cabeza;
        this.peso = peso;
        this.talla = talla;
    }

    public DatosCurva() {
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
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
