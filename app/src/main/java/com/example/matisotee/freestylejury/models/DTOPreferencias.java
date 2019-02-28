package com.example.matisotee.freestylejury.models;

import java.util.ArrayList;

public class DTOPreferencias {

    private ArrayList<String> modalidades;
    private ArrayList<String> tiempos;

    public DTOPreferencias(ArrayList<String> modalidades, ArrayList<String> tiempos) {
        this.modalidades = modalidades;
        this.tiempos = tiempos;
    }

    public ArrayList<String> getModalidades() {
        return modalidades;
    }

    public void setModalidades(ArrayList<String> modalidades) {
        this.modalidades = modalidades;
    }

    public ArrayList<String> getTiempos() {
        return tiempos;
    }

    public void setTiempos(ArrayList<String> tiempos) {
        this.tiempos = tiempos;
    }



}
