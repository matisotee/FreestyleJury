package com.example.matisotee.freestylejury.models;

import java.util.ArrayList;

public enum Tiempo {

    MINUTO(6,"minuto"),CUARENTA(4,"40 segundos"),NOVENTA(9,"9 entradas");

    private int cantidadTotalPatrones;
    private String tiempo;

    Tiempo(int cantTotalPatrones, String nombreTiempo){

        this.cantidadTotalPatrones=cantTotalPatrones;
        this.tiempo=nombreTiempo;
    }

    public int getCantidadTotalPatrones() {
        return cantidadTotalPatrones;
    }

    public String getTiempo() {
        return tiempo;
    }


    public static ArrayList<String> getTiempos(){
        Tiempo[] arregloTiempo = Tiempo.values();
        ArrayList<String> tiempos = new ArrayList<String>();

        for (int i = 0; i<arregloTiempo.length;i++){
            tiempos.add(arregloTiempo[i].getTiempo());
        }

        return tiempos;
    }

    public static Tiempo getTiempo(String nombre){
        Tiempo[] arregloTiempos = Tiempo.values();

        int i=0;

        while ((arregloTiempos[i].getTiempo())!= (nombre)){
            i++;
        }

        return arregloTiempos[i];
    }

}
