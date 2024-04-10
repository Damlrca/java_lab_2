package org.example.java_lab;

import java.util.ArrayList;

public class Lab2_Model {
    Lab2_DataAccessObject dao = new Lab2_DataAccessObject();
    private ArrayList<Lab2_IObserver> allObs = new ArrayList<>();

    public void addObserver(Lab2_IObserver o) {
        allObs.add(o);
    }

    public void event() {
        for (Lab2_IObserver o : allObs) {
            o.event(this);
        }
    }

    public void nextTick() {
        dao.nextTick();
        event();
    }

    public Lab2_GameState getGameState() {
        return dao.getGameState();
    }

    public void setGameState(Lab2_GameState gameState) {
        dao.setGameState(gameState);
        event();
    }

}
