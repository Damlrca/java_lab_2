package org.example.java_lab;

import java.util.ArrayList;
import java.util.Objects;

public class Lab2_GameState {
    private static String[] funnyColors = {"#ab274f", "#7fffd4", "#1faee9", "#cd7f32"};
    private Lab2_Targets targets = new Lab2_Targets();
    private ArrayList<Lab2_Player> players = new ArrayList<>();
    private Lab2_GameStatus gameStatus = Lab2_GameStatus.WAIT_PLAYERS;
    private String text = new String();

    public void nextTick() {
        if (gameStatus != Lab2_GameStatus.ONGOING) return;
        targets.nextTick();
        for (Lab2_Player player : players) {
            player.nextTick(targets);
        }
        generateText();
    }

    private void generateText() {
        text = "";
        for (Lab2_Player player : players) {
            text += "Игрок: " + player.getPlayerName() + " (" + player.getStatus() + ")\n";
            text += "Счёт игрока: " + player.getCountScore() + "\n";
            text += "Число выстрелов: " + player.getCountBullets() + "\n";
        }
    }

    public void fire(String playerName) {
        if (gameStatus != Lab2_GameStatus.ONGOING) return;
        for (Lab2_Player player : players) {
            if (Objects.equals(player.getPlayerName(), playerName)) {
                player.newBullet();
            }
        }
        generateText();
    }

    public boolean tryAddPlayer(String playerName) {
        if (gameStatus != Lab2_GameStatus.WAIT_PLAYERS || players.size() >= 4) return false;
        for (Lab2_Player player : players) {
            if (Objects.equals(player.getPlayerName(), playerName)) {
                return false;
            }
        }
        players.add(new Lab2_Player(playerName, 45, (players.size() + 1) * 100.0, funnyColors[players.size()]));
        generateText();
        return true;
    }

    public void setReady(String playerName) {
        for (Lab2_Player player : players) {
            if (Objects.equals(player.getPlayerName(), playerName)) {
                player.setStatus(Lab2_PlayerStatus.READY);
            }
        }
        generateText();
        for (Lab2_Player player : players) {
            if (player.getStatus() == Lab2_PlayerStatus.PAUSE) {
                return;
            }
        }
        gameStatus = Lab2_GameStatus.ONGOING;
    }

    public void setPause(String playerName) {
        for (Lab2_Player player : players) {
            if (Objects.equals(player.getPlayerName(), playerName)) {
                player.setStatus(Lab2_PlayerStatus.PAUSE);
            }
        }
        if (gameStatus == Lab2_GameStatus.ONGOING) {
            gameStatus = Lab2_GameStatus.PAUSE;
        }
        generateText();
    }

    public void resetGame(String additionalText) {
        targets = new Lab2_Targets();
        gameStatus = Lab2_GameStatus.WAIT_PLAYERS;
        for (Lab2_Player player : players) {
            player.resetPlayer();
        }
        // generateText(); // resetGame function resets everything but text
        text += additionalText;
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

    public String getText() {
        return text;
    }
}
