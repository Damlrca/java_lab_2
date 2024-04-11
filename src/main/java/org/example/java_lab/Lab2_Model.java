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

    public void fire(String playerName) {
        dao.fire(playerName);
        event();
    }

    public boolean tryAddPlayer(String playerName) {
        if (dao.tryAddPlayer(playerName)) {
            event();
            return true;
        }
        return false;
    }

    public void setReady(String playerName) {
        dao.setReady(playerName);
        event();
        Lab2_Server.wakeUpGameThread();
    }

    public void setPause(String playerName) {
        dao.setPause(playerName);
        event();
    }

    public void resetGame(String additionalText) {
        dao.resetGame(additionalText);
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
