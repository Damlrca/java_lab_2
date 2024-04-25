package org.example.java_lab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class Lab3_TableController {
    @FXML
    Label table;

    public void setTable(List<Lab3_TablePlayer> playerList) {
        String text = "";
        for (Lab3_TablePlayer player : playerList) {
            text += player.playerName + " : " + player.wins + " wins\n";
        }
        table.setText(text);
    }
}
