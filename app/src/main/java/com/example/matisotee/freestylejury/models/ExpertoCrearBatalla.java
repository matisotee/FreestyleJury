package com.example.matisotee.freestylejury.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ExpertoCrearBatalla implements Serializable {

    private Battalla batalla;

    public DTOPreferencias obtenerPreferencias(){
        ArrayList<String> modalidades = Modalidad.getModalidades();
        ArrayList<String> tiempos = Tiempo.getTiempos();

        DTOPreferencias dto = new DTOPreferencias(modalidades,tiempos);

        return dto;
    }

    public void configurarBatalla(DTOBatalla dto){

        ArrayList<String> nombresPlayers = dto.getPlayers();
        ArrayList<Player> players= new ArrayList<Player>();
        ArrayList<Round> rounds = new ArrayList<Round>();

        for(int i=0; i<nombresPlayers.size();i++){
            Player p = new Player(nombresPlayers.get(i));
            players.add(p);
        }

        ArrayList<DTORound> dtoRounds = dto.getRounds();

        for(int i=0; i<dtoRounds.size();i++){
            String nombreModalidad = dtoRounds.get(i).getModalidad();
            String nombreTiempo = dtoRounds.get(i).getTiempo();

            Modalidad modalidad = Modalidad.getModalidad(nombreModalidad);
            Tiempo tiempo = Tiempo.getTiempo(nombreTiempo);

            Round round = new Round(tiempo,modalidad);

            rounds.add(round);
        }

        batalla = new Battalla(rounds,players);
        int empieza = dto.getEmpieza();
        batalla.configurarTurnos(empieza);
    }

    public DTOInfoBatalla iniciarBatalla(){
        batalla.setRoundActual(-1);
        batalla.iniciarRound();

        return batalla.getInfoBatalla();
    }

    public DTOInfoBatalla votar(int puntos){
        batalla.votar(puntos);
        return batalla.getInfoBatalla();
    }

    public DTOInfoBatalla cambiarRound(){
        batalla.iniciarRound();
        return batalla.getInfoBatalla();
    }

    public ArrayList<DTOPuntuaciones> terminarBatalla(){
        return batalla.terminarBatalla();
    }

}
