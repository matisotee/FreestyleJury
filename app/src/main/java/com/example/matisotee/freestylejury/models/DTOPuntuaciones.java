package com.example.matisotee.freestylejury.models;

public class DTOPuntuaciones {

    private String player;

    private int puntuacion;


    public DTOPuntuaciones(String player, int puntuacion) {
        this.player = player;
        this.puntuacion = puntuacion;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
