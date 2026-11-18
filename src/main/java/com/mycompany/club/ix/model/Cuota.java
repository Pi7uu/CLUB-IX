package com.mycompany.club.ix.model;

public class Cuota {
    private String periodo;
    private String estado;

    public Cuota() {
    }

    public Cuota(String periodo, String estado) {
        this.periodo = periodo;
        this.estado = estado;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
