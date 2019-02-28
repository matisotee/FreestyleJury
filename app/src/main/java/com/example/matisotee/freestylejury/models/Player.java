package com.example.matisotee.freestylejury.models;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int puntiation;
    private int patronesRestantes;


    public Player(String name) {
        this.name = name;
        this.puntiation = 0;
    }

    public void restarPatron(){
        patronesRestantes--;
    }

    public void sumarPuntaje(int puntos){
        puntiation= puntiation + puntos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuntiation() {
        return puntiation;
    }

    public void setPuntiation(int puntiation) {
        this.puntiation = puntiation;
    }

    public int getPatronesRestantes() {
        return patronesRestantes;
    }

    public void setPatronesRestantes(int patronesRestantes) {
        this.patronesRestantes = patronesRestantes;
    }
}
