package com.example.pediatrapp.model;

import java.util.Date;

public class Hijo {

    private String id;
    private String documento;
    private Date nacimiento;
    private String sexo;

    public Hijo(String id, String documento, Date nacimiento, String sexo) {
        this.id = id;
        this.documento = documento;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
