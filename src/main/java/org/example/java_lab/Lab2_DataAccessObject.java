package org.example.java_lab;

public class Lab2_DataAccessObject {
    private Lab2_GameState gameState = new Lab2_GameState();

    public void nextTick() {
        gameState.nextTick();
    }

    public  void fire(String playerName) {
        gameState.fire(playerName);
    }
    public Lab2_GameState getGameState() {
        return gameState;
    }

    public void setGameState(Lab2_GameState gameState) {
        this.gameState = gameState;
    }

}
