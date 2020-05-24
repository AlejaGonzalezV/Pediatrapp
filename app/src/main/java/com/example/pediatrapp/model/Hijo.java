package com.example.pediatrapp.model;

import java.util.Date;

public class Hijo {

    private String id;
    private String documento;
    private String nacimiento;
    private String sexo;
    private String nombre;
    private String edad;

    public Hijo(String id, String documento, String nacimiento, String sexo, String nombre, String edad) {
        this.id = id;
        this.documento = documento;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Hijo() {
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
