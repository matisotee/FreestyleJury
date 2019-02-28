package com.example.matisotee.freestylejury.models;

import java.util.ArrayList;

public enum Modalidad {

    CUATROXCUATRO("4 x 4",1),OCHOXOCHO("8 x 8",2),LIBRE("Libre",0);

    private String nombre;
    private int patrones;

    private Modalidad(String nombre, int patrones){
        this.nombre=nombre;
        this.patrones=patrones;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPatrones() {
        return patrones;
    }

    public static ArrayList<String> getModalidades(){
        Modalidad[] arregloModalidades = Modalidad.values();
        ArrayList<String> modalidades = new ArrayList<String>();

        for (int i = 0; i<arregloModalidades.length;i++){
            modalidades.add(arregloModalidades[i].getNombre());
        }
        return modalidades;
    }

    public static Modalidad getModalidad(String nombre){
        Modalidad[] arregloModalidades = Modalidad.values();

        int i=0;

        while ((arregloModalidades[i].getNombre())!= (nombre)){
            i++;
        }

        return arregloModalidades[i];
    }

}
