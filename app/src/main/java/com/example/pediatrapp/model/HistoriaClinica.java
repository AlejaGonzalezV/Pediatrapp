package com.example.pediatrapp.model;

import java.util.HashMap;

public class HistoriaClinica {

    //String es id del hijo
    private HashMap<String, Diagnostico> diagnosticos;

    public HistoriaClinica(HashMap<String, Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public HistoriaClinica() {
    }

    public HashMap<String, Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(HashMap<String, Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

}
