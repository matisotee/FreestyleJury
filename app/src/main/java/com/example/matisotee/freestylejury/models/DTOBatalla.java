package com.example.matisotee.freestylejury.models;

import java.util.ArrayList;

public class DTOBatalla {

    private int empieza;
    private ArrayList<String> players;
    private ArrayList<DTORound> rounds;

    public int getEmpieza() {
        return empieza;
    }

    public void setEmpieza(int empieza) {
        this.empieza = empieza;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public ArrayList<DTORound> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<DTORound> rounds) {
        this.rounds = rounds;
    }
}
