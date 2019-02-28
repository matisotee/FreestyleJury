package com.example.matisotee.freestylejury.models;

public class DTOInfoBatalla {

    private String modalidad;
    private int patronesRestantes;
    private int round;
    private boolean terminarRound;
    private String turno;
    private boolean terminarBatalla;
    private String tiempo;

    public DTOInfoBatalla(String modalidad, int patronesRestantes, int round, boolean terminarRound, String turno, boolean terminarBatalla,String tiempo) {
        this.modalidad = modalidad;
        this.patronesRestantes = patronesRestantes;
        this.round = round;
        this.terminarRound = terminarRound;
        this.turno = turno;
        this.terminarBatalla=terminarBatalla;
        this.tiempo=tiempo;
    }

    public boolean getTerminarBatalla() {
        return terminarBatalla;
    }

    public void setTerminarBatalla(boolean terminarBatalla) {
        this.terminarBatalla = terminarBatalla;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public int getPatronesRestantes() {
        return patronesRestantes;
    }

    public void setPatronesRestantes(int patronesRestantes) {
        this.patronesRestantes = patronesRestantes;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public boolean getTerminarRound() {
        return terminarRound;
    }

    public void setTerminarRound(boolean terminarRound) {
        this.terminarRound = terminarRound;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
}
