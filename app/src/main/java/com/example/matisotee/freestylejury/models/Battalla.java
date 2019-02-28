package com.example.matisotee.freestylejury.models;

import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class Battalla implements Serializable {

    private int roundActual;
    private ArrayList<Round> rounds;
    private ArrayList<Player> players;
    private Player ganador;

    public Battalla(ArrayList<Round> rounds, ArrayList<Player> players) {
        this.rounds = rounds;
        this.players = players;
    }


    //Getters y Setters

    public int getRoundActual() {
        return roundActual;
    }

    public void setRoundActual(int roundActual) {
        this.roundActual = roundActual;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getGanador() {
        return ganador;
    }

    public void setGanador(Player ganador) {
        this.ganador = ganador;
    }

    //Metodos complejos

    private void cambiarTurno(){

        Player p = rounds.get(roundActual).getFreestylerActual();
        int currentPlayer = players.indexOf(p);
        currentPlayer++;
        if(currentPlayer>= players.size()){
            currentPlayer=0;
        }

        Player playerActual = players.get(currentPlayer);
        int patrones = rounds.get(roundActual).getModalidad().getPatrones();
        if(patrones==0){
            patrones=rounds.get(roundActual).getTiempo().getCantidadTotalPatrones();
        }
        playerActual.setPatronesRestantes(patrones);
        rounds.get(roundActual).setFreestylerActual(playerActual);
    }

    public void configurarTurnos(int empieza){
        //int empezoPrimeraRonda = empieza;
        int cantRondas = rounds.size();
        int cantPlayers = players.size()-1;
        int currentRound = 0;

        while (currentRound <cantRondas) {
            while (empieza  <= cantPlayers && currentRound < cantRondas) {
                rounds.get(currentRound).setFreestylerActual(players.get(empieza));
                empieza++;
                currentRound++;
            }

            empieza = 0;

            /*while (empieza  < empezoPrimeraRonda && currentRound < cantRondas) {
                rounds.get(currentRound).setEmpieza(empiezaRondactual);
                empiezaRondactual++;
                currentRound++;
            }**/
        }
    }

    public void iniciarRound(){
        roundActual++;

        if(roundActual > rounds.size()){return;}

        int cantPlayers = players.size();

        Modalidad modalidad = rounds.get(roundActual).getModalidad();

        int numeroPatrones = modalidad.getPatrones();

        Tiempo tiempo = rounds.get(roundActual).getTiempo();

        int patronesTotales = (tiempo.getCantidadTotalPatrones())*cantPlayers;

        rounds.get(roundActual).setPatronesTotalesRestantes(patronesTotales);

        if(numeroPatrones == 0){
            numeroPatrones = (patronesTotales/cantPlayers);
        }

        for(int i = 0;i<cantPlayers;i++){
            players.get(i).setPatronesRestantes(numeroPatrones);
        }



    }

    public DTOInfoBatalla getInfoBatalla(){
        Player playerActual = rounds.get(roundActual).getFreestylerActual();

        String nombrePlayerActual = playerActual.getName();
        int patronesRestantes = playerActual.getPatronesRestantes();

        Modalidad modalidadActual = rounds.get(roundActual).getModalidad();

        String nombreModalidad = modalidadActual.getNombre();

        Tiempo tiempoActual = rounds.get(roundActual).getTiempo();

        String nombreTiempo= tiempoActual.getTiempo();

        DTOInfoBatalla dto = new DTOInfoBatalla(nombreModalidad,patronesRestantes,roundActual,false,nombrePlayerActual,false,nombreTiempo);

        int patronesTotalesRestantes = rounds.get(roundActual).getPatronesTotalesRestantes();

        if (patronesTotalesRestantes == 0){
            dto.setTerminarRound(true);
            if(roundActual==(rounds.size()-1)){
                dto.setTerminarBatalla(true);
            }
        }

        return dto;
    }

    public void votar(int puntos){
        Player playerActual = rounds.get(roundActual).getFreestylerActual();

        playerActual.sumarPuntaje(puntos);

        playerActual.restarPatron();

        rounds.get(roundActual).restarPatron();

        int patronesTotalesRestantes = rounds.get(roundActual).getPatronesTotalesRestantes();
        int patronesRestantes = playerActual.getPatronesRestantes();

        if(patronesRestantes == 0 && patronesTotalesRestantes!=0){
            cambiarTurno();
        }
    }

    public ArrayList<DTOPuntuaciones> terminarBatalla(){
        ArrayList<DTOPuntuaciones> puntuaciones=new ArrayList<DTOPuntuaciones>();
        for(int i =0;i<players.size();i++){
            int puntos = players.get(i).getPuntiation();
            String nombre = players.get(i).getName();
            puntuaciones.add(new DTOPuntuaciones(nombre,puntos));
        }

        return puntuaciones;
    }

}
