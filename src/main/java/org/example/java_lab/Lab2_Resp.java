package org.example.java_lab;

public class Lab2_Resp {
    private Lab2_RespAction respAction;

    private Lab2_GameState gameState;

    public Lab2_Resp(Lab2_RespAction respAction, Lab2_GameState gameState) {
        this.respAction = respAction;
        this.gameState = gameState;
    }

    public Lab2_RespAction getRespAction() {
        return respAction;
    }

    public Lab2_GameState getGameState() {
        return gameState;
    }
}
