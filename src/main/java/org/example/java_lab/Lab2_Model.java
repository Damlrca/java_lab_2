package org.example.java_lab;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lab2_Model {
    Lab2_DataAccessObject dao = new Lab2_DataAccessObject();
    private final ArrayList<Lab2_IObserver> allObs = new ArrayList<>();

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
        synchronized (Lab2_BModel.getGameStateMutex()) {
            dao.nextTick();
            event();
        }
    }

    public void fire(String playerName) {
        synchronized (Lab2_BModel.getGameStateMutex()) {
            dao.fire(playerName);
            event();
        }
    }

    public boolean tryAddPlayer(String playerName) {
        synchronized (Lab2_BModel.getGameStateMutex()) {
            if (dao.tryAddPlayer(playerName)) {
                event();
                return true;
            }
            return false;
        }
    }

    public void setReady(String playerName) {
        synchronized (Lab2_BModel.getGameStateMutex()) {
            dao.setReady(playerName);
            event();
            Lab2_Server.wakeUpGameThread();
        }
    }

    public void setPause(String playerName) {
        synchronized (Lab2_BModel.getGameStateMutex()) {
            dao.setPause(playerName);
            event();
        }
    }

    public void resetGame(String additionalText) {
        synchronized (Lab2_BModel.getGameStateMutex()) {
            dao.resetGame(additionalText);
            event();
        }
    }

    public Lab2_GameState getGameState() {
        synchronized (Lab2_BModel.getGameStateMutex()) {
            return dao.getGameState();
        }
    }

    public void setGameState(Lab2_GameState gameState) {
        synchronized (Lab2_BModel.getGameStateMutex()) {
            dao.setGameState(gameState);
            event();
        }
    }

    public void updateWinner(String playerName) {
        List<Lab3_TablePlayer> table = getTable();
        Lab3_TablePlayer winner = null;
        for (Lab3_TablePlayer player : table) {
            if (Objects.equals(player.playerName, playerName)) {
                winner = player;
                winner.wins++;
                break;
            }
        }
        if (winner == null) {
            winner = new Lab3_TablePlayer(playerName, 1);
        }
        Session session = Lab3_HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(winner);
        tr.commit();
        session.close();
    }

    public List<Lab3_TablePlayer> getTable() {
        List<Lab3_TablePlayer> res = (List<Lab3_TablePlayer>) Lab3_HibernateSessionFactoryUtil.
                getSessionFactory().openSession().createQuery("From Lab3_TablePlayer").list();
        return res;
    }
}
