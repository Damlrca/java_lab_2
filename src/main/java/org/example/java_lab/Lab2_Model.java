package org.example.java_lab;

import java.util.ArrayList;

public class Lab2_Model {
    Lab2_DataAccessObject dao = new Lab2_DataAccessObject();
    private ArrayList<Lab2_IObserver> allObs = new ArrayList<>();

    public void addObserver(Lab2_IObserver o) {
        synchronized (allObs) {
            allObs.add(o);
        }
    }

    public void removeObserver(Lab2_IObserver o) {
        synchronized (allObs) {
            allObs.remove(o);
        }
    }

    public void event() {
        synchronized (allObs) {
            for (Lab2_IObserver o : allObs) {
                o.event(this);
            }
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
