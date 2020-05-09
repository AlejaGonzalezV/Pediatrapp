package com.example.pediatrapp.model;

import java.util.ArrayList;

public class HistoriaClinica {

    private String id_hijo;
    private ArrayList<Diagnostico> diagnosticos;
    private ArrayList<FormulaMedica> formulaMedicas;

    public HistoriaClinica(String id_hijo, ArrayList<Diagnostico> diagnosticos, ArrayList<FormulaMedica> formulaMedicas) {
        this.id_hijo = id_hijo;
        this.diagnosticos = diagnosticos;
        this.formulaMedicas = formulaMedicas;
    }

    public HistoriaClinica() {
    }

    public String getId_hijo() {
        return id_hijo;
    }

    public void setId_hijo(String id_hijo) {
        this.id_hijo = id_hijo;
    }

    public ArrayList<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(ArrayList<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public ArrayList<FormulaMedica> getFormulaMedicas() {
        return formulaMedicas;
    }

    public void setFormulaMedicas(ArrayList<FormulaMedica> formulaMedicas) {
        this.formulaMedicas = formulaMedicas;
    }
}
