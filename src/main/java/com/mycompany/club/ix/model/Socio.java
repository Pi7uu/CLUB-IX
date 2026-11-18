package com.mycompany.club.ix.model;

import java.util.ArrayList;
import java.util.List;

public class Socio {
    private String nombre;
    private String dni;
    private String estado;
    private String disciplinas;
    private List<Cuota> cuotas;

    public Socio() {
        this.cuotas = new ArrayList<>();
    }

    public Socio(String nombre, String dni, String estado, String disciplinas) {
        this.nombre = nombre;
        this.dni = dni;
        this.estado = estado;
        this.disciplinas = disciplinas;
        this.cuotas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(String disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public void agregarCuota(Cuota cuota) {
        this.cuotas.add(cuota);
    }

    @Override
    public String toString() {
        return nombre + " (" + dni + ")";
    }
}
