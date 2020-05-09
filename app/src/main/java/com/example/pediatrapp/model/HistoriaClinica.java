package com.example.pediatrapp.model;

import java.util.HashMap;

public class HistoriaClinica {

    private String id_hijo;
    private HashMap<String, Diagnostico> diagnosticos;
    private HashMap<String, FormulaMedica> formulaMedicas;

    public HistoriaClinica(String id_hijo, HashMap<String, Diagnostico> diagnosticos, HashMap<String, FormulaMedica> formulaMedicas) {
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

    public HashMap<String, Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(HashMap<String, Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public HashMap<String, FormulaMedica> getFormulaMedicas() {
        return formulaMedicas;
    }

    public void setFormulaMedicas(HashMap<String, FormulaMedica> formulaMedicas) {
        this.formulaMedicas = formulaMedicas;
    }
}
