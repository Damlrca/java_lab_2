package org.example.java_lab;

public class Lab2_DataAccessObject {
    private Lab2_GameState gameState = new Lab2_GameState();

    public void nextTick() {
        gameState.nextTick();
    }

    public void fire(String playerName) {
        gameState.fire(playerName);
    }

    public boolean tryAddPlayer(String playerName) {
        return gameState.tryAddPlayer(playerName);
    }

    public void setReady(String playerName) {
        gameState.setReady(playerName);
    }

    public void setPause(String playerName) {
        gameState.setPause(playerName);
    }

    public void resetGame(String additionalText) {
        gameState.resetGame(additionalText);
    }

    public Lab2_GameState getGameState() {
        return gameState;
    }

    public void setGameState(Lab2_GameState gameState) {
        this.gameState = gameState;
    }

}
