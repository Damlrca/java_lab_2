package org.example.java_lab;

public class Lab2_Msg {
    private Lab2_MsgAction msgAction;
    private String playerName;

    public Lab2_Msg(Lab2_MsgAction msgAction, String playerName) {
        this.msgAction = msgAction;
        this.playerName = playerName;
    }

    public Lab2_MsgAction getMsgAction() {
        return msgAction;
    }

    public String getPlayerName() {
        return playerName;
    }
}
