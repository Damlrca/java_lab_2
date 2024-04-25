package org.example.java_lab;

import java.util.List;

public class Lab2_Resp {
    private final Lab2_RespAction respAction;

    private final Lab2_GameState gameState;

    private final List<Lab3_TablePlayer> table;

    public Lab2_Resp(Lab2_RespAction respAction, Lab2_GameState gameState, List<Lab3_TablePlayer> table) {
        this.respAction = respAction;
        this.gameState = gameState;
        this.table = table;
    }

    public Lab2_RespAction getRespAction() {
        return respAction;
    }

    public Lab2_GameState getGameState() {
        return gameState;
    }

    public List<Lab3_TablePlayer> getTable() {
        return table;
    }
}
