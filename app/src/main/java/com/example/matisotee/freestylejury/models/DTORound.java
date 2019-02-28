package com.example.matisotee.freestylejury.models;

public class DTORound {

    private String modalidad;
    private String tiempo;
    private int posicionModalidad;
    private int posicionTiempo;

    public DTORound(String modalidad, String tiempo) {
        this.modalidad = modalidad;
        this.tiempo = tiempo;
    }

    public DTORound(String modalidad, String tiempo, int posicionModalidad, int posicionTiempo) {
        this.modalidad = modalidad;
        this.tiempo = tiempo;
        this.posicionModalidad = posicionModalidad;
        this.posicionTiempo = posicionTiempo;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public int getPosicionModalidad() {
        return posicionModalidad;
    }

    public void setPosicionModalidad(int posicionModalidad) {
        this.posicionModalidad = posicionModalidad;
    }

    public int getPosicionTiempo() {
        return posicionTiempo;
    }

    public void setPosicionTiempo(int posicionTiempo) {
        this.posicionTiempo = posicionTiempo;
    }
}
