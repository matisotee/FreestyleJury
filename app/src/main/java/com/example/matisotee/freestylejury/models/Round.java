package com.example.matisotee.freestylejury.models;

import java.io.Serializable;
import java.util.List;

public class Round implements Serializable{


    //public final static int MINUTO = 6;
    //public final static int CUARENTA = 4;
    //public final static int CUATRO_POR_CUATRO = 1;
    //public final static int OCHO_POR_OCHO = 2;

    private Tiempo tiempo;
    private Modalidad modalidad;
    private Player freestylerActual;
    private int patronesTotalesRestantes;

    public Round(Tiempo tiempo, Modalidad modalidad) {
        this.tiempo = tiempo;
        this.modalidad = modalidad;
    }

    public Tiempo getTiempo() {
        return tiempo;
    }

    public void setTiempo(Tiempo tiempo) {
        this.tiempo = tiempo;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Player getFreestylerActual() {
        return freestylerActual;
    }

    public void setFreestylerActual(Player empieza) {
        this.freestylerActual = empieza;
    }

    public int getPatronesTotalesRestantes() {
        return patronesTotalesRestantes;
    }

    public void setPatronesTotalesRestantes(int patronesTotalesRestantes) {
        this.patronesTotalesRestantes = patronesTotalesRestantes;
    }

    public void restarPatron(){
        patronesTotalesRestantes--;
    }
}
