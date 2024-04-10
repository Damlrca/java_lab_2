package org.example.java_lab;

import java.util.ArrayList;

public class Lab2_GameState {
    private Lab2_Targets targets = new Lab2_Targets();
    private ArrayList<Lab2_Player> players = new ArrayList<>();
    private Lab2_GameStatus gameStatus = Lab2_GameStatus.ONGOING;

    public void nextTick() {
        targets.nextTick();
        for (Lab2_Player player : players) {
            player.nextTick(targets);
        }
    }

    public Lab2_Targets getTargets() {
        return targets;
    }

    public ArrayList<Lab2_Player> getPlayers() {
        return players;
    }

    public Lab2_GameStatus getGameStatus() {
        return gameStatus;
    }
}
